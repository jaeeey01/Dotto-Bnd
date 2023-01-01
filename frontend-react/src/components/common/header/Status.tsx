import Modal from '@/components/common/modal/Modal'
import React, { useEffect, useState } from 'react'
import { Login } from '@/components/login'
import { Register } from '@/components/register'
import Typography from '@/components/common/typography/Typography'
import ALARM from '@/assets/icons/default_alarm.svg'
import PROFILE from '@/assets/icons/default.svg'
import Image from '@/components/common/image/Image'
import { DropIcon } from '@/components/register/icon/DropIcon'
import { DESIGN, REVIEW, RESERVATION, FAVORITES } from '@/assets/icons/mymenu'

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
            <Image alt={'alarm'} src={ALARM} width={32} height={32} />
          </div>
          <div className={'flex items-center'}>
            <div className={'profile--wrapper pl-26'}>
              <Image alt={'profile'} src={PROFILE} width={40} height={40} />
              <Typography
                className={'pt-8'}
                variant={'caption'}
                fontColor={'gray1'}
              >
                profile
              </Typography>
            </div>
            <div>
              <DropIcon />
              <div className={'profile--dropdown'}>
                <ul>
                  <li className={'flex'}>
                    <Image
                      alt={'타투 도안'}
                      src={DESIGN}
                      width={16}
                      height={16}
                    />
                    <Typography
                      className={'pl-12'}
                      variant={'body2'}
                      fontColor={'gray1'}
                    >
                      타투도안
                    </Typography>
                  </li>
                  <li className={'flex'}>
                    <Image
                      alt={'에약 목록'}
                      src={RESERVATION}
                      width={16}
                      height={16}
                    />
                    <Typography
                      className={'pl-12'}
                      variant={'body2'}
                      fontColor={'gray1'}
                    >
                      내 예약목록
                    </Typography>
                  </li>
                  <li className={'flex'}>
                    <Image
                      alt={'내 댓글 / 리뷰'}
                      src={REVIEW}
                      width={16}
                      height={16}
                    />
                    <Typography
                      className={'pl-12'}
                      variant={'body2'}
                      fontColor={'gray1'}
                    >
                      내 댓글 / 리뷰
                    </Typography>
                  </li>
                  <li className={'flex'}>
                    <Image
                      alt={'찜한 목록'}
                      src={FAVORITES}
                      width={17}
                      height={17}
                    />
                    <Typography
                      className={'pl-12'}
                      variant={'body2'}
                      fontColor={'gray1'}
                    >
                      찜한 목록
                    </Typography>
                  </li>
                </ul>
                <hr />
                <ul>
                  <li>
                    <Typography variant={'body2'} fontColor={'gray1'}>
                      보안 / 계정
                    </Typography>
                  </li>
                  <li>
                    <Typography variant={'body2'} fontColor={'gray1'}>
                      로그아웃
                    </Typography>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </section>
      ) : (
        // <section className="button--wrapper">
        //   <button type={'button'} onClick={logout}>
        //     <Typography variant={'caption'} fontColor={'gray1'}>
        //       Logout
        //     </Typography>
        //   </button>
        // </section>
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
