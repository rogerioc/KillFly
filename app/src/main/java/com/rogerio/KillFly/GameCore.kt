package com.rogerio.KillFly

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Handler
import android.os.Looper
import android.os.Vibrator
import android.view.View
import libs.com.rogerio.utils.AccelerometerListener
import libs.com.rogerio.utils.AccelerometerManager
import libs.com.rogerio.utils.Physics
import libs.com.rogerio.utils.Vector2
import java.util.*
import kotlin.math.sign

/*
 * Autor: Rogerio C. Santos - rogerio@rogeriocs.com.br
 * http://www.rogeriocs.com.br
 */
class GameCore
constructor(internal var mContext: Context, internal var mScreen: View) : Runnable, AccelerometerListener {

    internal var mHero: Hero
    internal var mRun = true
    internal var mStarted: Boolean = false
    internal var mTime: Long = 0
    internal var mLTime: Long = 0
    internal var mMov = 2
    internal var mFlys: MutableList<Fly>
    private var mBackground: Bitmap? = null
    internal var mBackDraw: Boolean = false
    internal var mVibre: Vibrator //Vibrator cell
    internal var mVibreKill: Long = 0
    internal var mVibreDie: Long = 100

    private val sign: Int
        get() {
            val sign: Int
            if (Math.random() * 100 % 2 == 0.0) {
                sign = -1
            } else {
                sign = 1
            }
            return sign
        }
    internal var hd: Handler = object : Handler() {
        override fun dispatchMessage(msg: android.os.Message) {
            core()
        }
    }

    init {
        mHero = Hero(mContext)
        mHero.position = Vector2()

        mFlys = ArrayList()


        mLTime = System.currentTimeMillis()
        AccelerometerManager.setContextAcc(mContext)
        mStarted = false
        mBackDraw = false
        mBackground = BitmapFactory.decodeResource(mContext.resources, R.drawable.bakground)
        mVibre = mContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        mVibreKill = 50

    }

    private fun Starter() {
        createEnemys()
        onAccStart()
        mBackground = Bitmap.createScaledBitmap(mBackground!!, mScreen.width, mScreen.height, true)
        val th = Thread(this)
        th.start()
    }

    private fun createEnemys() {
        for (i in 0..4) {
            val fly: Fly
            try {
                fly = Fly(mContext)
                fly.position = generatePosit()
                mFlys.add(fly)
            } catch (e: Exception) {

                e.printStackTrace()
            }

        }
    }

    //Gerar local de inicio
    private fun generatePosit(): Vector2 {
        val sizeW = mScreen.width//*2;
        val sizeH = mScreen.height//*2;
        return generatePosition(sizeW, sizeH)
    }

    private fun generatePosition(sizeW: Int, sizeH: Int): Vector2 {
        var x: Int
        val y: Int
        var sign: Int
        sign = this.sign
        do {
            x = (Math.random() * (sizeW * sign)).toInt()
        } while (x > sizeW)

        sign = this.sign
        y = (Math.random() * (sizeH * sign)).toInt()

        return Vector2(x.toFloat(), y.toFloat())
    }

    fun posTouch(x: Int, y: Int) {
        val delList = ArrayList<Fly>()
        for (f in mFlys) {
            if (Physics.collide(f.sprite!!, Vector2(x.toFloat(), y.toFloat()))) {
                delList.add(f)
                mVibre.vibrate(mVibreKill)
                break
            }
        }
        //Remover
        for (objetosImg in delList) {
            mFlys.remove(objetosImg)
        }

        if (mFlys.size < 1) {
            createEnemys()
        }
    }

    fun draw(canvas: Canvas) {
        if (!mBackDraw) {
            canvas.drawBitmap(mBackground!!, 0f, 0f, null)
            //mBackDraw=true;
        }

        try {
            mHero.draw(canvas)
            for (fly in mFlys)
                fly.draw(canvas)
        } catch (e: Exception) {

        }


    }

    fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        mHero.position = Vector2((w / 2).toFloat(), (h / 2).toFloat())
        if (!mStarted)
            Starter()
    }

    protected fun onAccStart() {
        if (AccelerometerManager.isSupported) {
            AccelerometerManager.startListening(this)
        }
    }

    fun onAccDestroy() {
        mRun = false
        if (AccelerometerManager.isListening) {
            AccelerometerManager.stopListening()
        }
    }

    override fun run() {
        Looper.prepare()

        while (mRun) {
            mTime = System.currentTimeMillis()

            if (mTime - mLTime > 100) {
                hd.sendEmptyMessage(0)
                mLTime = mTime
            }

        }

    }

    private fun core() {

        mHero.update(mTime)
        for (fly in mFlys) {
            var pos = fly.position
            pos = pos.normal(mHero.position)
            //Procurar o heroi
            val r = Math.atan2(pos.x.toDouble(), pos.y.toDouble()).toFloat() + 3.141516f / 2


            pos.x = pos.x * mMov + fly.position.x
            pos.y = pos.y * mMov + fly.position.y

            //Rota??o do inimigo: Olhar para o heroi
            fly.rotate = Math.toDegrees((r * -1).toDouble()).toFloat()

            fly.position = pos
            if (Physics.collide(mHero.sprite!!, fly.sprite!!)) {
                mVibre.vibrate(mVibreDie)

                //Sleep to begin game
                try {
                    Thread.sleep(600)
                } catch (e: InterruptedException) {

                }

                mHero.position = generatePosition(mScreen.width, mScreen.height)
            }
            fly.update(mTime)
        }


        mScreen.invalidate()


    }

    override fun onAccelerationChanged(x: Float, y: Float, z: Float) {
        var xp = mHero.position.x
        var yp = mHero.position.y

        if (x > 0)
            xp = Math.max(xp - x, 0f)
        else
            xp = Math.min(xp - x, (mScreen.width - mHero.sprite!!.width).toFloat())

        if (y < 0)
            yp = Math.max(yp + y, 0f)
        else
            yp = Math.min(yp + y, (mScreen.height - mHero.sprite!!.height).toFloat())



        mHero.position = Vector2(xp, yp)

    }

    override fun onShake(force: Float) {
        // TODO Auto-generated method stub

    }

}
