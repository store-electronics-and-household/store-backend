import React from 'react';
import Accordion from '../Accordion/Accordion';
import { faqList } from '../../utils/constants';
import Breadcrumb from '../Breadcrumb/Breadcrumb';

const Faq: React.FC = () => {
  return (
    <>
      <section className='faq'>
        <Breadcrumb
          currentPlace='Часто задаваемые вопросы'
          previousPlace='Главная'
          previousPath='/'
          />
        <div className='faq__container'>
          <h1 className='faq__header'>Частые вопросы</h1>
          <Accordion faqList={faqList.deliveryFaq} headText='Доставка:' />
          <Accordion
            faqList={faqList.characteristicFaq}
            headText='Наличие и характеристики товара:'
          />
          <Accordion
            faqList={faqList.returnsFaq}
            headText='Обмен и возврат товара:'
          />
        </div>
      </section>
    </>
  );
};

export default Faq;
