/* eslint-disable @typescript-eslint/strict-boolean-expressions */
import React from 'react';
import { Link } from 'react-router-dom';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import PopupTemplate from '../PopupTemplate/PopupTemplate';

interface SignInProps {
  onOpenSignIn: () => void;
  isOpenSignIn: boolean;
  onOpenReg: () => void;
  onLogin: (email: string, password: string) => void;
}

const SignIn: React.FC<SignInProps> = ({
  onOpenSignIn,
  isOpenSignIn,
  onOpenReg,
  onLogin,
}) => {
  const formik = useFormik({
    initialValues: {
      enableReinitialize: true,
      loginAuth: '',
      passwordAuth: '',
    },
    validationSchema: Yup.object({
      loginAuth: Yup.string()
        .email('Введите корректный email')
        .matches(
          /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/,
          'Некорректный формат email'
        )
        .required('Введите email'),
      passwordAuth: Yup.string()
        .min(6, 'Пароль должен содержать не менее 6 символов')
        .max(8, 'Пароль не должен превышать 8 символов')
        .required('Введите Ваш пароль'),
    }),
    onSubmit: (values) => {
      onLogin(values.loginAuth, values.passwordAuth);
      onOpenSignIn();
      formik.resetForm();
    },
  });

  const handleOpenReg = (): void => {
    onOpenSignIn();
    onOpenReg();
    formik.resetForm();
  };

  const handleCloseSignInPopup = (): void => {
    onOpenSignIn();
    formik.resetForm();
  };

  return (
    <PopupTemplate
      isOpen={isOpenSignIn}
      OnClose={handleCloseSignInPopup}
      popupClass='popup'
      popupClassOverlay='popup_overlay'
    >
      <div className='signin__container'>
        <button
          className='signin__button_cls'
          onClick={handleCloseSignInPopup}
        />
        <form
          className='signin__form'
          onSubmit={formik.handleSubmit}
          noValidate
        >
          <div className='signin__title-container'>
            <div className='signin__title'>Введите логин и пароль</div>
            <p className='signin__subtitle'>
              Авторизируйтесь, чтобы совершить покупку или наполнить корзину
            </p>
          </div>
          <div className='signin__inputs'>
            <input
              className={`signin__input ${
                formik.touched.loginAuth && formik.errors.loginAuth
                  ? 'signin__input_invalid'
                  : formik.touched.loginAuth
                  ? 'signin__input_valid'
                  : ''
              }`}
              placeholder='E-mail'
              type='email'
              name='loginAuth'
              value={formik.values.loginAuth}
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
              minLength={6}
              required
            />
            {formik.touched.loginAuth && formik.errors.loginAuth && (
              <span className='signin__error-text'>
                {formik.errors.loginAuth}
              </span>
            )}
            <input
              className={`signin__input ${
                formik.touched.passwordAuth && formik.errors.passwordAuth
                  ? 'signin__input_invalid'
                  : formik.touched.passwordAuth
                  ? 'signin__input_valid'
                  : ''
              }`}
              placeholder='Пароль'
              type='password'
              minLength={6}
              maxLength={10}
              name='passwordAuth'
              value={formik.values.passwordAuth}
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
              required
            />
            {formik.touched.passwordAuth && formik.errors.passwordAuth && (
              <span className='signin__error-text'>
                {formik.errors.passwordAuth}
              </span>
            )}
            <Link className='signin__link' to='/'>
              Не помню пароль
            </Link>
          </div>
          <div className='signin__buttons'>
            <button
              className='signin__button signin__button_enter'
              disabled={!(formik.dirty && formik.isValid)}
              type='submit'
            >
              Войти
            </button>
            <button
              className='signin__button signin__button_registr'
              onClick={handleOpenReg}
            >
              Регистрация
            </button>
          </div>
        </form>
      </div>
    </PopupTemplate>
  );
};

export default SignIn;

/*
return (
    <PopupTemplate
      isOpen={isOpenSignIn}
      OnClose={onOpenSignIn}
      popupClass='popup'
      popupClassOverlay='popup_overlay'
    >
      <div className='signin__container'>
        <button className='signin__button_cls' onClick={onOpenSignIn} />
        <form
          className='signin__form'
          onSubmit={formik.handleSubmit}
          noValidate
        >
          <div className='signin__title-container'>
            <div className='signin__title'>Введите логин и пароль</div>
            <p className='signin__subtitle'>
              Авторизируйтесь, чтобы совершить покупку или наполнить корзину
            </p>
          </div>
          <div className='signin__inputs'>
            <input
              className={`signin__input ${
                formik.dirty && !formik.errors.loginAuth
                  ? 'signin__input_valid'
                  : formik.touched.loginAuth
                  ? 'signin__input_invalid'
                  : ''
              }`}
              placeholder='E-mail'
              type='email'
              name='loginAuth'
              value={formik.values.loginAuth}
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
              minLength={6}
              required
            />
            {formik.touched.loginAuth && formik.errors.loginAuth && (
              <span className='signin__error-text'>
                {formik.errors.loginAuth}
              </span>
            )}
            <input
              className={`signin__input ${
                formik.dirty && !formik.errors.passwordAuth
                  ? 'signin__input_valid'
                  : formik.touched.passwordAuth
                  ? 'signin__input_invalid'
                  : ''
              }`}
              placeholder='Пароль'
              type='password'
              minLength={6}
              maxLength={10}
              name='passwordAuth'
              value={formik.values.passwordAuth}
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
              required
            />
            {formik.touched.passwordAuth && formik.errors.passwordAuth && (
              <span className='signin__error-text'>
                {formik.errors.passwordAuth}
              </span>
            )}
            <Link className='signin__link' to='/'>
              Не помню пароль
            </Link>
          </div>
          <div className='signin__buttons'>
            <button
              className='signin__button signin__button_enter'
              disabled={!(formik.dirty && formik.isValid)}
              type='submit'
            >
              Войти
            </button>
            <button
              className='signin__button signin__button_registr'
              onClick={handleOpenReg}
            >
              Регистрация
            </button>
          </div>
        </form>
      </div>
    </PopupTemplate>
  );
};
*/
