import React, { useState } from 'react';
import { View, TextInput, Button, StyleSheet, Alert } from 'react-native';
import { loginUser } from '../../services/api/userApi';

const LoginScreen = ({ navigation }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = async () => {
    try {
      const data = await loginUser({ email, password });
      console.log(data);
      navigation.navigate('Home');
    } catch (error) {
      console.error(error);
      Alert.alert("로그인 실패", "로그인 중 문제가 발생했습니다.");
    }
  };

  return (
    <View style={styles.container}>
      <TextInput
        placeholder="Email"
        value={email}
        onChangeText={setEmail}
        style={styles.input}
      />
      <TextInput
        placeholder="Password"
        value={password}
        onChangeText={setPassword}
        secureTextEntry
        style={styles.input}
      />
      <Button title="로그인" onPress={handleLogin} />
      <Button
        title="회원가입"
        onPress={() => navigation.navigate('Signup')}
        color="grey"
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    padding: 20,
    justifyContent: 'center',
    alignItems: 'stretch',
  },
  input: {
    marginBottom: 10,
    borderWidth: 1,
    padding: 10,
  },
});

export default LoginScreen;
