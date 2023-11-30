import React from 'react';
import { Link } from 'react-router-dom';
// import './Discount.css';
import DISCOUNTBANNER_1 from '../../image/discountBanner_1.svg';
import DISCOUNTBANNER_2 from '../../image/discountBanner_2.svg';
import DISCOUNTBANNER_3 from '../../image/discountBanner_3.svg';
import DISCOUNTBANNER_4 from '../../image/discountBanner_4.svg';

const Discount: React.FC = () => {
  return (
    <section className='discount'>
      <Link className='discount__container' rel='noreferrer' to='#'>
        <img src={DISCOUNTBANNER_1} alt='баннер' className='discount__offer' />
      </Link>
      <Link className='discount__container' rel='noreferrer' to='#'>
        <img src={DISCOUNTBANNER_2} alt='баннер' className='discount__offer' />
      </Link>
      <Link className='discount__container' rel='noreferrer' to='#'>
        <img src={DISCOUNTBANNER_3} alt='баннер' className='discount__offer' />
        <img src={DISCOUNTBANNER_4} alt='баннер' className='discount__offer' />
      </Link>
    </section>
  );
};

export default Discount;
