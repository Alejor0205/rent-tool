(function (global) {
  const FRONTEND_BASE = '/frontend';

  function goToLogin() {
    window.location.href = FRONTEND_BASE + '/pages/login.html';
  }

  function guard(allowedRoles) {
    const token = global.Auth.getToken();
    if (!token) {
      goToLogin();
      return;
    }

    if (global.Auth.isTokenExpired(token)) {
      global.Auth.logout('Tu sesión ha expirado.');
      return;
    }

    global.Auth.startAutoLogout();

    const role = global.Auth.getRole();
    if (allowedRoles && allowedRoles.length && !allowedRoles.includes(role)) {
      window.location.href = FRONTEND_BASE + '/index.html?err=forbidden';
    }
  }

  function routeFromRole(role) {
    if (role === 'ADMIN') return FRONTEND_BASE + '/pages/admin-users.html';
    if (role === 'PROVIDER') return FRONTEND_BASE + '/pages/provider-tools.html';
    if (role === 'CUSTOMER') return FRONTEND_BASE + '/pages/client-explore.html';
    return FRONTEND_BASE + '/pages/login.html';
  }

  function redirectLanding() {
    const token = global.Auth.getToken();
    if (!token) {
      window.location.href = FRONTEND_BASE + '/pages/login.html';
      return;
    }
    if (global.Auth.isTokenExpired(token)) {
      global.Auth.logout('Tu sesión ha expirado.');
      return;
    }
    const role = global.Auth.getRole();
    window.location.href = routeFromRole(role);
  }

  global.Router = { guard, routeFromRole, redirectLanding, goToLogin, FRONTEND_BASE };
})(window);
