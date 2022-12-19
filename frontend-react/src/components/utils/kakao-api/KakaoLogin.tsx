import { OAuth } from '@/lib/utils/OAuth/OAuth'
import { KAKAO } from '@/assets/icons/social'
import React, { useCallback } from 'react'

export const KakaoLogin = () => {
  const url = `https://kauth.kakao.com/oauth/authorize?client_id=${OAuth.REST_API_KEY}&redirect_uri=${OAuth.REDIRECT_URI}&response_type=code`

  const kakaoLogin = useCallback(() => popupOpen(url), [url])

  const popupOpen = (url: string) => {
    const width = 425
    const height = 625
    const left = Math.ceil((window.screen.width - width) / 2)
    const top = Math.ceil((window.screen.height - height) / 2)

    window.open(
      url,
      '',
      `left=${left}, top=${top}, toolbar=no, width=${width}, height=${height}`
    )
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
