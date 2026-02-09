(function () {
  async function loadComponents() {
    const sidebarHost = document.getElementById('rt-sidebar');
    const topbarHost = document.getElementById('rt-topbar');
    const bottomHost = document.getElementById('rt-bottom');
    if (!sidebarHost || !topbarHost || !bottomHost) return;

    const base = (window.Router && Router.FRONTEND_BASE) ? Router.FRONTEND_BASE : '/frontend';
    const res = await fetch(base + '/pages/components.html');
    const html = await res.text();
    const doc = new DOMParser().parseFromString(html, 'text/html');

    const role = sidebarHost.getAttribute('data-role');
    const section = sidebarHost.getAttribute('data-section');

    const sidebarTemplate = doc.getElementById(`sidebar-${role.toLowerCase()}`);
    const bottomTemplate = doc.getElementById(`bottom-${role.toLowerCase()}`);
    const topbarTemplate = doc.getElementById('topbar');

    if (sidebarTemplate) {
      sidebarHost.innerHTML = sidebarTemplate.innerHTML;
    }
    if (bottomTemplate) {
      bottomHost.innerHTML = bottomTemplate.innerHTML;
    }
    if (topbarTemplate) {
      topbarHost.innerHTML = topbarTemplate.innerHTML;
      const title = topbarHost.getAttribute('data-title') || '';
      const subtitle = topbarHost.getAttribute('data-subtitle') || '';
      const titleEl = topbarHost.querySelector('[data-title]');
      const subtitleEl = topbarHost.querySelector('[data-subtitle]');
      if (titleEl) titleEl.textContent = title;
      if (subtitleEl) subtitleEl.textContent = subtitle;
    }

    const allLinks = document.querySelectorAll('[data-section]');
    allLinks.forEach(link => {
      if (link.getAttribute('data-section') === section) {
        link.classList.add('active');
      } else {
        link.classList.remove('active');
      }
    });

    const logoutBtn = document.getElementById('logoutBtn');
    if (logoutBtn && window.Auth) {
      logoutBtn.addEventListener('click', () => Auth.logout());
    }
  }

  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', loadComponents);
  } else {
    loadComponents();
  }
})();
