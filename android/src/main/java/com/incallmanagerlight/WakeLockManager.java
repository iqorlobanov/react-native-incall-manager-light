package com.incallmanagerlight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;

public class WakeLockManager {

  private PowerManager mPowerManager;

  private PowerManager.WakeLock wakeLock;
  private boolean isWakeLocked = false;

  @SuppressLint("InvalidWakeLockTag")
  public WakeLockManager(Context reactContext) {
    mPowerManager = (PowerManager) reactContext.getSystemService(Context.POWER_SERVICE);
    wakeLock = mPowerManager.newWakeLock(PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK, "WakeLockManager");
  }

  public void setWakeLock() {
    if (isWakeLocked) {
      return;
    }

    wakeLock.acquire();
    isWakeLocked = true;
  }

  public void releaseWakeLock() {
    if (!isWakeLocked) {
      return;
    }

    wakeLock.release();
    isWakeLocked = false;
  }

  public boolean isWakeLocked() {
    return isWakeLocked;
  }
}
