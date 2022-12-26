import './index.scss'
import { useEffect, useRef, useState } from 'react'

const TOTAL_SLIDES = 4

export const Banner = () => {
  const [currentSlide, setCurrentSlide] = useState<number>(0)
  const slideRef = useRef<any>(null)

  useEffect(() => {
    slideRef.current.style.transition = 'all 0.5s ease-in-out'
    if (currentSlide === 0)
      slideRef.current.style.transform = `translateX(-${currentSlide}00%)`
    else slideRef.current.style.transform = `translateX(-${currentSlide}20%)`
  }, [currentSlide])

  const nextSlide = () => {
    if (currentSlide >= TOTAL_SLIDES) {
      setCurrentSlide(0)
    } else {
      setCurrentSlide(currentSlide + 1)
    }
  }

  const prevSlide = () => {
    if (currentSlide === 0) {
      setCurrentSlide(TOTAL_SLIDES)
    } else {
      setCurrentSlide(currentSlide - 1)
    }
  }

  return (
    <article className={'banner'}>
      <button type={'button'} onClick={prevSlide}>
        {'<'}
      </button>
      <div ref={slideRef} className={'img-group'}>
        <img
          src={'https://picsum.photos/300/300'}
          alt={'test'}
          className={'img item'}
        />
        <img
          src={'https://picsum.photos/300/300'}
          alt={'test'}
          className={'img item'}
        />
        <img
          src={'https://picsum.photos/300/300'}
          alt={'test'}
          className={'img item'}
        />
        <img
          src={'https://picsum.photos/300/300'}
          alt={'test'}
          className={'img item'}
        />
      </div>

      <button type={'button'} onClick={nextSlide}>
        {'>'}
      </button>
    </article>
  )
}
