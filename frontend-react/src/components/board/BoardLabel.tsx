import Typography from '@/components/common/typography/Typography'
import { Link } from 'react-router-dom'
import { useEffect, useState } from 'react'
import arrow from '@/assets/icons/main/redirect.svg'
import Image from '@/components/common/image/Image'

interface IBoardLabel {
  title: string
  subTitle: string
  type: string
}

export const BoardLabel = (props: IBoardLabel) => {
  const { title, subTitle, type } = props
  const [href, setHref] = useState('')

  useEffect(() => {
    redirectBoard()
  }, [type])

  const redirectBoard = () => {
    switch (type) {
      case 'home':
        setHref('/home/board')
        break
      case 'dottoTest':
        setHref('/')
        break
    }
  }
  return (
    <article className={'pb-32 flex justify-between items-center'}>
      <div className={'flex'}>
        <Typography
          className={'pr-16'}
          variant={'h3'}
          fontWeight={'bold'}
          fontColor={'gray1'}
        >
          {title}
        </Typography>
        <Typography variant={'sub1'} fontWeight={'regular'} fontColor={'gray2'}>
          {subTitle}
        </Typography>
      </div>
      <Link to={href}>
        <Typography
          className={'flex'}
          variant={'span'}
          fontWeight={'medium'}
          fontColor={'gray1'}
        >
          전체보기
          <Image
            alt={'닷투 게시판으로 이동'}
            width={24}
            height={24}
            src={arrow}
          />
        </Typography>
      </Link>
    </article>
  )
}
