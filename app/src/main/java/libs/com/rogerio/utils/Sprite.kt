package libs.com.rogerio.utils

/*
 * Autor: Rogerio C. Santos - rogerio@rogeriocs.com.br
 * http://www.rogeriocs.com.br
 */
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Rect

class Sprite {

    private var mAnimation: Bitmap? = null

    private val mSRectangle: Rect
    private var mFPS: Int = 0
    private var mNoOfFrames: Int = 0
    private var mCurrentFrame: Int = 0
    private var mFrameTimer: Long = 0
    var height: Int = 0
        private set
    var width: Int = 0
        private set
    lateinit var position: Vector2

    var rotate: Float = 0.toFloat()

    init {
        mSRectangle = Rect(0, 0, 0, 0)
        mFrameTimer = 0
        mCurrentFrame = 0

    }

    @Throws(Exception::class)
    fun init(theBitmap: Bitmap?, Height: Int, Width: Int, theFPS: Int, theFrameCount: Int) {
        if (theBitmap == null) {
            throw Exception("Null Bitmap.")
        }
        mAnimation = theBitmap
        height = Height
        width = Width
        mSRectangle.top = 0
        mSRectangle.bottom = height
        mSRectangle.left = 0
        mSRectangle.right = width
        mFPS = 1000 / theFPS
        mNoOfFrames = theFrameCount
        rotate = 0f
    }

    fun Update(GameTime: Long) {
        if (GameTime > mFrameTimer + mFPS) {
            mFrameTimer = GameTime
            mCurrentFrame += 1

            if (mCurrentFrame >= mNoOfFrames) {
                mCurrentFrame = 0
            }
        }

        mSRectangle.left = mCurrentFrame * width
        mSRectangle.right = mSRectangle.left + width
    }

    fun draw(canvas: Canvas) {


        val matrix = Matrix()
        // rotate the Bitmap
        matrix.postRotate(rotate)

        val resizedBitmap = Bitmap.createBitmap(mAnimation!!, mSRectangle.left, mSRectangle.top,
                width, height, matrix, true)


        val dest = Rect(position!!.x.toInt(), position!!.y.toInt(), position!!.x.toInt() + width,
                position!!.y.toInt() + height)
        canvas.drawBitmap(resizedBitmap, position!!.x, position!!.y, null)
        //canvas.drawBitmap(resizedBitmap, mSRectangle, dest, null);
    }


}