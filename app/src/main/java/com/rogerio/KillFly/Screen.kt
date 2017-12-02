package com.rogerio.KillFly

/*
 * Autor: Rogerio C. Santos - rogerio@rogeriocs.com.br
 * http://www.rogeriocs.com.br
 */

import android.content.Context
import android.graphics.Canvas
import android.view.MotionEvent
import android.view.View

class Screen
constructor(context: Context) : View(context) {
    private val mGame: GameCore

    init {
        mGame = GameCore(context, this)

        isFocusable = true
        //requestLayout();
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mGame.posTouch(event.x.toInt(), event.y.toInt())
        return true
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        mGame.draw(canvas)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mGame.onSizeChanged(w, h, oldw, oldh)
    }

    fun destroy() {
        mGame.onAccDestroy()
    }

    fun starter() {


    }


}
