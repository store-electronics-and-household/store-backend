import React from 'react';
import { Link } from 'react-router-dom';

interface BreadcrumbItemProps {
  breadcrumbText: string
  breadcrumbPath: string
}

const BreadcrumbItem: React.FC<BreadcrumbItemProps> = ({
  breadcrumbText,
  breadcrumbPath,
}: BreadcrumbItemProps) => {
  return (
    <li className='breadcrumb__item'>
      <Link className='breadcrumb__item-link' to={breadcrumbPath}>
        {breadcrumbText}
      </Link>
    </li>
  );
};

export default BreadcrumbItem;
