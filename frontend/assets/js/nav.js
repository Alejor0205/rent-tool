(function () {
  function basename(path) {
    const clean = path.split('?')[0].split('#')[0];
    const parts = clean.split('/');
    return parts[parts.length - 1];
  }

  function setActiveLinks() {
    const current = basename(window.location.pathname);
    const links = document.querySelectorAll('.rt-nav a, .rt-bottom-nav a');
    links.forEach(link => {
      const href = link.getAttribute('href') || '';
      const target = basename(href);
      if (target && target === current) {
        link.classList.add('active');
      } else {
        link.classList.remove('active');
      }
    });
  }

  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', setActiveLinks);
  } else {
    setActiveLinks();
  }
})();
