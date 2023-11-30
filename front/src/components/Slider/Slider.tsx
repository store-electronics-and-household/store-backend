import React from 'react';
import { Link } from 'react-router-dom';
import { Swiper, SwiperSlide } from 'swiper/react';
import './Slider.css';
import 'swiper/css';
import 'swiper/css/navigation';
import { Navigation } from 'swiper/modules';
import BANNER from '../../image/banner.png';

const Slider: React.FC = () => {
  return (
    <>
      <Swiper
        navigation={true}
        modules={[Navigation]}
        slidesPerView={1}
        className='swiper-container'
      >
        <SwiperSlide>
          <Link to='#' rel='noreferrer'>
            <img src={BANNER} alt='баннер' className='slider__action-img' />
          </Link>
        </SwiperSlide>
        <SwiperSlide>
          <Link to='#' rel='noreferrer'>
            <img src={BANNER} alt='баннер' className='slider__action-img' />
          </Link>
        </SwiperSlide>
        <SwiperSlide>
          <Link to='#' rel='noreferrer'>
            <img src={BANNER} alt='баннер' className='slider__action-img' />
          </Link>
        </SwiperSlide>
        <SwiperSlide>
          <Link to='#' rel='noreferrer'>
            <img src={BANNER} alt='баннер' className='slider__action-img' />
          </Link>
        </SwiperSlide>
        <SwiperSlide>
          <img src={BANNER} alt='баннер' className='slider__action-img' />
        </SwiperSlide>
      </Swiper>
    </>
  );
};

export default Slider;
