import '../../assets/styles/common/card/index.scss'
import { Banner } from '../banner'
import './index.scss'
import { ins } from '@/lib/axios'
import Button from '@/components/common/button/Button'

export const Dotto = () => {
  const apiTest = async () => {
    try {
      const { data } = await ins.get('/feed')
      console.log(data)
    } catch (e) {
      console.log(e)
    }
  }
  return (
    <>
      <Banner />
      <article className="dotto mt-32">
        <Button variant={'primary-outline'} type={'button'} onClick={apiTest}>
          API TEST
        </Button>
        <section className="card-layout mb-32">
          <figure className="card-layout__img"></figure>
          <section className="card-layout__content">
            <p className="card-layout__content__items--writer">tattoist</p>
            <p className="card-layout__content__items--title mt-16">
              반짝이와 패턴 (도안 수정 가능)
            </p>
            <p className=" price-container mt-8">
              <span className="event-price">5만원</span>
              <span className="origin-price">10만</span>
              <span className="percent">50%</span>
            </p>
            <p className="tag-container mt-16">
              <span className="tag mr-8">서울 홍대</span>
              <span className="tag mr-8">라인워크</span>
              <span className="tag">미니타투</span>
            </p>
          </section>
        </section>

        <section className="card-layout">
          <section>IMAGE</section>
          <section>WRITER</section>
        </section>

        <section className="card-layout">
          <section>IMAGE</section>
          <section>WRITER</section>
        </section>

        <section className="card-layout">
          <section>IMAGE</section>
          <section>WRITER</section>
        </section>

        <section className="card-layout">
          <section>IMAGE</section>
          <section>WRITER</section>
        </section>

        <section className="card-layout">
          <section>IMAGE</section>
          <section>WRITER</section>
        </section>

        <section className="card-layout">
          <section>IMAGE</section>
          <section>WRITER</section>
        </section>

        <section className="card-layout">
          <section>IMAGE</section>
          <section>WRITER</section>
        </section>
      </article>
    </>
  )
}
