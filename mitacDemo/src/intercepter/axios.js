import axios from "axios";

axios.defaults.withCredentials = true;
axios.defaults.headers.post['Content-Type'] = 'application/json';
axios.defaults.headers.put['Content-Type'] = 'application/json';
axios.interceptors.request.use(
  config => {
    let token = localStorage.getItem('token');
    if (token){
      config.headers.Authorization = token;
    }
    return config;
  },
  error => {
    return Promise.reject(error)
  }
);
axios.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    return Promise.reject(error);
  }
)
export default function () {
  return axios;
}
