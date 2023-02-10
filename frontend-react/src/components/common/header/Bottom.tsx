import { ComponentProps, useEffect, useState } from 'react'
import { Link, useLocation } from 'react-router-dom'
import Typography from '@/components/common/typography/Typography'
import cn from 'classnames'

interface Props extends ComponentProps<'nav'> {}

export const Bottom = (props: Props) => {
  const { className } = props
  const location = useLocation()
  const [currentPath, setCurrentPath] = useState(location.pathname)

  useEffect(() => {
    setCurrentPath(location.pathname)
  }, [location])

  return (
    <nav className={cn(className, 'navigation')}>
      <ul>
        <li className={'mr-20'}>
          <Link to={'/'}>
            <Typography
              as="span"
              variant={'sub2'}
              className={
                'nav-title' + (currentPath === '/dotto/board' ? 'active' : '')
              }
            >
              HOME
            </Typography>
          </Link>
        </li>
        <li className={'mr-20'}>
          <Link to={'/'}>
            <Typography
              as="span"
              variant={'sub2'}
              className={'nav-title' + (currentPath === '/try' ? 'active' : '')}
            >
              try DOTTO
            </Typography>
          </Link>
        </li>
        <li className={'mr-20'}>
          <Typography
            as="span"
            variant={'sub2'}
            className={
              'nav-title' +
              (currentPath === '/home' || currentPath === '/home-board'
                ? 'active'
                : '')
            }
          >
            COMMUNITY
            <ul className={'dropdown--container'}>
              <li>
                <Link to={'/home/board'}>
                  <Typography
                    as="span"
                    variant={'sub2'}
                    className={
                      'nav-title' + (currentPath === '/home' ? 'active' : '')
                    }
                  >
                    닷투 게시판
                  </Typography>
                </Link>
              </li>
              <li>
                <Link to={'/'}>
                  <Typography
                    as="span"
                    variant={'sub2'}
                    className={
                      'nav-title' +
                      (currentPath === '/home-board' ? 'active' : '')
                    }
                  >
                    닷찾사 게시판
                  </Typography>
                </Link>
              </li>
            </ul>
          </Typography>
        </li>
        <li className={'mr-20'}>
          <Link to={'/feed'}>
            <Typography
              as="span"
              variant={'sub2'}
              className={
                'nav-title' + (currentPath === '/feed' ? 'active' : '')
              }
            >
              Feed
            </Typography>
          </Link>
        </li>
        <li className={'mr-20'}>
          <Typography
            as="span"
            variant={'sub2'}
            className={'nav-title' + (currentPath === '/faq' ? 'active' : '')}
          >
            고객지원
            <ul className={'dropdown--container'}>
              <li>
                <Link to={'/'}>
                  <Typography
                    as="span"
                    variant={'sub2'}
                    className={
                      'nav-title' + (currentPath === '/faq' ? 'active' : '')
                    }
                  >
                    FAQ
                  </Typography>
                </Link>
              </li>
              <li>
                <Link to={'/'}>
                  <Typography
                    as="span"
                    variant={'sub2'}
                    className={
                      'nav-title' + (currentPath === '/notice' ? 'active' : '')
                    }
                  >
                    공지사항
                  </Typography>
                </Link>
              </li>
            </ul>
          </Typography>
        </li>
      </ul>
    </nav>
  )
}
