package com.rogerio.KillFly;
/*
 * Autor: Rogerio C. Santos - rogerio@rogeriocs.com.br
 * http://www.rogeriocs.com.br
 */

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class Screen extends View {
	private GameCore mGame;
	public Screen(Context context) throws Exception {	
		super(context);
		mGame = new GameCore(context,this);
		
		setFocusable(true);
		//requestLayout();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mGame.posTouch((int)event.getX(),(int)event.getY());
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		mGame.draw(canvas);
	  
	}
	
	 protected void onSizeChanged(int w, int h, int oldw, int oldh) {
	        super.onSizeChanged(w, h, oldw, oldh);	       
	        mGame.onSizeChanged(w, h, oldw, oldh);
	 }
	 
	 public void destroy(){
		 mGame.onAccDestroy();
	 }

	public void starter() {
		
		
	}

	
	 
}
