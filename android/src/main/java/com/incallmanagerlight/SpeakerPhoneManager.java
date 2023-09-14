package com.incallmanagerlight;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

public class SpeakerPhoneManager {

  private final AudioManager audioManager;

  public SpeakerPhoneManager(Context context) {
    this.audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
  }

  public boolean isSpeakerphoneOn() {
    return audioManager.isSpeakerphoneOn();
  }

  public void setSpeakerPhoneOn(Boolean isSpeakerPhoneOn) {
    audioManager.setSpeakerphoneOn(isSpeakerPhoneOn);
  }
}
