import '@/pages/search/SearchResults.scss'
import { SideMenu } from '@/components/search/SideMenu'
import { useParams } from 'react-router'
import { useEffect, useState } from 'react'
import PostList from '@/components/board/PostList'
import { usePostList } from '@/lib/hooks/usePostList'
import { BoardLabel } from '@/components/board/BoardLabel'

export const SearchResults = () => {
  const [keyword, setKeyword] = useState('')
  const params = useParams()

  const { postList } = usePostList()
  const resultPostList = postList.filter(() => true).sort()

  useEffect(() => {
    const { keyword: param } = params
    if (param) setKeyword(param)
  }, [keyword])

  return (
    <main className={'search-result--container'}>
      <SideMenu />
      <section className={'search-result__body'}>
        <div className={'search-result__body--group'}>
          <BoardLabel
            title={'닷투 게시판'}
            subTitle={'타투이스트가 올린 다양한 작품 중 내 취향을 찾아보세요!'}
            type={'dotto'}
          />
          <PostList list={resultPostList} />
        </div>
      </section>
    </main>
  )
}
