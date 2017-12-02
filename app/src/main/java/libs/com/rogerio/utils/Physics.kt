package libs.com.rogerio.utils


/*
 * Autor: Rogerio C. Santos - rogerio@rogeriocs.com.br
 * http://www.rogeriocs.com.br
 */
object Physics {
    private val g = 0.9f

    fun gravity(obj: ObjetosImg) {
        if (obj.vel < 55.5)
            obj.vel = obj.vel + g
        obj.position.y = obj.position.y + obj.vel.toInt()
    }

    fun collide(obj1: ObjetosImg, obj2: ObjetosImg): Boolean {
        val wX = (obj1.position.x + obj1.objW).toInt()
        val h = (obj1.position.y + obj1.objH).toInt()
        val dimension = obj2.position.x >= obj1.position.x && obj2.position.x < wX || obj2.position.x + obj2.objW >= obj1.position.x && obj2.position.x + obj2.objW < wX

        return if (dimension && (obj2.position.y >= obj1.position.y && obj2.position.y <= h
                || obj2.position.y + obj2.objH >= obj1.position.y && obj2.position.y <= h
                || obj2.position.y >= obj1.position.y + obj1.objH)) {
            true
        } else false


    }

    fun collide(obj1: Sprite, obj2: Sprite): Boolean {
        val w = (obj1.position.x + obj1.width).toInt()
        val h = (obj1.position.y + obj1.height).toInt()

        val dimension = obj2.position.x >= obj1.position.x && obj2.position.x < w || obj2.position.x + obj2.width >= obj1.position.x && obj2.position.x + obj2.width < w

        return if (dimension && (obj2.position.y >= obj1.position.y && obj2.position.y <= h || obj2.position.y + obj2.height >= obj1.position.y && obj2.position.y + obj2.height <= h)) {
            true
        } else false


    }

    fun collide(obj1: Sprite, obj2: Vector2): Boolean {
        val wX = (obj1.position.x + obj1.width).toInt()
        val h = (obj1.position.y + obj1.height).toInt()
        val dimension = obj2.x >= obj1.position.x && obj2.x < wX


        return if (dimension && obj2.y >= obj1.position.y && obj2.y <= h) {
            true
        } else false


    }

}
