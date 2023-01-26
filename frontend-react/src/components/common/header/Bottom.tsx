import { useEffect, useState } from 'react'
import { Link, useLocation } from 'react-router-dom'
import Typography from '@/components/common/typography/Typography'

export const Bottom = () => {
  const location = useLocation()
  const [currentPath, setCurrentPath] = useState(location.pathname)

  useEffect(() => {
    setCurrentPath(location.pathname)
  }, [location])

  return (
    <nav className="navigation">
      <ul>
        <li className={'mr-20'}>
          <Link to={'/'}>
            <Typography
              className={'nav-title' + (currentPath === '/' ? 'active' : '')}
            >
              HOME
            </Typography>
          </Link>
        </li>
        <li className={'mr-20'}>
          <Link to={'/'}>
            <Typography
              className={'nav-title' + (currentPath === '/try' ? 'active' : '')}
            >
              try DOTTO
            </Typography>
          </Link>
        </li>
        <li className={'mr-20'}>
          <Typography
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
              className={
                'nav-title' + (currentPath === '/feed' ? 'active' : '')
              }
            >
              Feed
            </Typography>
          </Link>
        </li>
        <li className={'mr-20'}>
          <Link to={'/'}>
            <Typography
              className={'nav-title' + (currentPath === '/faq' ? 'active' : '')}
            >
              고객지원
              <ul className={'dropdown--container'}>
                <li>
                  <Link to={'/'}>
                    <Typography
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
                      className={
                        'nav-title' +
                        (currentPath === '/notice' ? 'active' : '')
                      }
                    >
                      공지사항
                    </Typography>
                  </Link>
                </li>
              </ul>
            </Typography>
          </Link>
        </li>
      </ul>
    </nav>
  )
}
