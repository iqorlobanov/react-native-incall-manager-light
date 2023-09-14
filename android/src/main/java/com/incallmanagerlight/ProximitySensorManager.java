package com.incallmanagerlight;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class ProximitySensorManager implements SensorEventListener {
  private static final String KEY_PROXIMITY = "proximity";
  private static final String KEY_DISTANCE = "distance";
  private static final String EVENT_ON_SENSOR_CHANGE = "onSensorChanged";
  private final Context reactContext;
  private final SensorManager mSensorManager;
  private final Sensor mProximity;
  private final WakeLockManager wakeLockManager;

  public ProximitySensorManager(Context context) {
    reactContext = context;
    wakeLockManager = new WakeLockManager(context);
    mSensorManager = (SensorManager) reactContext.getSystemService(Context.SENSOR_SERVICE);
    mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
  }

  private boolean listen = false;

  public void addListener() {
    if (!listen) {
      // Set up any upstream listeners or background tasks as necessary
      mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
      listen = true;
    }
  }

  public void removeListener() {
    if (listen) {
      // Remove upstream listeners, stop unnecessary background tasks
      mSensorManager.unregisterListener(this);
      listen = false;
    }
  }

  @Override
  public void onSensorChanged(SensorEvent sensorEvent) {

    double distance = sensorEvent.values[0];
    double maximumRange = mProximity.getMaximumRange();
    boolean isNearDevice = distance < maximumRange;

    if(isNearDevice) {
      wakeLockManager.setWakeLock();
    } else {
      wakeLockManager.releaseWakeLock();
    }
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int i) {

  }
}
