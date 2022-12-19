import { useEffect } from 'react'
import { useMutation } from 'react-query'
import { ins as axios } from '@/lib/axios'
import { Spinner } from '@/components/common/spinner/Spinner'

export const KakaoLoginSuccess = () => {
  const url = new URL(window.location.href)
  const params = url.searchParams
  const code = params.get('code')

  const oauthApi = async () => {
    const { data } = await axios.get(`/oauth/${code}`)
    return data
  }

  const { mutate } = useMutation(oauthApi, {
    onSuccess: () => {},
    onError: () => {},
    onSettled: () => {
      //popupClose()
    },
  })
  useEffect(() => {
    mutate()
  }, [])

  const popupClose = () => {
    window.close()
  }

  return (
    <>
      <Spinner />
    </>
  )
}
