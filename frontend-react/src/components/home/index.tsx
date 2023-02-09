import { Banner } from '../banner'
import { BoardLabel } from '@/components/board/BoardLabel'
import { LABEL } from '@/constants/placeholders'
import PostList from '@/components/board/PostList'
import './index.scss'
import dummy from '@/assets/dummy/board'

export const Main = () => {
  return (
    <>
      <Banner />
      <main className={'main__container'}>
        <section>
          <BoardLabel
            title={LABEL.DOTTO_TITLE}
            subTitle={LABEL.DOTTO_SUB_TITLE}
            type={'home'}
          />
          <PostList list={dummy} />
        </section>

        <section>
          <BoardLabel
            title={LABEL.BOARD_TITLE}
            subTitle={LABEL.BOARD_SUB_TITLE}
            type={'home'}
          />
          <PostList list={dummy} />
        </section>
      </main>
    </>
  )
}
