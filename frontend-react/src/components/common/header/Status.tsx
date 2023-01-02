import Modal from '@/components/common/modal/Modal'
import React, { useEffect, useState } from 'react'
import { Login } from '@/components/login'
import { Register } from '@/components/register'
import Typography from '@/components/common/typography/Typography'
import ALARM from '@/assets/icons/default_alarm.svg'
import PROFILE from '@/assets/icons/default.svg'
import Image from '@/components/common/image/Image'
import { DropMenu } from '@/components/common/header/DropMenu'

export const Status = () => {
  const [open, setOpen] = useState(false)
  const [isLogin, setIsLogin] = useState(false)
  const [componentType, setComponentType] = useState('login')
  const getToken = window.sessionStorage.getItem('accessToken')

  useEffect(() => {
    if (getToken) setIsLogin(true)
    else setIsLogin(false)
  }, [getToken])

  const openModal = () => {
    setOpen(true)
    scrollFixed()
  }

  const scrollFixed = () => {
    const body = document.querySelector('body')
    if (body)
      !open ? (body.style.overflow = 'hidden') : (body.style.overflow = '')
  }

  const closeModal = (payload: boolean) => {
    setOpen(payload)
    modalRest()
    scrollFixed()
  }

  const modalRest = () => {
    setComponentType('login')
  }

  const logout = () => {
    window.sessionStorage.clear()
  }

  const components = (type: string) => {
    setComponentType(type)
  }

  const style = {
    loginForm: {
      width: '520px',
      height: '630px',
    } as React.CSSProperties,
    registerForm: {
      width: '680px',
      height: '700px',
    } as React.CSSProperties,
  }

  return (
    <article className="nav-status-container">
      {!isLogin ? (
        <section className="profile-box pr-40">
          <div>
            <Image alt={'alarm'} src={ALARM} width={30} height={30} />
            <div className={'notification scroll scroll-type'}>
              <ul>
                <Typography
                  className={'notification-date'}
                  fontColor={'gray1'}
                  variant={'body1'}
                  fontWeight={'medium'}
                >
                  오늘
                </Typography>
                <li className={'flex notification-list'}>
                  <Image alt={'profile'} src={PROFILE} width={38} height={38} />
                  <div className={'pl-12'}>
                    <Typography>
                      <span>User Name</span> 님이 댓글을 남기셨습니다.
                    </Typography>
                    <Typography
                      variant={'caption'}
                      fontWeight={'medium'}
                      fontColor={'gray3'}
                    >
                      18분 전
                    </Typography>
                  </div>
                </li>
                <li className={'flex notification-list'}>
                  <Image alt={'profile'} src={PROFILE} width={38} height={38} />
                  <div className={'pl-12'}>
                    <Typography>
                      <span>User Name</span> 님이 댓글을 남기셨습니다.
                    </Typography>
                    <Typography
                      variant={'caption'}
                      fontWeight={'medium'}
                      fontColor={'gray3'}
                    >
                      18분 전
                    </Typography>
                  </div>
                </li>
              </ul>
              <hr />
              <ul>
                <Typography
                  className={'notification-date'}
                  fontColor={'gray1'}
                  variant={'body1'}
                  fontWeight={'medium'}
                >
                  이번주
                </Typography>
                <li className={'flex notification-list'}>
                  <Image alt={'profile'} src={PROFILE} width={38} height={38} />
                  <div className={'pl-12'}>
                    <Typography>
                      <span>User Name</span> 님이 댓글을 남기셨습니다.
                    </Typography>
                    <Typography
                      variant={'caption'}
                      fontWeight={'medium'}
                      fontColor={'gray3'}
                    >
                      18분 전
                    </Typography>
                  </div>
                </li>
                <li className={'flex notification-list'}>
                  <Image alt={'profile'} src={PROFILE} width={38} height={38} />
                  <div className={'pl-12'}>
                    <Typography>
                      <span>User Name</span> 님이 댓글을 남기셨습니다.
                    </Typography>
                    <Typography
                      variant={'caption'}
                      fontWeight={'medium'}
                      fontColor={'gray3'}
                    >
                      18분 전
                    </Typography>
                  </div>
                </li>
              </ul>
            </div>
          </div>
          <div className={'flex items-center'}>
            <div className={'profile--wrapper pl-26'}>
              <Image alt={'profile'} src={PROFILE} width={38} height={38} />
              <Typography
                className={'pt-8'}
                variant={'caption'}
                fontColor={'gray1'}
              >
                profile
              </Typography>
            </div>
            <DropMenu />
          </div>
        </section>
      ) : (
        <section className="button--wrapper">
          <button type={'button'} onClick={openModal}>
            <Typography variant={'caption'} fontColor={'gray1'}>
              Login
            </Typography>
          </button>
        </section>
      )}
      <Modal
        cardStyle={
          componentType === 'login' ? style.loginForm : style.registerForm
        }
        isOpen={open}
        onRequestClose={() => closeModal(false)}
      >
        {componentType === 'login' && (
          <Login closeModal={closeModal} changeComponent={components} />
        )}
        {componentType === 'register' && (
          <Register changeComponent={components} />
        )}
      </Modal>
    </article>
  )
}
