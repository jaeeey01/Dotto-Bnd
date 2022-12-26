import React, { useEffect } from 'react'
import { gapi } from 'gapi-script'
import GoogleLogin, {
  GoogleLoginResponse,
  GoogleLoginResponseOffline,
} from 'react-google-login'

const clientId = process.env.REACT_APP_GOOGLE_CLIENT_ID

interface GoogleLoginFailure {
  error: string
}

export const GoogleLoginButton = () => {
  useEffect(() => {
    gapi.load('client:auth2', googleInit)
  }, [])

  const googleInit = () => {
    gapi.client.init({
      clientId,
      scope: 'email',
    })
  }

  const onSuccess = async (
    response: GoogleLoginResponse | GoogleLoginResponseOffline
  ): Promise<void> => {
    console.log('성공', response)
  }
  const onFailure = (response: GoogleLoginFailure) => {
    console.log('실패', response)
  }
  return (
    <section className="login__items--wrapper mt-12">
      <GoogleLogin
        clientId={clientId || ''}
        buttonText={'구글 계정으로 로그인'}
        onSuccess={onSuccess}
        onFailure={onFailure}
        className="google__button"
      />
    </section>
  )
}
