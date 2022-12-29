import { UserPreview } from '@/components/user/UserPreview'
import { BoardLabel } from '@/components/board/BoardLabel'
import PostList from '@/components/board/PostList'
import { usePostList } from '@/lib/hooks/usePostList'
import { useEffect, useState } from 'react'
import { ins as axios } from '@/lib/axios'
import { useLocation } from 'react-router'
import qs from 'qs'

export const SearchIndex = () => {
  const location = useLocation()
  const query = qs.parse(location.search, { ignoreQueryPrefix: true })
  const [keyword, setKeyword] = useState<string>('')
  const { postList } = usePostList()
  const resultPostList = postList.filter(() => true).sort()

  useEffect(() => {
    const { keyword: queryKeyword } = query
    if (queryKeyword) setKeyword(queryKeyword.toString())

    const load = async () => {
      console.log(keyword)
      const { data } = await axios.get(`/search/${keyword}`)
      console.log(data)
    }
    load()
  }, [location])

  return (
    <>
      <div className={'search-result__body'}>
        <section className={'search-result__body--group '}>
          <UserPreview type={'artist'} label={'아티스트'} />
        </section>
        <section className={'search-result__body--group'}>
          <UserPreview type={'user'} label={'회원'} />
        </section>
        <section className={'search-result__body--group'}>
          <BoardLabel
            title={'닷투 게시판'}
            subTitle={'타투이스트가 올린 다양한 작품 중 내 취향을 찾아보세요!'}
            type={'dotto'}
          />
          <PostList list={resultPostList} />
        </section>
        <section className={'search-result__body--group'}>
          <BoardLabel
            title={'닷찾사 게시판'}
            subTitle={
              '내가 원하는 도안을 제시하고 타투이스트에게 답변을 받아보세요!'
            }
            type={'etc'}
          />
          <PostList list={resultPostList} />
        </section>
      </div>
    </>
  )
}
