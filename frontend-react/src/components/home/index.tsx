import { Banner } from '../banner'
import { BoardLabel } from '@/components/board/BoardLabel'
import { LABEL } from '@/constants/placeholders'
import PostList from '@/components/board/PostList'
import './index.scss'
import { ins } from '@/lib/axios'

export const Main = () => {
  const apiTest = async () => {
    const { data } = await ins.get('/follower/2')
    console.log(data)
  }
  return (
    <>
      <Banner />
      <main className={'main__container'}>
        <button onClick={apiTest}>TEST</button>
        <section>
          <BoardLabel
            title={LABEL.DOTTO_TITLE}
            subTitle={LABEL.DOTTO_SUB_TITLE}
            type={'home'}
          />
          <PostList list={undefined} />
        </section>

        <section>
          <BoardLabel
            title={LABEL.BOARD_TITLE}
            subTitle={LABEL.BOARD_SUB_TITLE}
            type={'home'}
          />
          <PostList list={undefined} />
        </section>
      </main>
    </>
  )
}
