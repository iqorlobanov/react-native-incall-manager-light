package com.incallmanagerlight;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

public class MicrophoneManager {
  private final AudioManager audioManager;

  public MicrophoneManager(Context context) {
    this.audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
  }

  public void enableHeadsetMicro() {
    try {
      audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
      if(audioManager.isBluetoothScoAvailableOffCall()) {
        audioManager.startBluetoothSco();
        audioManager.setBluetoothScoOn(true);
      }
    } catch (Exception e){
      e.printStackTrace();
    }

  }

  public void disableHeadsetMicro() {
    try {
      audioManager.setMode(AudioManager.MODE_NORMAL);
      if(audioManager.isBluetoothScoAvailableOffCall()) {
        audioManager.stopBluetoothSco();
        audioManager.setBluetoothScoOn(false);
      }
    } catch (Exception e){
      e.printStackTrace();
    }
  }

//  @ReactMethod(isBlockingSynchronousMethod = true)
//  public void handleHeadsetMicro(boolean enable) {
//    if(enable) {
//      enableHeadsetMicro();
//    } else {
//      disableHeadsetMicro();
//    }
//  }
}
