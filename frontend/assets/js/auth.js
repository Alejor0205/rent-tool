(function (global) {
  const TOKEN_KEY = 'rt.token';
  const ROLE_KEY = 'rt.role';
  const USER_KEY = 'rt.userId';
  const EXP_KEY = 'rt.exp';

  let expTimer = null;

  function padBase64(input) {
    const pad = input.length % 4;
    if (pad === 0) return input;
    return input + '='.repeat(4 - pad);
  }

  function decodeJwt(token) {
    try {
      const payload = token.split('.')[1];
      const padded = padBase64(payload.replace(/-/g, '+').replace(/_/g, '/'));
      const json = atob(padded);
      return JSON.parse(json);
    } catch (err) {
      return null;
    }
  }

  function persistAuth(data) {
    localStorage.setItem(TOKEN_KEY, data.token);
    localStorage.setItem(USER_KEY, data.userId || '');
    localStorage.setItem(ROLE_KEY, data.role || '');

    const decoded = decodeJwt(data.token);
    if (decoded && decoded.exp) {
      localStorage.setItem(EXP_KEY, String(decoded.exp));
    } else {
      localStorage.removeItem(EXP_KEY);
    }

    startAutoLogout();
  }

  function startAutoLogout() {
    if (expTimer) {
      clearInterval(expTimer);
    }
    expTimer = setInterval(() => {
      const exp = Number(localStorage.getItem(EXP_KEY));
      if (!exp) {
        return;
      }
      const now = Math.floor(Date.now() / 1000);
      if (now >= exp) {
        logout('Tu sesión ha expirado.');
      }
    }, 10000);
  }

  function isTokenExpired(token) {
    const decoded = decodeJwt(token);
    if (!decoded || !decoded.exp) return false;
    const now = Math.floor(Date.now() / 1000);
    return now >= decoded.exp;
  }

  async function login(payload) {
    const data = await global.Api.apiClient.post('/api/auth/login', payload);
    persistAuth(data);
    return data;
  }

  async function register(payload) {
    const data = await global.Api.apiClient.post('/api/auth/register', payload);
    persistAuth(data);
    return data;
  }

  function logout(message) {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem(ROLE_KEY);
    localStorage.removeItem(USER_KEY);
    localStorage.removeItem(EXP_KEY);
    if (message && global.UI) {
      global.UI.toast(message, 'warning');
    }
    if (global.Router) {
      global.Router.goToLogin();
    } else {
      window.location.href = '../pages/login.html';
    }
  }

  function getToken() {
    return localStorage.getItem(TOKEN_KEY);
  }

  function getRole() {
    return localStorage.getItem(ROLE_KEY);
  }

  function getUserId() {
    return localStorage.getItem(USER_KEY);
  }

  global.Auth = {
    login,
    register,
    logout,
    getToken,
    getRole,
    getUserId,
    decodeJwt,
    startAutoLogout,
    isTokenExpired
  };
})(window);
