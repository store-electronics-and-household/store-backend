import React from 'react';
import ReactModal from 'react-modal';
import { type PopupProps } from '../../utils/types';
import './PopupTemplate.css';

const PopupTemplate: React.FC<PopupProps> = ({
  isOpen,
  OnClose,
  popupClass,
  popupClassOverlay,
  children,
}) => {
  return (
    <ReactModal
      isOpen={isOpen}
      onRequestClose={OnClose}
      className={popupClass}
      overlayClassName={popupClassOverlay}
    >
      {children}
    </ReactModal>
  );
};

export default PopupTemplate;
