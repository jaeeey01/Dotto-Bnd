import React, { useEffect, useRef, useState } from 'react'
import LOGO from '@/assets/icons/logo/dotto.svg'
import SEARCH from '@/assets/icons/nav/search.svg'
import CLOSE from '@/assets/icons/nav/x-button.svg'
import { Link, useNavigate } from 'react-router-dom'
import Typography from '@/components/common/typography/Typography'
import cn from 'classnames'
import { useCookies } from 'react-cookie'

export namespace IEvent {
  export type handleClick = React.MouseEvent<HTMLElement>
  export type handleKeyPressEnter = React.KeyboardEvent<HTMLInputElement>
}

export const Top = () => {
  const [cookies, setCookie, removeCookie] = useCookies(['keyword'])
  const [showSearch, setShowSearch] = useState(false)
  const [keyword, setKeyword] = useState('')
  const [keywordList, setKeywordList] = useState<Array<string>>([])
  const navigate = useNavigate()
  const ele = useRef<any>(null)
  const date = new Date()
  const expires1Month = new Date(date.getMonth() + 1)

  useEffect(() => {
    getKeywordList()
  }, [keyword])

  const getKeywordList = () => {
    try {
      const getKeywordLists = cookies['keyword']
      if (getKeywordLists !== undefined && getKeywordLists !== null) {
        let keywordLists: string[] = []
        if (typeof getKeywordLists === 'string')
          keywordLists = getKeywordLists.split(',')
        else keywordLists = getKeywordLists
        setKeywordList([...keywordLists])
      }
    } catch (e) {
      console.log(e)
    }
  }

  const onChangeKeyword = (e: { target: HTMLInputElement }) => {
    const { value } = e.target
    setKeyword(value)
  }

  const handleClickSearchBar = () => {
    if (!showSearch) setShowSearch(!showSearch)
  }
  const searchBarClose = () => {
    setShowSearch(!showSearch)
  }
  const searchBarCloseOuter = (e: React.MouseEvent) => {
    const { target } = e
    if (ele && !ele.current.contains(target)) {
      if (showSearch) searchBarClose()
    }
  }

  const setCookies = (keyword: string[]) => {
    setCookie('keyword', `${keyword}`, { path: '/', expires: expires1Month })
  }

  const search = (e: React.FormEvent) => {
    e.preventDefault()
    if (keyword.length > 0) {
      if (keywordList.length >= 6)
        setKeywordList([...keywordList.slice(1), keyword])
      else setKeywordList([...keywordList, keyword])
      setCookies(keywordList)
    }
    navigate(`/dotto/search/result/${keyword}`)
    setShowSearch(!showSearch)
  }

  const keywordClear = () => {
    removeCookie('keyword')
    setKeywordList([])
  }

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
        {showSearch && (
          <article className="keyword-container" onClick={searchBarCloseOuter}>
            <article className={'list-group'} ref={ele}>
              <section className="keyword-container--list">
                {keywordList.length > 0 ? (
                  <section>
                    <div className={'flex justify-between'}>
                      <Typography
                        variant={'body1'}
                        fontColor={'gray4'}
                        fontWeight={'medium'}
                      >
                        최근 검색어
                      </Typography>
                      <button type={'button'} onClick={keywordClear}>
                        <Typography
                          variant={'body1'}
                          fontColor={'gray4'}
                          fontWeight={'medium'}
                        >
                          모두지우기
                        </Typography>
                      </button>
                    </div>
                    <ul className={'search-list'}>
                      {keywordList.map((item, index) => {
                        return (
                          <li key={index}>
                            <Typography variant={'sub2'} fontWeight={'medium'}>
                              {item}
                            </Typography>
                          </li>
                        )
                      })}
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
        )}
      </form>
      {showSearch && (
        <section className="search-bar__button--close">
          <button type="button" onClick={searchBarClose}>
            <img alt="검색 닫기" src={CLOSE} width={18} height={18} />
          </button>
        </section>
      )}
    </article>
  )
}
