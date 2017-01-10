package com.rogerio.KillFly;
import java.util.ArrayList;
import java.util.List;

import libs.com.rogerio.utils.AccelerometerListener;
import libs.com.rogerio.utils.AccelerometerManager;
import libs.com.rogerio.utils.Physics;
import libs.com.rogerio.utils.Vector2;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.view.View;
/*
 * Autor: Rogerio C. Santos - rogerio@rogeriocs.com.br
 * http://www.rogeriocs.com.br
 */
public class GameCore implements Runnable, AccelerometerListener{
	
	Hero mHero;
	boolean mRun=true,mStarted;
	long mTime,mLTime;
	View mScreen;
	Context mContext;
	int mMov=2;
	List<Fly> mFlys;
	private Bitmap mBackground;
	boolean mBackDraw;
	Vibrator mVibre; //Vibrator cell
	long mVibreKill;
	long mVibreDie=100;
	public GameCore(Context context, View screen) throws Exception {		
		
		this.mScreen=screen;
		this.mContext=context;
		mHero = new Hero(context);
		mHero.setPosition(new Vector2());
		
		mFlys = new ArrayList<Fly>();
		
		
		mLTime=System.currentTimeMillis();
		AccelerometerManager.setContextAcc(context);
		mStarted=false;
		mBackDraw=false;
		mBackground = BitmapFactory.decodeResource( context.getResources() ,R.drawable.bakground);
		mVibre = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		mVibreKill=50;
		
	}
	
	private void Starter(){
		createEnemys();	
		onAccStart();
		mBackground = Bitmap.createScaledBitmap(mBackground, mScreen.getWidth(), mScreen.getHeight(), true);
		Thread th= new Thread(this);
		th.start();
	}

	private void createEnemys() {
		for(int i=0;i<5;i++){
			Fly fly;
			try {
				fly = new Fly(mContext);
				fly.setPosition(generatePosit());
				mFlys.add(fly);
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
			
		}
	}
	
	//Gerar local de inicio
	private Vector2 generatePosit(){	
		int sizeW = mScreen.getWidth();//*2;
		int sizeH = mScreen.getHeight();//*2;		
		return generatePosition(sizeW, sizeH);
	}

	private Vector2 generatePosition(int sizeW, int sizeH) {
		int x,y,sign;
		sign = getSign();
		do{
			x = (int) (Math.random()*(sizeW*sign));
		}while(x>sizeW);
		
		sign = getSign();
		y = (int) (Math.random()*(sizeH*sign));		
		
		return new Vector2(x,y);
	}

	private int getSign() {
		int sign;
		if(Math.random()*100%2==0){
			sign = -1;
		}else{
			sign = 1;
		}
		return sign;
	}
	
	public void posTouch(int x, int y) {
		List<Fly> delList = new ArrayList<Fly>();
		for (Fly f : mFlys) {
			if(Physics.collide(f.getSprite(), new Vector2(x, y))){
				delList.add(f);
				mVibre.vibrate(mVibreKill);
				break;
			}
		}
		//Remover
		for (Fly objetosImg : delList) {
			mFlys.remove(objetosImg);
		}
		
		if(mFlys.size()<1){
			createEnemys();
		}
	}

	public void draw(Canvas canvas) {
		if(!mBackDraw){
			canvas.drawBitmap(mBackground,0,0,null);
			//mBackDraw=true;
		}
		
		try {
			mHero.draw(canvas);
			for (Fly fly : mFlys) 
				fly.draw(canvas);
		} catch (Exception e) {
			
		}
		
		
	}

	public void onSizeChanged(int w, int h, int oldw, int oldh) {
		mHero.setPosition(new Vector2(w/2, h/2));
		if(!mStarted)
			Starter();
	}

	protected void onAccStart() {	        
        if (AccelerometerManager.isSupported()) {
            AccelerometerManager.startListening(this);
        }
    }
	public void onAccDestroy() {
		mRun=false;
		if (AccelerometerManager.isListening()) {
            AccelerometerManager.stopListening();
        }
	}
	Handler hd = new Handler(){
		public void dispatchMessage(android.os.Message msg) {
			core();
		};	
	};
	@Override
	public void run() {
		Looper.prepare();
		
		while(mRun){
			mTime=System.currentTimeMillis();
			
			if((mTime-mLTime)>100){
				hd.sendEmptyMessage(0);
				mLTime=mTime;
			}
			
		}
		
	}

	private void core() {
		
		mHero.update(mTime);
		for (Fly fly : mFlys) {			
			Vector2 pos = fly.getPosition();
			pos = pos.normal(mHero.getPosition());
			//Procurar o heroi
			float r=(float)Math.atan2(pos.getX(),pos.getY()) + 3.141516f / 2 ;
			
					
			pos.setX((pos.getX()*mMov)+fly.getPosition().getX());
			pos.setY((pos.getY()*mMov)+fly.getPosition().getY());	
			
			//Rota??o do inimigo: Olhar para o heroi
			fly.setRotate((float)Math.toDegrees(r*-1));
			
			fly.setPosition(pos);
			if(Physics.collide(mHero.getSprite(), fly.getSprite())){
				mVibre.vibrate(mVibreDie);
				
				//Sleep to begin game
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					
				}
				mHero.setPosition(generatePosition(mScreen.getWidth(),mScreen.getHeight()));
			}
			fly.update(mTime);
		}
		
		
		mScreen.invalidate();
		
		
	}

	@Override
	public void onAccelerationChanged(float x, float y, float z) {
		float xp = mHero.getPosition().getX();
		float yp = mHero.getPosition().getY();
		
		if(x>0)
			xp = Math.max(xp-x, 0);
		else
			xp = Math.min(xp-x, mScreen.getWidth()-mHero.getSprite().getWidth());
			
		if(y<0)
			yp = Math.max(yp+y, 0);
		else
			yp = Math.min(yp+y, mScreen.getHeight()-mHero.getSprite().getHeight());
		
		
		
		mHero.setPosition(new Vector2(xp,yp));
		
	}

	@Override
	public void onShake(float force) {
		// TODO Auto-generated method stub
		
	}

}
