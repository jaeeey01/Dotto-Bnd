import {
  createBrowserRouter,
  createRoutesFromElements,
  Navigate,
  Route,
} from 'react-router-dom'
import { Dotto } from '@/components/dotto'
import MainLayout from '@/components/layout/MainLayout'
import BoardPost from '@/pages/BoardPost'
import BoardPostDetail from '@/pages/BoardPostDetail'
import BoardWrite from '@/pages/BoardWrite'
import { KakaoLoginSuccess } from '@/components/utils/kakao-api/KakaoLoginSuccess'
import { SearchResults } from '@/pages/search/SearchResults'
import Request from '@/pages/request/Request'
import { UserResults } from '@/components/search/UserResults'
import { BoardResults } from '@/components/search/BoardResults'
import { SearchIndex } from '@/components/search/SearchIndex'

export const router = createBrowserRouter(
  createRoutesFromElements(
    <Route>
      <Route element={<MainLayout />}>
        <Route index element={<Navigate to="/dotto/board" />} />
        <Route path="dotto">
          <Route path="board">
            <Route index element={<Dotto />} />
            <Route path="post" element={<BoardPost />} />
            <Route path="view/:postNo" element={<BoardPostDetail />} />
            <Route path="write" element={<BoardWrite />} />
          </Route>

          <Route path="feed">
            <Route index />
            <Route path="post" />
          </Route>
        </Route>

        <Route path="search" element={<SearchResults />}>
          <Route index path="result" element={<SearchIndex />} />
          <Route path="menu" element={<UserResults />} />
        </Route>

        <Route path="estimate/:postNo" />
        <Route path="my" />
        <Route path="401" />
        <Route path="*" />
      </Route>

      <Route path="request">
        <Route index element={<Navigate to="/*" />} />
        <Route path=":postNo" element={<Request />} />
      </Route>
      <Route path="/oauth/kakao/redirect" element={<KakaoLoginSuccess />} />
    </Route>
  )
)
