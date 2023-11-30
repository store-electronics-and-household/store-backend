import React from 'react';
import smileGoal from '../../image/icons/smile_icon.svg';
import delivery from '../../image/icons/delivery_icon.svg';
import originalMedal from '../../image/icons/medal-original_icon.svg';
import Breadcrumb from '../Breadcrumb/Breadcrumb';

const AboutCompany: React.FC = () => {
  return (
    <>
      <section className='about-company'>
        <Breadcrumb
          currentPlace='О нас'
          previousPlace='Главная'
          previousPath='/'
        />
        <div className='about-company__container'>
          <h1 className='about-company__header'>О нас</h1>
          <ul className='about-company__feature-list'>
            <li className='about-company__feature'>
              <img
                className='about-company__feature-icon'
                src={smileGoal}
                alt='смайл, улыбка, цель компании'
              />
              <h2 className='about-company__feature-name'>Наша цель</h2>
              <div className='about-company__feature-content'>
                <p className='about-company__feature-text'>
                  У нас есть офлайновая торговая точка
                  и&nbsp;интернет&#8209;магазин. Продаем смартфоны, компьютеры,
                  «умные» часы и браслеты, кофемашины и др.
                </p>
                <p className='about-company__feature-text'>
                  Цель, к которой мы стремимся — сделать так, чтобы люди
                  пользовались классными гаджетами&nbsp;и&nbsp;электроникой за
                  приемлемую стоимость. Потому что крутая техника облегчает
                  жизнь и доставляет удовольствие — пусть ее у всех будет
                  больше.
                </p>
              </div>
            </li>
            <li className='about-company__feature'>
              <img
                className='about-company__feature-icon'
                src={delivery}
                alt='Супер быстрая доставка, brjyrfтранспорт'
              />
              <h2 className='about-company__feature-name'>Супер-доставка</h2>
              <div className='about-company__feature-content'>
                <p className='about-company__feature-text'>
                  У нас работает доставка до двери и самовывоз. Большинство
                  заказов доставляем в день оформления либо на следующий день.
                  Возможна оплата на сайте или при получении.
                </p>
                <p className='about-company__feature-text'>
                  Срок и стоимость доставки в регионы рассчитывается
                  автоматически, когда вы оформляете заказ, но в этом случае мы
                  просим сразу внести оплату.
                </p>
                <p className='about-company__feature-text'>
                  Мы гарантируем, что заказ будет доставлен вовремя и без
                  повреждений, даже если вокруг новогодние праздники или упал
                  метеорит. Остальные условия читайте в разделе Доставка.
                </p>
              </div>
            </li>
            <li className='about-company__feature'>
              <img
                className='about-company__feature-icon'
                src={originalMedal}
                alt='знак медали, оригинальная техника, новинки'
              />
              <h2 className='about-company__feature-name'>
                Только оригинальная техника и новинки
              </h2>
              <div className='about-company__feature-content'>
                <p className='about-company__feature-text'>
                  У нас вы не встретите подделок и устаревших моделей товаров.
                  Продаем только оригинальную технику. Выбираем проверенные
                  бренды и новинки.
                </p>
              </div>
            </li>
          </ul>
        </div>
      </section>
    </>
  );
};

export default AboutCompany;
