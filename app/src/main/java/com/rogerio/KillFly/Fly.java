package com.rogerio.KillFly;
/*
 * Autor: Rogerio C. Santos - rogerio@rogeriocs.com.br
 * http://www.rogeriocs.com.br
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import libs.com.rogerio.utils.Sprite;

public class Fly extends SpritesObjs {
	public Fly(Context context) throws Exception{
		this.mContext=context;
		mSprite = new Sprite();
		Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),R.drawable.flyleft);		
		mSprite.init(bmp, bmp.getHeight(), bmp.getHeight(), 7, 15);
	}
	
	
	
}
