import Modal from '@/components/common/modal/Modal'
import React, { useEffect, useState } from 'react'
import { Login } from '@/components/login'
import { Register } from '@/components/register'
import Typography from '@/components/common/typography/Typography'

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
      {isLogin ? (
        <section className="button--wrapper">
          <button type={'button'} onClick={logout}>
            <Typography variant={'caption'} fontColor={'gray1'}>
              Logout
            </Typography>
          </button>
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
