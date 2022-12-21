import React, { useState } from 'react'
import LOGO from '@/assets/icons/logo/dotto.svg'
import SEARCH from '@/assets/icons/nav/search.svg'
import CLOSE from '@/assets/icons/nav/x-button.svg'
import { Link } from 'react-router-dom'
import Typography from '@/components/common/typography/Typography'
import cn from 'classnames'

export namespace IEvent {
  export type handleClick = React.MouseEvent<HTMLElement>
  export type handleKeyPressEnter = React.KeyboardEvent<HTMLInputElement>
}

export const Top = () => {
  const [showSearch, setShowSearch] = useState(false)
  const [keyword, setKeyword] = useState('')
  const [testArr, setTestArr] = useState([])
  const onChangeKeyword = (e: { target: HTMLInputElement }) => {
    const { value } = e.target
    setKeyword(value)
  }

  const handleClickSearchBar = (e: IEvent.handleClick) => {
    setShowSearch(!showSearch)
  }
  // 검색 추가
  const search = () => {}

  return (
    <article className="top-container">
      <section className="logo-wrapper">
        <Link to="/">
          <img width={103} height={40} alt="닷투 로고" src={LOGO} />
        </Link>
      </section>

      <form className="search-bar--container">
        <input
          type="text"
          placeholder="Search"
          className={cn(showSearch ? 'search-bar--active' : '', 'search-bar')}
          value={keyword}
          name="keyword"
          onClick={handleClickSearchBar}
          onChange={onChangeKeyword}
          autoComplete="search"
        />
        <button type="submit" className="search__button" onClick={search}>
          <img alt="검색 버튼" src={SEARCH} width={20} height={20} />
        </button>
        {showSearch ? (
          <article className="keyword-container">
            <article className={'list-group'}>
              <section className="keyword-container--list">
                {testArr.length > 0 ? (
                  <section>
                    <Typography>최근 검색어</Typography>
                    <button type={'button'}>모두지우기</button>
                    <ul>
                      <li>TEST 2022.11.26</li>
                      <li>TEST2 2022.11.26</li>
                    </ul>
                  </section>
                ) : (
                  <Typography
                    className={'no-keyword--text'}
                    fontColor={'gray2'}
                    variant={'body1'}
                  >
                    검색된 검색어가 존재하지 않습니다.
                  </Typography>
                )}
              </section>
            </article>
          </article>
        ) : (
          ''
        )}
      </form>
      {showSearch ? (
        <section className="search-bar__button--close">
          <button type="button" onClick={handleClickSearchBar}>
            <img alt="검색 닫기" src={CLOSE} width={18} height={18} />
          </button>
        </section>
      ) : (
        ''
      )}
    </article>
  )
}
