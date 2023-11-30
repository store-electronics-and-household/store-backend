import React, { useState } from 'react';
import { type FaqItem } from '../../utils/types';
import AccordionItem from './AccordionItem';

const Accordion: React.FC<{ faqList: FaqItem[]; headText: string }> = ({
  faqList,
  headText,
}: {
  faqList: FaqItem[];
  headText: string;
}) => {
  const [currentFaqId, setCurrentFaqId] = useState(-1);
  const btnOnClick = (faqId: number): void => {
    setCurrentFaqId((currentValue) => (currentValue !== faqId ? faqId : -1));
  };

  return (
    <div className='accordion__container'>
      <h2 className='accordion__container-head'>{headText}</h2>
      <ul className='accordion__list'>
        {faqList.map((faqItem, faqId) => {
          return (
            <AccordionItem
              faqItem={faqItem}
              key={faqId}
              isQuestionOpen={faqId === currentFaqId}
              btnOnClick={() => {
                btnOnClick(faqId);
              }}
            />
          );
        })}
      </ul>
    </div>
  );
};

export default Accordion;
