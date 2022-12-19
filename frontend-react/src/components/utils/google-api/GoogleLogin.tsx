import { GOOGLE } from '@/assets/icons/social'
import React from 'react'

export const GoogleLogin = () => {
  const googleLogin = () => {
    console.log('롸')
  }
  return (
    <section className="login__items--wrapper mt-12">
      <button type="button" className="google__button" onClick={googleLogin}>
        <img
          alt="구글 로그인 이미지"
          src={GOOGLE}
          className={'ml-20'}
          width={24}
          height={24}
        />
        <span>구글 계정으로 로그인</span>
      </button>
    </section>
  )
}
