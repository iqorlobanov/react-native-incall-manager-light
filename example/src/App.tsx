import * as React from 'react';
import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';
import IncallManagerLight from 'react-native-incall-manager-light';

export default function App() {
  const [speakerEnabled, setSpeakerEnabled] = React.useState(
    IncallManagerLight.isSpeakerphoneOn()
  );

  React.useEffect(() => {
    console.log('speakerEnabled', IncallManagerLight.isSpeakerphoneOn());
  }, [speakerEnabled]);

  return (
    <View style={styles.container}>
      <TouchableOpacity
        style={styles.button}
        onPress={() => {
          setSpeakerEnabled((prev) => {
            IncallManagerLight.setSpeakerPhoneOn(!prev);
            return !prev;
          });
        }}
      >
        <Text style={styles.text}>{`Speaker enabled: ${speakerEnabled}`}</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.button}
        onPress={() => {
          IncallManagerLight.enableProximity();
        }}
      >
        <Text style={styles.text}>{`Start proximity`}</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.button}
        onPress={() => {
          IncallManagerLight.disableProximity();
        }}
      >
        <Text style={styles.text}>{`Stop proximity`}</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'black',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
  button: {
    marginBottom: 10,
    height: 50,
    width: 200,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'white',
  },
  text: {
    color: 'black',
  },
});
