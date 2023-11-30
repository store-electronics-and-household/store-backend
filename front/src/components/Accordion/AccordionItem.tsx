import React, { useEffect, useRef, useState } from 'react';
import { type FaqItem } from '../../utils/types';
import arrow from '../../image/icons/arr-down.svg';

const AccordionItem: React.FC<{
  faqItem: FaqItem;
  isQuestionOpen: boolean;
  btnOnClick: () => void;
}> = ({
  faqItem,
  isQuestionOpen,
  btnOnClick,
}: {
  faqItem: FaqItem;
  isQuestionOpen: boolean;
  btnOnClick: () => void;
}) => {
  const contentRef = useRef<HTMLParagraphElement>(null);
  const [height, setHeight] = useState(0);

  useEffect(() => {
    if (isQuestionOpen) {
      const contentEl = contentRef.current as HTMLParagraphElement;
      setHeight(contentEl?.scrollHeight);
    } else {
      setHeight(0);
    }
  }, [isQuestionOpen]);

  return (
    <li className='accordion__item'>
      <button className='accordion__btn' onClick={btnOnClick}>
        <p className='accordion__question'>{faqItem.q}</p>
        <img
          className={`accordion__arrow ${
            isQuestionOpen ? 'accordion__arrow_opened' : ''
          }`}
          src={arrow}
          alt='Стрелка'
        />
      </button>
      <div className='accordion__answer-wrap' style={{ height }}>
        <p ref={contentRef} className='accordion__answer'>
          {faqItem.a}
        </p>
      </div>
    </li>
  );
};

export default AccordionItem;
