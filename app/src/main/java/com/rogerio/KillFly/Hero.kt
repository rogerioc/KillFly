package com.rogerio.KillFly

/*
 * Autor: Rogerio C. Santos - rogerio@rogeriocs.com.br
 * http://www.rogeriocs.com.br
 */
import android.content.Context
import android.graphics.BitmapFactory
import libs.com.rogerio.utils.Sprite

class Hero
constructor(context: Context) : SpritesObjs() {
    init {
        this.mContext = context
        sprite = Sprite()
        val bmp = BitmapFactory.decodeResource(context.resources, R.drawable.bowl)
        sprite!!.init(bmp, bmp.height, bmp.width, 1, 1)
    }
}
