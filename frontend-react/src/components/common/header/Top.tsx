import React, { useEffect, useRef, useState } from 'react'
import LOGO from '@/assets/icons/logo/dotto.svg'
import SEARCH from '@/assets/icons/nav/search.svg'
import CLOSE from '@/assets/icons/nav/x-button.svg'
import { Link, useNavigate } from 'react-router-dom'
import Typography from '@/components/common/typography/Typography'
import cn from 'classnames'
import { useCookies } from 'react-cookie'
import { SearchForm } from '@/components/search/SearchForm'

export namespace IEvent {
  export type handleClick = React.MouseEvent<HTMLElement>
  export type handleKeyPressEnter = React.KeyboardEvent<HTMLInputElement>
}

export const Top = () => {
  const [showSearch, setShowSearch] = useState(false)
  useEffect(() => {
    scrollFixed()
  }, [showSearch])

  const closeSearchBar = (payload: boolean) => {
    setShowSearch(payload)
  }

  const scrollFixed = () => {
    const body = document.querySelector('body')
    if (body) {
      if (showSearch) body.style.overflow = 'hidden'
      else body.style.overflow = ''
    }
  }

  return (
    <article className="top-container">
      <section className="logo-wrapper">
        <Link to="/">
          <img width={103} height={40} alt="닷투 로고" src={LOGO} />
        </Link>
      </section>

      <SearchForm showSearch={showSearch} closeSearchBar={closeSearchBar} />

      {showSearch && (
        <section className="search-bar__button--close">
          <button type="button" onClick={() => closeSearchBar(!showSearch)}>
            <img alt="검색 닫기" src={CLOSE} width={18} height={18} />
          </button>
        </section>
      )}
    </article>
  )
}
