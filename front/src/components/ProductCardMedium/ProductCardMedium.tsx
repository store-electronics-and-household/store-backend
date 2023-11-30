import React from 'react';
import vector from '../../image/Vector.png';

const ProductCardMedium: React.FC<{
  originPrice: number;
  salesPrice: number;
  name: string;
  discount: number;
}> = (product) => {
  const [isLiked, setIsLiked] = React.useState(false);
  function handleLike(): void {
    setIsLiked(!isLiked);
  }
  const [isCounter, setIsCounter] = React.useState(0);
  function handleAddToBasket(): void {
    setIsCounter((prev) => {
      return prev + 1;
    });
  }
  const cardLikeButtonClassName = `card-medium__like ${
    isLiked && 'card-medium__like_active'
  }`;
  return (
    <li className='card-medium'>
      <div className='card-medium__container'>
        <div className='card-medium__container-image'>
          <a className='card-medium__link' href='/'>
            <img
              className='card-medium__image'
              src={vector}
              alt='прекрасная фотография товара'
            />
            <p className='card-medium__sticker'>- {product.discount} %</p>
          </a>
          <button
            type='button'
            aria-label='Поставить лайк'
            className={cardLikeButtonClassName}
            onClick={handleLike}
          />
          <div className='card-medium__button'>
            <button
              type='button'
              aria-label='Добавить в корзину'
              className={
                isCounter > 0 ? 'card-medium__buy_big' : 'card-medium__buy'
              }
              onClick={handleAddToBasket}
            />
            <span className='card-medium__counter'>
              {isCounter > 0 ? isCounter : ''}
            </span>
          </div>
        </div>
        <div className='card-medium__footer'>
          <a className='card-medium__link' href='/'>
            <div className='card-medium__container-footer'>
              <h3 className='card-medium__title'>{product.name}</h3>
              <div className='card-medium__price-container'>
                <p className='card-medium__price'>
                  {' '}
                  {product.originPrice.toLocaleString('ru-RU', {
                    style: 'currency',
                    currency: 'RUB',
                    maximumFractionDigits: 0,
                  })}{' '}
                </p>
                <p className='card-medium__oldprice'>
                  {product.salesPrice.toLocaleString('ru-RU', {
                    style: 'currency',
                    currency: 'RUB',
                    maximumFractionDigits: 0,
                  })}
                </p>
              </div>
            </div>
          </a>
        </div>
      </div>
    </li>
  );
};

export default ProductCardMedium;
