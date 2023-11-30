import React from 'react';
import '../Catalog/Catalog.css';
import ProductCardMedium from '../ProductCardMedium/ProductCardMedium';
import CategoriesTile from '../СategoriesTile/CategoriesTile';
import Breadcrumb from '../Breadcrumb/Breadcrumb';
import { product, subCategoriesList } from '../../utils/constants';

const Categories: React.FC<{
  id?: number;
  name?: string;
  originPrice?: number;
  salesPrice?: number;
  discount?: number;
}> = () => {
  return (
    <section className='catalog'>
      <Breadcrumb
        currentPlace='Смартфоны'
        previousPlace='Главная'
        previousPath='/'
      />
      <div className='catalog__container-big'>
        <h1 className='catalog__title'>Смартфоны</h1>
        <div className='catalog__container'>
          <ul className='catalog__collection'>
            <li className='catalog__models'>
              <a className='catalog__model-link' href=''>
                Производитель
              </a>
              <ul className='catalog__models'>
                <li className='catalog__model'>
                  <a className='catalog__model-link' href='/catalog'>
                    Apple IPhone
                  </a>
                </li>
                <li className='catalog__model'>
                  <a className='catalog__model-link' href='/catalog'>
                    Samsung Galaxy
                  </a>
                </li>
                <li className='catalog__model'>
                  <a className='catalog__model-link' href=''>
                    Xiaomi
                  </a>
                </li>
                <li className='catalog__model'>
                  <a className='catalog__model-link' href=''>
                    Google
                  </a>
                </li>
                <li className='catalog__model'>
                  <a className='catalog__model-link' href=''>
                    Honor
                  </a>
                </li>
                <li className='catalog__model'>
                  <a className='catalog__model-link' href=''>
                    Realme
                  </a>
                </li>
                <li className='catalog__model'>
                  <a className='catalog__model-link' href=''>
                    HUAWEI
                  </a>
                </li>
                <li className='catalog__model'>
                  <a className='catalog__model-link' href=''>
                    OnePlus
                  </a>
                </li>
              </ul>
            </li>
            <ul className='catalog__models'>
              <a className='catalog__model-link' href=''>
                Кнопочные телефоны
              </a>
            </ul>
            <ul className='catalog__models'>
              <li className='catalog__models'>
                <a className='catalog__model-link' href=''>
                  Аксессуары
                </a>
              </li>
              <li className='catalog__model'>
                <a className='catalog__model-link' href=''>
                  Зарядные устройства
                </a>
              </li>
              <li className='catalog__model'>
                <a className='catalog__model-link' href=''>
                  Кабели и адаптеры
                </a>
              </li>
              <li className='catalog__model'>
                <a className='catalog__model-link' href=''>
                  Защитные пленки и стекла
                </a>
              </li>
              <li className='catalog__model'>
                <a className='catalog__model-link' href=''>
                  Наушники
                </a>
              </li>
              <li className='catalog__model'>
                <a className='catalog__model-link' href=''>
                  Чехлы
                </a>
              </li>
            </ul>
          </ul>
          <ul className='catalog__rendered-categories'>
            <div className='catalog__render-cat'>
              {subCategoriesList.slice(0, 9).map((tile) => (
                <CategoriesTile
                  key={tile.id}
                  catTitle={tile.catTitle}
                  catImg={tile.catImg}
                  catUrl={tile.catUrl}
                />
              ))}
            </div>
            <h2 className='catalog__popular'>Популярные товары</h2>
            <div className='catalog__render-popular'>
              {product.slice(0, 6).map((product) => (
                <ProductCardMedium
                  key={product.id}
                  name={product.name}
                  originPrice={product.originPrice}
                  salesPrice={product.salesPrice}
                  discount={product.discount}
                />
              ))}
            </div>
          </ul>
        </div>
      </div>
    </section>
  );
};

export default Categories;
