package com.rogerio.KillFly;
/*
 * Autor: Rogerio C. Santos - rogerio@rogeriocs.com.br
 * http://www.rogeriocs.com.br
 */
import libs.com.rogerio.utils.Sprite;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Hero extends SpritesObjs {
	public Hero(Context context) throws Exception{
		this.mContext=context;
		mSprite = new Sprite();
		Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),R.drawable.bowl);
		mSprite.init(bmp, bmp.getHeight(), bmp.getWidth(), 1, 1);
	}
}
