import { useEffect } from 'react'
import { useLocation } from 'react-router'
import Typography from '@/components/common/typography/Typography'
import { Link } from 'react-router-dom'
import Image from '@/components/common/image/Image'
import { UserLists } from '@/components/search/UserLists'

export const UserResults = () => {
  const location = useLocation()

  useEffect(() => {
    const { search } = location
    console.log(search)
  }, [location])

  return (
    <article className={'search-result__body'}>
      <section className={'flex'}>
        <Typography variant={'sub1'} fontColor={'gray1'}>
          검색결과
        </Typography>
        <Typography variant={'sub2'} fontColor={'gray3'}>
          18,000
        </Typography>
      </section>
      <UserLists />
    </article>
  )
}
