package com.rogerio.KillFly

/*
 * Autor: Rogerio C. Santos - rogerio@rogeriocs.com.br
 * http://www.rogeriocs.com.br
 */
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import libs.com.rogerio.utils.Sprite

class Fly
constructor(context: Context) : SpritesObjs() {
    init {
        this.mContext = context
        sprite = Sprite()
        val bmp = BitmapFactory.decodeResource(context.resources, R.drawable.flyleft)
        sprite!!.init(bmp, bmp.height, bmp.height, 7, 15)
    }


}
