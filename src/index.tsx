import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-incall-manager-light' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const IncallManagerLightModule = NativeModules.IncallManagerLight
  ? NativeModules.IncallManagerLight
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

const IncallManagerLight = (() => {
  const isSpeakerphoneOn = (): boolean => {
    return IncallManagerLightModule.isSpeakerphoneOn();
  };

  const setSpeakerPhoneOn = (enable: boolean): void => {
    IncallManagerLightModule.setSpeakerPhoneOn(enable);
  };

  const enableProximity = (): void => {
    IncallManagerLightModule.enableProximity();
  };

  const disableProximity = (): void => {
    IncallManagerLightModule.disableProximity();
  };

  const handleHeadsetMicrophone = (enable: boolean): void => {
    if (Platform.OS !== 'android') {
      return;
    }

    IncallManagerLightModule.handleHeadsetMicrophone(enable);
  };

  return {
    isSpeakerphoneOn,
    setSpeakerPhoneOn,
    enableProximity,
    disableProximity,
    handleHeadsetMicrophone,
  };
})();

export default IncallManagerLight;
