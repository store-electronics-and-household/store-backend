/* eslint-disable @typescript-eslint/prefer-nullish-coalescing */
/* eslint-disable @typescript-eslint/strict-boolean-expressions */
interface ErrorResponse {
  message?: string;
}

const checkResponse = async (res: Response): Promise<any> => {
  if (res.ok) {
    return await res.json();
  } else {
    const errorData: ErrorResponse = await res.json();
    const errorMessage = errorData.message || 'Ошибка сервера';
    throw new Error(`Ошибка: ${res.status}. ${errorMessage}`);
  }
};

const request = async (
  endpoint: string,
  options: RequestInit
): Promise<any> => {
  const url = `http://localhost:8080${endpoint}`;
  return await fetch(url, options).then(checkResponse);
};

export const register = async (
  email: string,
  password: string
): Promise<any> => {
  return await request('/regist', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Accept: 'application/json',
    },
    body: JSON.stringify({ email, password }),
  });
};

export const authorize = async (
  email: string,
  password: string
): Promise<any> => {
  // const base64Credentials = btoa(`${email}:${password}`);
  return await request('/autoriz', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Accept: 'application/json',
      // Authorization: `Basic ${base64Credentials}`
    },
    body: JSON.stringify({ email, password }),
  });
};

export const sayHello = async (jwt: string): Promise<any> => {
  return await request('/hello', {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: `Bearer ${jwt}`,
    },
  });
};
