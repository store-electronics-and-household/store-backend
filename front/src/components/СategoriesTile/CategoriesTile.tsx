import React from 'react';
import './CategoriesTile.css';
import { useLocation } from 'react-router-dom';

const CategoriesTile: React.FC<{
  catTitle: string;
  catImg: string;
  catUrl?: string;
}> = (tile) => {
  const location = useLocation();
  return (
    <li
      className={
        location.pathname === '/main'
          ? 'tile__item'
          : 'tile__item tile__item_small'
      }
    >
      <a className='tile__content' href={tile.catUrl}>
        <span className='tile__category-name'>{tile.catTitle}</span>
        <img
          className={
            location.pathname === '/main'
              ? 'tile__image'
              : 'tile__image tile__image_small'
          }
          alt=''
          src={tile.catImg}
        />
      </a>
    </li>
  );
};

export default CategoriesTile;
