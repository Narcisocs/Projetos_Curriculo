package br.com.narcisocsales.trabalho2fa7;

import android.content.Context;
import android.hardware.SensorEvent;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

/**
 * Created by Narciso on 24/08/2015.
 */
public class SensorUtil {

    public static float[] fixAcelerometro(Context context, SensorEvent event){
        float sensorX = 0;
        float sensorY = 0;
        float sensorZ = 0;

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        switch (display.getRotation()){
            case Surface.ROTATION_0:
                sensorX = -event.values[0];
                sensorY = event.values[1];
                break;
            case Surface.ROTATION_90:
                sensorX = event.values[1];
                sensorY = event.values[0];
                break;
            case Surface.ROTATION_180:
                sensorX = -event.values[0];
                sensorY = -event.values[1];
                break;
            case Surface.ROTATION_270:
                sensorX = -event.values[1];
                sensorY = -event.values[0];
                break;
        }

        float[] valores = new float[3];
        valores[0] = sensorX;
        valores[1] = sensorY;
        valores[2] = event.values[2];

        return valores;
    }

    public static String getRotationString(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        switch (display.getRotation()){
            case Surface.ROTATION_0:
                return "ROTATION_0";
            case Surface.ROTATION_90:
                return "ROTATION_90";
            case Surface.ROTATION_180:
                return "ROTATION_180";
            case Surface.ROTATION_270:
                return "ROTATION_270";
        }

        return null;
    }
}
