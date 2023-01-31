import style from '@/components/feed/FeedLists.module.scss'
import Typography from '@/components/common/typography/Typography'
import Image from '@/components/common/image/Image'
import MENU from '@/assets/icons/myfeed/dotmenu.svg'
import { Hearts } from '@/components/feed/icons/Hearts'
import { Comments } from '@/components/feed/icons/Comments'
import SUBMIT from '@/assets/icons/myfeed/submit.svg'
import { useState } from 'react'
import { ToggleMenu } from '@/components/feed/ToggleMenu'
import { Banner } from '@/components/banner'

const mockData = [
  {
    user: {
      id: 1,
      profile: 'https://picsum.photos/300/300',
      nickname: 'User_Nickname',
    },
    post: {
      id: 1,
      writer: 'User_Name1',
      createdAt: '1시간 전',
      profile: 'https://picsum.photos/300/300',
      thumbnails: [
        {
          id: 1,
          src: 'https://picsum.photos/300/300',
        },
        {
          id: 2,
          src: 'https://picsum.photos/300/300',
        },
        {
          id: 3,
          src: 'https://picsum.photos/300/300',
        },
      ],
      comment: [
        {
          id: 1,
        },
      ],
      like: 6,
    },
  },
  {
    user: {
      id: 2,
      profile: 'https://picsum.photos/300/300',
      nickname: 'User_Nickname',
    },
    post: {
      id: 2,
      writer: 'User_Name2',
      createdAt: '방금',
      profile: 'https://picsum.photos/300/300',
      thumbnails: [
        {
          id: 1,
          src: 'https://picsum.photos/300/300',
        },
        {
          id: 2,
          src: 'https://picsum.photos/300/300',
        },
        {
          id: 3,
          src: 'https://picsum.photos/300/300',
        },
      ],
      comment: [
        {
          id: 1,
        },
      ],
      like: 5,
    },
  },
  {
    user: {
      id: 3,
      profile: 'https://picsum.photos/300/300',
      nickname: 'User_Nickname',
    },
    post: {
      id: 3,
      writer: 'User_Name3',
      createdAt: '10시간 전',
      profile: 'https://picsum.photos/300/300',
      thumbnails: [
        {
          id: 1,
          src: 'https://picsum.photos/300/300',
        },
        {
          id: 2,
          src: 'https://picsum.photos/300/300',
        },
        {
          id: 3,
          src: 'https://picsum.photos/300/300',
        },
      ],
      comment: [
        {
          id: 0,
        },
      ],
      like: 0,
    },
  },
]

export const FeedLists = () => {
  const [selectedCard, setSelectedCard] = useState<number>(-1)

  const onClickHandlerShowMenu = (index: number) => {
    if (selectedCard > -1) setSelectedCard(-1)
    else setSelectedCard(index)
  }
  return (
    <article className={style.feed__container}>
      <ul>
        {mockData.map((feed, index) => {
          return (
            <li key={index} className={style.feed__card}>
              <section className={style.feed__profile}>
                <section className={'flex'}>
                  <Image
                    width={40}
                    height={40}
                    className={style.feed__profile__img}
                    src={feed.post.profile || 'https://picsum.photos/300/300'}
                    alt={`thubnail`}
                  />
                  <div>
                    <Typography
                      variant={'body1'}
                      fontWeight={'bold'}
                      fontColor={'black'}
                    >
                      {feed.post.writer}
                    </Typography>
                    <Typography
                      variant={'body2'}
                      fontWeight={'regular'}
                      fontColor={'gray3'}
                    >
                      {feed.post.createdAt}
                    </Typography>
                  </div>
                </section>
                <section>
                  <button
                    type={'button'}
                    onClick={() => onClickHandlerShowMenu(index)}
                  >
                    <Image src={MENU} alt={`feed menu`} />
                  </button>
                  <ToggleMenu selectedIndex={selectedCard} keyIndex={index} />
                </section>
              </section>
              {/* TODO: IMAGE Carousel */}
              <Banner />
              {/*<div>*/}
              {/*  <Image*/}
              {/*    className={style.feed__thumbnail}*/}
              {/*    src={'https://picsum.photos/300/300'}*/}
              {/*    alt={`thumbnail`}*/}
              {/*  />*/}
              {/*  <div className={'flex justify-center'}>*/}
              {/*    <span>1</span>*/}
              {/*    <span>2</span>*/}
              {/*    <span>3</span>*/}
              {/*  </div>*/}
              {/*</div>*/}

              <section className={style.feed__information}>
                <Hearts count={feed.post.like} className={'mr-24'} />
                <Comments count={feed.post.comment.length} />
              </section>

              <hr />

              <section className={style.feed__comment__container}>
                <Image
                  width={32}
                  height={32}
                  className={style.my__profile__img}
                  src={feed.user.profile || 'https://picsum.photos/300/300'}
                  alt={`my profile`}
                />
                <input
                  type={'text'}
                  placeholder={'댓글을 입력하세요...'}
                  className={style.feed__comment__input}
                />
                <button type={'button'} className={style.feed__comment__submit}>
                  <Image
                    width={32}
                    height={32}
                    src={SUBMIT}
                    alt={`comment submit`}
                  />
                </button>
              </section>
            </li>
          )
        })}
      </ul>
    </article>
  )
}
