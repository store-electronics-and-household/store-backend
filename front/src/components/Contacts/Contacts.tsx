import React from 'react';
import mapImg from '../../image/map.png';
import { contactsData } from '../../utils/constants';
import Breadcrumb from '../Breadcrumb/Breadcrumb';

const Contacts: React.FC = () => {
  return (
    <>
      <section className='contacts'>
        <Breadcrumb
          currentPlace='Контакты'
          previousPlace='Главная'
          previousPath='/'
          />
        <div className='contacts__container'>
          <h1 className='contacts__header'>Контакты</h1>
          <div className='contacts__wrap'>
            <p className='contacts__info'>{contactsData.phoneNumber}</p>
            <p className='contacts__phone-time'>
              для заказов с доставкой по территории РФ принимаем звонки с 9:00
              до 22:00 по Мск
            </p>
          </div>
          <div className='contacts__wrap'>
            <p className='contacts__info'>{contactsData.email}</p>
            <p className='contacts__phone-time'>
              для заказов с доставкой по территории РФ принимаем звонки с 9:00
              до 22:00 по Мск
            </p>
          </div>
          <div className='contacts__map-block'>
            <p className='contacts__adress'>{contactsData.adress}</p>
            <img
              className='contacts__map'
              src={mapImg}
              alt='карта проезда, адрес'
            />
          </div>
        </div>
      </section>
    </>
  );
};

export default Contacts;
