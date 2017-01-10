package com.rogerio.KillFly;
/*
 * Autor: Rogerio C. Santos - rogerio@rogeriocs.com.br
 * http://www.rogeriocs.com.br
 */
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class KillFly extends Activity {
    /** Called when the activity is first created. */
	Screen mScreen;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
			mScreen = new Screen(this);
			setContentView(mScreen);
		}  catch (Exception e) {
			Toast.makeText(this, "Ocorreu algum erro inesperado.", Toast.LENGTH_LONG).show();
			finish();
		}
        
    }
    
    @Override
    protected void onStart() {    	
    	super.onStart();
    	
    	
    }

	@Override
	protected void onResume() {
		//screen.starter();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mScreen.destroy();
	}
    
    
    
    
}