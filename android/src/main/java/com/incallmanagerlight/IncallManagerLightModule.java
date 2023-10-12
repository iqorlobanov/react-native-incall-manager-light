package com.incallmanagerlight;

import androidx.annotation.NonNull;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = IncallManagerLightModule.NAME)
public class IncallManagerLightModule extends ReactContextBaseJavaModule {
  public static final String NAME = "IncallManagerLight";
  private final SpeakerPhoneManager speakerPhoneManager;
  private final ProximitySensorManager proximitySensorManager;
  private final MicrophoneManager microphoneManager;

  public IncallManagerLightModule(ReactApplicationContext reactContext) {
    super(reactContext);
    speakerPhoneManager = new SpeakerPhoneManager(reactContext);
    proximitySensorManager = new ProximitySensorManager(reactContext);
    microphoneManager = new MicrophoneManager(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @ReactMethod(isBlockingSynchronousMethod = true)
  public boolean isSpeakerphoneOn() {
    return speakerPhoneManager.isSpeakerphoneOn();
  }

  @ReactMethod
  public void setSpeakerPhoneOn(boolean enable) {
    speakerPhoneManager.setSpeakerPhoneOn(enable);
  }

  @ReactMethod
  public void enableProximity() {
    proximitySensorManager.addListener();
  }
  @ReactMethod
  public void disableProximity() {
    proximitySensorManager.removeListener();
  }
  @ReactMethod()
  public void handleHeadsetMicrophone(boolean enable) {
    if(enable) {
      microphoneManager.enableHeadsetMicro();
    } else {
      microphoneManager.disableHeadsetMicro();
    }
  }
}
