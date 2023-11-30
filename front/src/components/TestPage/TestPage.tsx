import React from 'react';
import { sayHello } from '../../utils/api/api';

const TestPage: React.FC = () => {
  const [currentUser, setCurrentUser] = React.useState<string>('');

  React.useEffect(() => {
    const jwt: string | null = localStorage.getItem('token');
    if (jwt !== null) {
      sayHello(jwt)
        .then((res) => {
          setCurrentUser(res.email);
        })
        .catch((error) => {
          console.log(error.message);
        });
    } else {
      console.log('JWT is null');
    }
  }, []);

  return (
    <section className='test-page'>
      <h1 className='test-title'>{`Привет, ${currentUser}`}</h1>
    </section>
  );
};

export default TestPage;
