import React from 'react';
import BreadcrumbItem from './BreadcrumbItem';

interface BreadcrumbProps {
  children?: React.ReactNode
  currentPlace: string
  previousPlace: string
  previousPath: string
}

const Breadcrumb: React.FC<BreadcrumbProps> = ({
  children,
  currentPlace,
  previousPlace = '',
  previousPath = ''
}: BreadcrumbProps) => {
  return (
    <ul className='breadcrumb'>
      <BreadcrumbItem
        breadcrumbPath = {previousPath}
        breadcrumbText = {previousPlace}
      />
      {children}
      <li className='breadcrumb__item breadcrumb__item_current'>
        {currentPlace}
      </li>
    </ul>
  );
};

export default Breadcrumb;

// <BreadcrumbItem breadcrumbPath='/' breadcrumbText='Главная' />
