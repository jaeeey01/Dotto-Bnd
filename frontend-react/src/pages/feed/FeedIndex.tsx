import { FeedHeader } from '@/components/feed/FeedHeader'
import { Footer } from '@/components/common/footer/Footer'
import { FeedLists } from '@/components/feed/FeedLists'
import { RecommendedUser } from '@/components/feed/RecommendedUser'

export const FeedIndex = () => {
  return (
    <>
      <FeedHeader />
      {/*<Outlet />*/}
      <main>
        <FeedLists />
        <RecommendedUser />
      </main>
      <Footer />
    </>
  )
}
