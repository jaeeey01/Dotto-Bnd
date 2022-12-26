import Typography from '@/components/common/typography/Typography'
import { useUserPreview } from '@/lib/hooks/useUserPreview'
import '@/components/user/UserPreview.scss'
import { useState } from 'react'
import Image from '@/components/common/image/Image'

interface IUserPreview {
  type: string
  label: string
}

export const UserPreview = (props: IUserPreview) => {
  const { type, label } = props
  useUserPreview(type)

  const [userLists, setUserLists] = useState([])

  return (
    <>
      <Typography>{label}</Typography>
      <div className={'user-list--group'}>
        <ul className={'flex'}>
          {[...new Array(10)].map((item, index) => {
            return (
              <li key={index}>
                <Image
                  className="profile-image mr-10"
                  src={item?.image || 'https://picsum.photos/300/300'}
                  alt={`${item} profile`}
                />

                <Typography
                  variant={'body2'}
                  fontColor={'gray3'}
                  className={'profile-image__caption pt-16 mr-10'}
                >
                  {item || '전다훈'}
                </Typography>
              </li>
            )
          })}
        </ul>
      </div>
    </>
  )
}
