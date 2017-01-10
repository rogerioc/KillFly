package com.rogerio.KillFly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Main extends Activity {
	ImageView btStart;
	OnClickListener lstStart = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent start = new Intent(getApplicationContext(),KillFly.class);
			startActivity(start);
			
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btStart = (ImageView) findViewById(R.id.start);
		btStart.setOnClickListener(lstStart);
	}
	
}
