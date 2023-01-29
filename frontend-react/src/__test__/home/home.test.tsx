import { render } from '@testing-library/react'
import { Header } from '@/components/common/header/Header'
import { Banner } from '@/components/banner'

describe('메인 홈 화면 테스트 코드', () => {
  const mockFn = jest.fn()
  /**
   * @description 필요한 API 불러오기
   * 1. 닷투 게시판
   * 2. 닷찾사 게시판
   */
  beforeAll(async () => {})
  test('배너 이미지', () => {
    render(<Banner />)
  })
  test('닷투 게시판 불러오기', async () => {
    console.log('?')
  })

  test('닷찾사 게시판 불러오기', async () => {
    console.log('TEST2')
  })
})

export {}
