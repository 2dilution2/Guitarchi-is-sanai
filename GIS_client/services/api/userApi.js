import axios from 'axios';

const API_URL = 'http://localhost:8080/api/users';

// API 인스턴스 생성
const api = axios.create({
  baseURL: API_URL,
  timeout: 10000,
});

// 회원가입 요청
export const signupUser = async (userData) => {
    try {
      const response = await api.post('/signup', userData);
      return response.data;
    } catch (error) {
      console.error('signupUser error:', error);
      throw error;
    }
  };
  
  // 로그인 요청
  export const loginUser = async (credentials) => {
    try {
      const response = await api.post('/login', credentials);
      return response.data;
    } catch (error) {
      console.error('loginUser error:', error);
      throw error;
    }
  };