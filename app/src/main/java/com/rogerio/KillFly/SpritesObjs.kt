package com.rogerio.KillFly

/*
 * Autor: Rogerio C. Santos - rogerio@rogeriocs.com.br
 * http://www.rogeriocs.com.br
 */
import libs.com.rogerio.utils.Sprite
import libs.com.rogerio.utils.Vector2
import android.content.Context
import android.graphics.Canvas

open class SpritesObjs {

    var sprite: Sprite? = null
        protected set
    protected var mContext: Context? = null

    var position: Vector2
        get() = sprite!!.position!!
        set(pos) {
            sprite!!.position = pos
        }

    var rotate: Float
        get() = sprite!!.rotate
        set(rot) {
            sprite!!.rotate = rot
        }

    fun update(time: Long) {
        sprite!!.Update(time)
    }

    fun draw(canvas: Canvas) {
        sprite!!.draw(canvas)
    }
}