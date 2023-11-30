import React from 'react';
import './CatalogMenu.css';

const CatalogMenu: React.FC = () => (
  <section className='catalog-menu'>
    <h2 className='catalog-menu__title'>Каталог</h2>

      <div className='catalog-menu__list'>
        <div className='catalog-menu__category'>
          <a className='catalog-menu__category-link' href="/">
            <div className='catalog-menu__category-wrapper'>
              <div className='catalog-menu__category-img'></div>
              <span className='catalog-menu__category-name'>Игровые приставки</span>
            </div>
            </a>
          </div>

          <div className='catalog-menu__category'>
          <a className='catalog-menu__category-link' href="/">
            <div className='catalog-menu__category-wrapper'>
                <div className='catalog-menu__category-img'></div>
                <span className='catalog-menu__category-name'>Телефоны и аксессуары</span>
            </div>
            </a>
          </div>

          <div className='catalog-menu__category'>
          <a className='catalog-menu__category-link' href="/">
            <div className='catalog-menu__category-wrapper'>
                <div className='catalog-menu__category-img'></div>
                <span className='catalog-menu__category-name'>Планшеты</span>
            </div>
            </a>
          </div>

          <div className='catalog-menu__category'>
          <a className='catalog-menu__category-link' href="/">
            <div className='catalog-menu__category-wrapper'>
                <div className='catalog-menu__category-img'></div>
                <span className='catalog-menu__category-name'>Ноутбуки</span>
            </div>
            </a>
          </div>

          <div className='catalog-menu__category'>
          <a className='catalog-menu__category-link' href="/">
            <div className='catalog-menu__category-wrapper'>
                <div className='catalog-menu__category-img'></div>
                <span className='catalog-menu__category-name'>Аксессуары</span>
            </div>
            </a>
          </div>
      </div>

      <div className='catalog-menu__list'>
          <div className='catalog-menu__category'>
          <a className='catalog-menu__category-link' href="/">
            <div className='catalog-menu__category-wrapper'>
                <div className='catalog-menu__category-img'></div>
                <span className='catalog-menu__category-name'>Умные часы и браслеты</span>
            </div>
            </a>
          </div>

          <div className='catalog-menu__category'>
          <a className='catalog-menu__category-link' href="/">
            <div className='catalog-menu__category-wrapper'>
                <div className='catalog-menu__category-img'></div>
                <span className='catalog-menu__category-name'>Наушники и гарнитуры</span>
            </div>
            </a>
          </div>

          <div className='catalog-menu__category'>
          <a className='catalog-menu__category-link' href="/">
            <div className='catalog-menu__category-wrapper'>
                <div className='catalog-menu__category-img'></div>
                <span className='catalog-menu__category-name'>Квадрокоптеры</span>
            </div>
            </a>
          </div>

          <div className='catalog-menu__category'>
          <a className='catalog-menu__category-link' href="/">
            <div className='catalog-menu__category-wrapper'>
                <div className='catalog-menu__category-img'></div>
                <span className='catalog-menu__category-name'>Компьютеры и комплектующие </span>
            </div>
            </a>
          </div>

          <div className='catalog-menu__category'>
          <a className='catalog-menu__category-link' href="/">
            <div className='catalog-menu__category-wrapper'>
                <div className='catalog-menu__category-img'></div>
                <span className='catalog-menu__category-name'>Портативная акустика</span>
            </div>
            </a>
          </div>
      </div>

      <div className='catalog-menu__list'>
          <div className='catalog-menu__category'>
          <a className='catalog-menu__category-link' href="/">
            <div className='catalog-menu__category-wrapper'>
                <div className='catalog-menu__category-img'></div>
                <span className='catalog-menu__category-name'>Техника для дома</span>
            </div>
            </a>
          </div>

          <div className='catalog-menu__category'>
          <a className='catalog-menu__category-link' href="/">
            <div className='catalog-menu__category-wrapper'>
                <div className='catalog-menu__category-img'></div>
                <span className='catalog-menu__category-name'>Гаджеты</span>
            </div>
            </a>
          </div>

      </div>
  </section>
);

export default CatalogMenu;
