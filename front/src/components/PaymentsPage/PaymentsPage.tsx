import React, { useEffect } from 'react';
import Breadcrumb from '../Breadcrumb/Breadcrumb';
import { paymentPageData } from '../../utils/constants';
import PaymentsPageItem from './PaymentsPageItem';

const PaymentsPage: React.FC = () => {
  // interface ClientDataProps {
  //   name: string
  //   phone: string
  //   address: string
  // }
  // const [clientData, setClientData] = React.useState<ClientDataProps | null>(null);

  interface GoodsListProps {
    id: number
    quantity: number
    salesPrice: number
    imgUrl: string
    discount: number
  }
  const [goodsList, setGoodsList] = React.useState <GoodsListProps[]>([]);

  useEffect(() => {
    setGoodsList(paymentPageData);
  }, []);

  return (
    <section className='payments-page'>
      <Breadcrumb
        currentPlace='Оформление заказа'
        previousPlace='Корзина'
        previousPath='/cart'
      />
      <h2 className='payments-page__title'>Оформление заказа</h2>
      <div className='payments-page__container'>
        <div className='payments-page__input-containers'>
          <form className='payments-page__client-name' action=''>
            <p className='payments-page__form-title'>Ваши данные</p>
            <div className='payments-page__line'></div>
          </form>
          <form className='payments-page__client-address' action=''>
            <div className='payments-page__title-container'>
              <p className='payments-page__form-title'>Адрес доставки</p>
              <button className='payments-page__delivery-type'>Курьер</button>
              <button className='payments-page__delivery-type'>
                Самовывоз
              </button>
            </div>
            <div className='payments-page__line'></div>
          </form>
        </div>
        <div className='payments-page__summary-conatiner'>
          <div className='payments-page__summary'>
            <p className='payments-page__form-title'>Ваш заказ</p>
            <div className='payments-page__line'></div>
            <div className='payments-page__cart-container'>
              {goodsList.map((item) => (
                <PaymentsPageItem
                  key={item.id}
                  quantity={item.quantity}
                  imgUrl={item.imgUrl}
                />
              ))}
            </div>
            <div className='payments-page__summary-data'>
              <p className='payments-page__summary-row'>3 товара на сумму</p>
              <p className='payments-page__summary-row'>463970р</p>
            </div>
            <div className='payments-page__summary-data'>
              <p className='payments-page__summary-row'>Скидка</p>
              <p className='payments-page__summary-row payments-page__summary-row_discount'>
                12390
              </p>
            </div>
            <div className='payments-page__summary-data'>
              <p className='payments-page__summary-row'>Доставка</p>
              <p className='payments-page__summary-row '>Не выбрано</p>
            </div>
            <div className='payments-page__line'></div>
            <div className='payments-page__summary-data'>
              <p className='payments-page__summary-final'>Итого</p>
              <p className='payments-page__summary-final'>463 970 ₽</p>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default PaymentsPage;

//   const [clientData, setClientData] = React.useState({});
/*
<div className='payments-page__good'>
                <img
                  className='payments-page__good-photo'
                  src={GOOD_1}
                  alt='фото товара'
                />
                <p className='payments-page__good-numbers'>x2</p>
              </div>
              <div className='payments-page__good'>
                <img
                  className='payments-page__good-photo'
                  src={GOOD_2}
                  alt='фото товара'
                />
                <p className='payments-page__good-numbers'>x2</p>
              </div>
              <div className='payments-page__good'>
                <img
                  className='payments-page__good-photo'
                  src={GOOD_2}
                  alt='фото товара'
                />
                <p className='payments-page__good-numbers'>x2</p>
              </div>
              <div className='payments-page__good'>
                <img
                  className='payments-page__good-photo'
                  src={GOOD_2}
                  alt='фото товара'
                />
                <p className='payments-page__good-numbers'>x2</p>
              </div>
*/
