import style from '@/components/feed/FeedLists.module.scss'
import Typography from '@/components/common/typography/Typography'

export const FeedLists = () => {
  return (
    <article className={style.feedContainer}>
      <section>
        <ul>
          <li>
            <section>
              <div>
                <span>프로필 이미지</span>
                <Typography>User_name</Typography>
                <Typography>회원 1시간전</Typography>
              </div>
              <div>메뉴버튼</div>
            </section>
            <section>
              이미지파일 (캐러셀)
              <span>dot button</span>
            </section>
            <section>
              <span>하트</span>
              <span>comment count</span>
            </section>
            <section>
              <div>로그인한 프로필</div>
              <div>댓글 입력 input</div>
              <div>submit button</div>
            </section>
          </li>
        </ul>
      </section>
    </article>
  )
}
