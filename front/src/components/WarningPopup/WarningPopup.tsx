import React from 'react';
import './WarningPopup.css';

interface WarningPopupProps {
  isOpen: boolean;
  onOpenAuth: () => void;
  onOpenWarningPopup: () => void;
}

const WarningPopup: React.FC<WarningPopupProps> = ({
  isOpen,
  onOpenAuth,
  onOpenWarningPopup,
}) => {
  const handleOpenAuth = (): void => {
    onOpenAuth();
    onOpenWarningPopup();
  };
  return (
    <div className={`warningPopup ${isOpen ? '' : 'warningPopup_none'}`}>
      <div className='warning-popup__container'>
        <p className='warning-popup__text'>
          Для добавления товара в корзину необходимо авторизоваться
        </p>
        <button className='warning-popup__cls-btn' onClick={handleOpenAuth}>
          Войти
        </button>
      </div>
    </div>
  );
};

export default WarningPopup;
