import '@/components/search/SideMenu.scss'
import Typography from '@/components/common/typography/Typography'
import { Link } from 'react-router-dom'
import { useLocation } from 'react-router'
import { useEffect, useState } from 'react'

export const SideMenu = () => {
  const location = useLocation()
  const [category, setCategory] = useState(location.pathname)

  useEffect(() => {
    setCategory(location.pathname)
  }, [location])

  return (
    <aside className={'side-menu'}>
      <ul className={'side-menu--items'}>
        <li className={'side-menu--item'}>
          <Link to={'/'}>
            <Typography
              className={'flex'}
              fontColor={'all' === 'all' ? 'gray1' : 'gray3'}
              fontWeight={'all' === 'all' ? 'medium' : 'regular'}
            >
              통합
              <span className={'circle ml-2'} />
            </Typography>
          </Link>
        </li>
        <li className={'side-menu--item'}>
          <Link to={'/'}>
            <Typography className={'flex'} fontColor={'gray3'}>
              닷투
              {category === '1' && <span className={'circle ml-2'} />}
            </Typography>
          </Link>
        </li>
        <li className={'side-menu--item'}>
          <Link to={'/'}>
            <Typography className={'flex'} fontColor={'gray3'}>
              닷찾사
              {category === '1' && <span className={'circle ml-2'} />}
            </Typography>
          </Link>
        </li>
        <li className={'side-menu--item'}>
          <Link to={'/'}>
            <Typography className={'flex'} fontColor={'gray3'}>
              아티스트
              {category === '1' && <span className={'circle ml-2'} />}
            </Typography>
          </Link>
        </li>
        <li className={'side-menu--item'}>
          <Link to={'/'}>
            <Typography className={'flex'} fontColor={'gray3'}>
              회원
              {category === '1' && <div className={'circle ml-2'} />}
            </Typography>
          </Link>
        </li>
      </ul>
    </aside>
  )
}
