package com.rogerio.KillFly;
/*
 * Autor: Rogerio C. Santos - rogerio@rogeriocs.com.br
 * http://www.rogeriocs.com.br
 */
import libs.com.rogerio.utils.Sprite;
import libs.com.rogerio.utils.Vector2;
import android.content.Context;
import android.graphics.Canvas;

public class SpritesObjs{

	protected Sprite mSprite;
	protected Context mContext;

	public Sprite getSprite() {
		return mSprite;
	}

	public SpritesObjs() {
		
	}

	public void update(long time) {
		mSprite.Update(time);
	}

	public void draw(Canvas canvas) {
		mSprite.draw(canvas);
	}

	public void setPosition(Vector2 pos) {
		mSprite.setPosition(pos);
	}

	public Vector2 getPosition() {
		return mSprite.getPosition();
	}
	
	public void setRotate(float rot){
		mSprite.setRotate(rot);
	}

	public float getRotate(){
		return mSprite.getRotate();
	}
}