import LOGO from '@/assets/icons/logo/dotto.svg'
import { Link } from 'react-router-dom'
import React from 'react'
import { Bottom } from '@/components/common/header/Bottom'

export const FeedHeader = () => {
  return (
    <header>
      <nav>
        <Link to="/">
          <img width={103} height={40} alt="닷투 로고" src={LOGO} />
        </Link>
        <Bottom />
      </nav>
    </header>
  )
}
