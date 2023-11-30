import React from 'react';
import ProductCardMedium from '../ProductCardMedium/ProductCardMedium';
import { mockedFavouritesProducts } from '../../utils/mocks';
import './Favourites.css';

const Favourites: React.FC<{
  name?: string
  originPrice?: number
  salesPrice?: number
  discount?: number
}> = () => {
  return (
    <section className='favourites'>
      <div className='favourites__container'>
        <h2 className='favourites__header'>Избранное</h2>
        <div className='favourites__content'>
        {mockedFavouritesProducts.map(({ name, originPrice, salesPrice, discount }) => {
          return (
            <>
            <ProductCardMedium name={name} originPrice={originPrice} salesPrice={salesPrice} discount={discount} />
            </>
          );
        })
    }
        </div>
      </div>
  </section>
  );
};

export default Favourites;
