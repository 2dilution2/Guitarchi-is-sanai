import React from 'react';
import { View, Text, Button, StyleSheet } from 'react-native';

const HomeScreen = ({ navigation }) => {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Guitarchi Is Sanai</Text>
      <Button
        title="로그인"
        onPress={() => navigation.navigate('LoginScreen')}
      />
      <Button
        title="회원가입"
        onPress={() => navigation.navigate('SignupScreen')}
        color="grey"
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  title: {
    fontSize: 24,
    marginBottom: 20,
  },
});

export default HomeScreen;
