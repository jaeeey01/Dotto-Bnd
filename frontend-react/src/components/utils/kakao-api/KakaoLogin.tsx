import { OAuth } from '@/lib/utils/OAuth/OAuth'
import { KAKAO } from '@/assets/icons/social'
import React from 'react'

export const KakaoLogin = () => {
  const kakaoLogin = async () => {
    try {
      const url = `https://kauth.kakao.com/oauth/authorize?client_id=${OAuth.REST_API_KEY}&redirect_uri=${OAuth.REDIRECT_URI}&response_type=code`
      window.open(url, '', 'width: auto; height: auto')
    } catch (e) {
      console.log(e)
    }
  }

  return (
    <section className="login__items--wrapper">
      <button type="button" className="kakao__button" onClick={kakaoLogin}>
        <img
          alt="카카오 로그인 이미지"
          className={'ml-20'}
          src={KAKAO}
          width={24}
          height={24}
        />
        <span>카카오 계정으로 로그인</span>
      </button>
    </section>
  )
}
