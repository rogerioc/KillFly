package libs.com.rogerio.utils;
/*
 * Autor: Rogerio C. Santos - rogerio@rogeriocs.com.br
 * http://www.rogeriocs.com.br
 */
import android.content.Context;

public interface AccelerometerListener {

	public void onAccelerationChanged(float x, float y, float z);
	 
	public void onShake(float force);

	
}
