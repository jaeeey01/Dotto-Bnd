import { FeedHeader } from '@/components/feed/FeedHeader'
import { Footer } from '@/components/common/footer/Footer'
import { FeedLists } from '@/components/feed/FeedLists'
import { RecommendedUser } from '@/components/feed/RecommendedUser'
import style from '@/pages/feed/FeedIndex.module.scss'
import { TopButton } from '@/components/button/TopButton'
import { FeedPostButton } from '@/components/feed/FeedPostButton'

export const FeedIndex = () => {
  return (
    <>
      <FeedHeader />
      {/*<Outlet />*/}
      <main className={style.feed__main}>
        <FeedLists />
        <RecommendedUser />
        <FeedPostButton />
        <TopButton />
      </main>
      <Footer />
    </>
  )
}
