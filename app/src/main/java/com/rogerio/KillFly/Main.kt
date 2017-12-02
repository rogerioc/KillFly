package com.rogerio.KillFly

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View.OnClickListener
import android.widget.ImageView

class Main : Activity() {
    internal lateinit var btStart: ImageView
    internal var lstStart: OnClickListener = OnClickListener {
        val start = Intent(applicationContext, KillFly::class.java)
        startActivity(start)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        btStart = findViewById(R.id.start) as ImageView
        btStart.setOnClickListener(lstStart)
    }

}
