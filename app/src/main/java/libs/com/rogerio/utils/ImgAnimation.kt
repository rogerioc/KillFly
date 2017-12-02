package libs.com.rogerio.utils

/*
 * Autor: Rogerio C. Santos - rogerio@rogeriocs.com.br
 * http://www.rogeriocs.com.br
 */

import android.graphics.Canvas
import android.graphics.drawable.Drawable

open class ImgAnimation {

    protected lateinit var obj: List<ObjetosImg>
    protected var max: Int = 0
    internal var ind = 0
    lateinit var position: Vector2
    lateinit  internal var img: Drawable
    lateinit  internal var imgO: ObjetosImg
    var isFinish: Boolean = false

    constructor(objs: List<ObjetosImg>) {
        obj = objs
        max = obj.size
        imgO = obj[0]
        img = imgO?.img
        isFinish = false
    }

    constructor() : super() {}

    open fun getImg(): ObjetosImg {
        return imgO
    }

    open fun draw(canvas: Canvas) {

        img.setBounds(position.x.toInt(), position.y.toInt(),
                position.x.toInt() + img.intrinsicWidth, position.y.toInt() + img.intrinsicHeight)
        img.draw(canvas)

    }

    protected fun getObj() {
        getNewImg()

    }

    fun getNewImg() {
        if (ind + 1 == max) isFinish = true
        ind = (ind + 1) % max
        imgO = obj[ind]
        imgO.position = position
        img = imgO.img
    }

    fun moveDown(mov: Int) {
        position.y = position.y + mov
        ind = (ind + 1) % max
        //getObj();

    }

}