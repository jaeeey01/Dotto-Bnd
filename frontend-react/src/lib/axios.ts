import axios, { AxiosInstance } from 'axios'

const baseURL = 'http://3.39.107.150:9002/api'

const instance: AxiosInstance = axios.create({
  baseURL,
  headers: {
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
  },
  timeout: 20000,
})

instance.interceptors.request.use((config) => {
  const token = window.sessionStorage.getItem('accessToken') ?? ''
  if (token.length) {
    config.headers!.Authorization = `Bearer ${token}`
    return config
  }
  return config
})

instance.interceptors.response.use(
  (response) => {
    // const { data } = response
    // const { result } = data
    return response
  },
  (error) => {
    const { response } = error
    const { status } = response

    if (status === 401) {
      console.log('401 ERROR')
    } else {
      console.log('OTHER ERROR')
    }
    console.log(response)
    return response
  }
)
export const ins = instance
