(function (global) {
  function goToLogin() {
    window.location.href = '../pages/login.html';
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
      window.location.href = '../index.html?err=forbidden';
    }
  }

  function routeFromRole(role) {
    if (role === 'ADMIN') return '../pages/admin-users.html';
    if (role === 'PROVIDER') return '../pages/provider-tools.html';
    if (role === 'CUSTOMER') return '../pages/client-explore.html';
    return '../pages/login.html';
  }

  function redirectLanding() {
    const token = global.Auth.getToken();
    if (!token) {
      window.location.href = './pages/login.html';
      return;
    }
    if (global.Auth.isTokenExpired(token)) {
      global.Auth.logout('Tu sesión ha expirado.');
      return;
    }
    const role = global.Auth.getRole();
    window.location.href = routeFromRole(role);
  }

  global.Router = { guard, routeFromRole, redirectLanding, goToLogin };
})(window);
