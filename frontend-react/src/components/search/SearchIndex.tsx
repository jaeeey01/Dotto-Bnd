import { UserPreview } from '@/components/user/UserPreview'
import { BoardLabel } from '@/components/board/BoardLabel'
import PostList from '@/components/board/PostList'
import { useEffect, useState } from 'react'
// import { ins } from '@/lib/axios'
import { useLocation } from 'react-router'
import qs from 'qs'
import { LABEL } from '@/constants/placeholders'
import axios from 'axios'

export const SearchIndex = () => {
  const location = useLocation()
  const query = qs.parse(location.search, { ignoreQueryPrefix: true })
  const [keyword, setKeyword] = useState<string>('')

  useEffect(() => {
    const { keyword: queryKeyword } = query
    if (queryKeyword) setKeyword(queryKeyword as string)

    const load = async () => {
      const { data } = await axios.get(`/search/${keyword}`)
      console.log(data)
    }
    load()
  }, [location])

  const api = async () => {
    try {
      const { data } = await axios.get('http://3.39.107.150/api/policy')
      console.log(data, '???????????????')
    } catch (e) {
      console.log()
    }
  }

  return (
    <>
      <div className={'search-result__body'}>
        <section className={'search-result__body--group '}>
          <UserPreview type={'artist'} label={LABEL.ARTIST} />
        </section>
        <button type={'button'} onClick={api}>
          API TEST
        </button>
        <section className={'search-result__body--group'}>
          <UserPreview type={'user'} label={LABEL.USER} />
        </section>
        <section className={'search-result__body--group'}>
          <BoardLabel
            title={LABEL.DOTTO_TITLE}
            subTitle={LABEL.DOTTO_SUB_TITLE}
            type={'home'}
          />
          <PostList list={undefined} />
        </section>
        <section className={'search-result__body--group'}>
          <BoardLabel
            title={LABEL.BOARD_TITLE}
            subTitle={LABEL.BOARD_SUB_TITLE}
            type={'etc'}
          />
          <PostList list={undefined} />
        </section>
      </div>
    </>
  )
}
