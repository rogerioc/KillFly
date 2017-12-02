package com.rogerio.KillFly

/*
 * Autor: Rogerio C. Santos - rogerio@rogeriocs.com.br
 * http://www.rogeriocs.com.br
 */
import android.app.Activity
import android.os.Bundle
import android.widget.Toast

class KillFly : Activity() {
    /** Called when the activity is first created.  */
    internal lateinit var mScreen: Screen

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            mScreen = Screen(this)
            setContentView(mScreen)
        } catch (e: Exception) {
            Toast.makeText(this, "Ocorreu algum erro inesperado.", Toast.LENGTH_LONG).show()
            finish()
        }

    }

    override fun onStart() {
        super.onStart()


    }

    override fun onResume() {
        //screen.starter();
        super.onResume()
    }

    override fun onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy()
        mScreen!!.destroy()
    }


}