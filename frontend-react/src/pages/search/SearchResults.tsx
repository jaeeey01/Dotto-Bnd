import '@/pages/search/SearchResults.scss'
import { SideMenu } from '@/components/search/SideMenu'
import { useParams } from 'react-router'
import { useEffect, useState } from 'react'
import { BoardLabel } from '@/components/board/BoardLabel'
import { UserPreview } from '@/components/user/UserPreview'
import PostList from '@/components/board/PostList'

export const SearchResults = () => {
  const [keyword, setKeyword] = useState('')
  const params = useParams()

  useEffect(() => {
    const { keyword: param } = params
    if (param) setKeyword(param)
  }, [keyword])

  return (
    <main className={'search-result--container'}>
      <SideMenu />
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
          <PostList list={undefined} />
        </section>
        <section className={'search-result__body--group'}>
          <BoardLabel
            title={'닷찾사 게시판'}
            subTitle={
              '내가 원하는 도안을 제시하고 타투이스트에게 답변을 받아보세요!'
            }
            type={'etc'}
          />
          <PostList list={undefined} />
        </section>
      </div>
    </main>
  )
}
