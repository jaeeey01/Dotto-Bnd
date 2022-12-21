import '@/pages/search/SearchResults.scss'
import { SideMenu } from '@/components/search/SideMenu'

export const SearchResults = () => {
  return (
    <main className={'search-result--container'}>
      <SideMenu />
      <section className={'search-result--body'}>TEST</section>
    </main>
  )
}
