import React from 'react';
import './CategoriesMain.css';
import { categoriesList } from '../../utils/constants';
import CategoriesTile from '../СategoriesTile/CategoriesTile';

const CategoriesMain: React.FC<{
  catId?: number;
  catTitle?: string;
  catImg?: string;
  catUrl?: string;
}> = (tile) => {
  return (
    <section className='tile-main'>
      <div className='tile-main__text'>
        <h2 className='tile-main__title'>Категории</h2>
      </div>
      <ul className='tile-main__list'>
        {categoriesList
          .sort((a, b) => a.catTitle.localeCompare(b.catTitle))
          .map((tile) => (
            <CategoriesTile
              key={tile.catId}
              catTitle={tile.catTitle}
              catImg={tile.catImg}
              catUrl={tile.catUrl}
            />
          ))}
      </ul>{' '}
    </section>
  );
};

export default CategoriesMain;
