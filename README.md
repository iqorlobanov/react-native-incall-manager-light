# react-native-incall-manager-light

Incall manager for ReactNative

## Installation

```sh
npm install react-native-incall-manager-light
```

```sh
yarn add react-native-incall-manager-light
```

## Android
Add `MODIFY_AUDIO_SETTINGS` and `WAKE_LOCK` permissions to `AndroidManifest.xml`
```sh
// ...
<uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS" />
<uses-permission android:name="android.permission.WAKE_LOCK" />
```


## Usage

```js
import IncallManagerLight from 'react-native-incall-manager-light';

// ...

IncallManagerLight.enableProximity();
```

## Methods

| method                              | description                                                                    |
| ----------------------------------- | ------------------------------------------------------------------------------ |
| `isSpeakerphoneOn()`                | Return `boolean` value                                                         |
| `setSpeakerPhoneOn(enable: boolean)`| Set speakerphone status                                                        |
| `enableProximity()`                 | Enables the screen to turn off when an object approaches the proximity sensor  |
| `disableProximity()`                | Вшыфидуы the screen to turn off when an object approaches the proximity sensor |

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
