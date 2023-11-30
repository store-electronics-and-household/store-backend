import React from 'react';
import type { FC } from 'react';
import { Link } from 'react-router-dom';
import { mockedCartProducts } from '../../utils/mocks';

// import './Cart.css';
import crossIcon from '../../image/icons/cart_cross_icon.svg';
import minusIconDefault from '../../image/icons/cart_minus_icon_default.svg';
import minusIconActive from '../../image/icons/cart_minus_icon_active.svg';
import plusIconActive from '../../image/icons/cart_plus_icon_active.svg';

const Cart: FC = () => {
  return (
  <section className='cart'>
  <div className='cart__container'>
  <h2 className='cart__header'>Корзина</h2>
    {/*
      For future development:

      If cart is empty:
      <div className='cart__default_content'>
      <h3 className='cart__default-info'>В корзине пока ничего нет</h3>
      <button className='cart__default-button'><span className='cart__default-button-content'>Перейти в каталог</span></button>
      </div>
    */}

    <div className='cart__content'>
      <div className='cart__list'>

        {mockedCartProducts.map(({ id, name, description, article, quantity, originPrice, salesPrice }) => {
          return (
        <>
        <div className='cart__item' key={id}>
          <div className='cart__item-info'>
            <div className='cart__item-image-wrapper'></div>
              <div className='cart__item-textdata'>
                <h3 className='cart__item-name'>{name}</h3>
                <p className='cart__item-description'>{description}</p>
                <p className='cart__item-article'>Арт. {article}</p>
              </div>
            </div>
          <div className='cart__item-quantity-button'>
            <button className='cart__item-quantity-button-symbol'><img src={minusIconDefault} alt="Уменьшить количество" /></button>
            <p className='cart__item-quantity-button-number'>{quantity}</p>
            <button className='cart__item-quantity-button-symbol'><img src={plusIconActive} alt="Увеличить количество" /></button>
          </div>
          <div className='cart__item-price-container'>
            <div className='cart__item-price-wrapper'>
              <span className='cart__item-salesprice'>{salesPrice}<span> ₽</span></span>
            </div>
            <div className='cart__item-price-wrapper'>
              <span className='cart__item-originprice'>{originPrice}<span> ₽</span></span>
            </div>
          </div>
          <div className='cart__cross-button-wrapper'>
            <button className='cart__cross-button'>
              <img className='cart__cross-icon' src={crossIcon} alt="Удалить из корзины"/>
            </button>
          </div>
        </div>
      </>
          );
        })
      }
        <div className='cart__item'>
          <div className='cart__item-info'>
            <div className='cart__item-image-wrapper'></div>
            <div className='cart__item-textdata'>
              <h3 className='cart__item-name'>Название товара</h3>
              <p className='cart__item-description'>Характеристики товара</p>
              <p className='cart__item-article'>Арт. 1234556</p>
            </div>
          </div>

          <div className='cart__item-price-wrapper'>
            <div className='cart__item-quantity-button'>
              <button className='cart__item-quantity-button-symbol'><img src={minusIconActive} alt="Уменьшить количество" /></button>
              <p className='cart__item-quantity-button-number'>2</p>
              <button className='cart__item-quantity-button-symbol'><img src={plusIconActive} alt="Увеличить количество" /></button>

              <div className='cart__item-onceprice-container'>
                <div className='cart__item-onceprice-wrapper'>
                  <span className='cart__item-onceprice'>100 000<span> ₽/шт.</span></span>
                </div>
              </div>
            </div>
          </div>

          <div className='cart__item-price-container'>
            <div className='cart__item-price-wrapper'>
            <span className='cart__item-salesprice'>200000<span> ₽</span></span>
            </div>
          </div>
          <div className='cart__cross-button-wrapper'>
            <button className='cart__cross-button'>
            <img className='cart__cross-icon' src={crossIcon} alt="Удалить из корзины"/>
            </button>
          </div>
        </div>
      </div>

  <div className='cart__summary'>
    <div className='cart__summary-container'>
        <div className='cart__order-title-wrapper'>
          <h2 className='cart__order-title'>Сумма заказа</h2>
        </div>

        <div className='cart__order-info'>
            <div className='cart__order-info-wrapper'>
              <span className='cart__order-info-sum'>3 товара на сумму</span>
              <span className='cart__order-info-sum'>400 000<span> ₽</span></span>
            </div>

            <div className='cart__order-info-wrapper'>
              <span className='cart__order-info-sum'>Скидка</span>
              <span className='cart__order-info-sum-discount'><span>-</span>10 000<span> ₽</span></span>
            </div>
        </div>

        <div className='cart__order-total-wrapper'>
          <h2 className='cart__order-total'>Итого</h2>
          <h2 className='cart__order-total-sum'>390 000<span> ₽</span></h2>{/* price * quantity */}
        </div>
      </div>

        <div className='cart__order-button-wrapper'>
          <Link to='/'>
            <button className='cart__order-button'>
            <span className='cart__order-button-title'>Оформить заказ</span>
            </button>
          </Link>
        </div>
      </div>
    </div>
  </div>
  </section>
  );
};

export default Cart;
