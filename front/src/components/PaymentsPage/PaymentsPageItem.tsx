import React from 'react';

interface PaymentsPageItemProps {
  quantity: number
  imgUrl: string
}

const PaymentsPageItem: React.FC<PaymentsPageItemProps> = ({ quantity, imgUrl }) => {
  return (
    <div className='payments-page__good'>
      <img
        className='payments-page__good-photo'
        src={imgUrl}
        alt='фото товара'
      />
      <p className='payments-page__good-numbers'>x{quantity}</p>
    </div>
  );
};

export default PaymentsPageItem;

//   const [clientData, setClientData] = React.useState({});
