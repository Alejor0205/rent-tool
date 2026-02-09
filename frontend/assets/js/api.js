(function (global) {
  const BASE_KEY = 'rt.baseUrl';
  const DEFAULT_BASE = 'http://localhost:8080';

  function getBaseUrl() {
    return localStorage.getItem(BASE_KEY) || DEFAULT_BASE;
  }

  function setBaseUrl(url) {
    localStorage.setItem(BASE_KEY, url);
  }

  async function request(method, path, body, options) {
    const opts = options || {};
    const headers = opts.headers || {};
    const token = global.Auth && global.Auth.getToken();

    if (!(body instanceof FormData)) {
      headers['Content-Type'] = 'application/json';
    }
    headers['Accept'] = opts.accept || 'application/json';
    if (token) {
      headers['Authorization'] = 'Bearer ' + token;
    }

    const fetchOptions = {
      method,
      headers,
      body: body ? (body instanceof FormData ? body : JSON.stringify(body)) : undefined
    };

    const res = await fetch(getBaseUrl() + path, fetchOptions);

    if (res.status === 401) {
      if (global.Auth) {
        global.Auth.logout('Sesión expirada. Vuelve a iniciar.');
      }
      throw new Error('Unauthorized');
    }
    if (res.status === 403) {
      if (global.UI) {
        global.UI.toast('No tienes permisos para esta acción.', 'warning');
      }
      throw new Error('Forbidden');
    }

    if (!res.ok) {
      let message = 'Error inesperado';
      try {
        const data = await res.json();
        message = data.message || JSON.stringify(data);
      } catch (err) {
        message = await res.text();
      }
      if (global.UI) {
        global.UI.toast(message, 'danger');
      }
      throw new Error(message);
    }

    if (opts.responseType === 'blob') {
      return res.blob();
    }
    if (opts.responseType === 'text') {
      return res.text();
    }

    const contentType = res.headers.get('content-type') || '';
    if (contentType.includes('application/json')) {
      return res.json();
    }
    return res.text();
  }

  const apiClient = {
    get: (path, options) => request('GET', path, null, options),
    post: (path, body, options) => request('POST', path, body, options),
    put: (path, body, options) => request('PUT', path, body, options),
    patch: (path, body, options) => request('PATCH', path, body, options),
    delete: (path, options) => request('DELETE', path, null, options)
  };

  global.Api = { apiClient, getBaseUrl, setBaseUrl };
})(window);
