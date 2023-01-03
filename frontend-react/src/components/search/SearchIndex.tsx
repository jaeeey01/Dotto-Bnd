import { UserPreview } from '@/components/user/UserPreview'
import { BoardLabel } from '@/components/board/BoardLabel'
import PostList from '@/components/board/PostList'
import { usePostList } from '@/lib/hooks/usePostList'
import { useEffect, useState } from 'react'
import { ins as axios } from '@/lib/axios'
import { useLocation } from 'react-router'
import qs from 'qs'
import { LABEL } from '@/constants/placeholders'

export const SearchIndex = () => {
  const location = useLocation()
  const query = qs.parse(location.search, { ignoreQueryPrefix: true })
  const [keyword, setKeyword] = useState<string>('')
  const { postList } = usePostList()
  const resultPostList = postList.filter(() => true).sort()

  useEffect(() => {
    const { keyword: queryKeyword } = query
    if (queryKeyword) setKeyword(queryKeyword as string)

    const load = async () => {
      const { data } = await axios.get(`/search/${keyword}`)
      console.log(data)
    }
    load()
  }, [location])

  return (
    <>
      <div className={'search-result__body'}>
        <section className={'search-result__body--group '}>
          <UserPreview type={'artist'} label={LABEL.ARTIST} />
        </section>
        <section className={'search-result__body--group'}>
          <UserPreview type={'user'} label={LABEL.USER} />
        </section>
        <section className={'search-result__body--group'}>
          <BoardLabel
            title={LABEL.DOTTO_TITLE}
            subTitle={LABEL.DOTTO_SUB_TITLE}
            type={'dotto'}
          />
          <PostList list={resultPostList} />
        </section>
        <section className={'search-result__body--group'}>
          <BoardLabel
            title={LABEL.BOARD_TITLE}
            subTitle={LABEL.BOARD_SUB_TITLE}
            type={'etc'}
          />
          <PostList list={resultPostList} />
        </section>
      </div>
    </>
  )
}
