(function (global) {
  function qs(sel, ctx) {
    return (ctx || document).querySelector(sel);
  }

  function qsa(sel, ctx) {
    return Array.from((ctx || document).querySelectorAll(sel));
  }

  function toast(message, type) {
    const el = document.createElement('div');
    el.className = 'rt-toast';
    el.textContent = message;
    if (type === 'danger') el.style.background = '#b42318';
    if (type === 'warning') el.style.background = '#92400e';
    if (type === 'success') el.style.background = '#0f766e';
    document.body.appendChild(el);
    setTimeout(() => el.remove(), 3200);
  }

  function setLoading(container, isLoading) {
    if (!container) return;
    if (isLoading) {
      container.innerHTML = '<div class="rt-loader" aria-label="Cargando"></div>';
    }
  }

  function formatDate(dateStr) {
    if (!dateStr) return '-';
    const d = new Date(dateStr);
    if (Number.isNaN(d.getTime())) return dateStr;
    return d.toLocaleDateString();
  }

  function paginate(data, pageSize, page) {
    const start = (page - 1) * pageSize;
    return data.slice(start, start + pageSize);
  }

  function renderTable({ container, columns, data, pageSize }) {
    const state = { sortKey: null, sortDir: 'asc', page: 1 };
    pageSize = pageSize || 8;

    function render() {
      if (!data || !data.length) {
        container.innerHTML = '<div class="rt-empty">Sin datos</div>';
        return;
      }
      let rows = data.slice();
      if (state.sortKey) {
        rows.sort((a, b) => {
          const av = a[state.sortKey];
          const bv = b[state.sortKey];
          if (av === bv) return 0;
          if (av === undefined) return 1;
          if (bv === undefined) return -1;
          return state.sortDir === 'asc' ? (av > bv ? 1 : -1) : (av < bv ? 1 : -1);
        });
      }
      const totalPages = Math.ceil(rows.length / pageSize);
      rows = paginate(rows, pageSize, state.page);

      const headers = columns.map(col => {
        const label = col.label || col.key;
        return `<th data-key="${col.key}">${label}</th>`;
      }).join('');

      const body = rows.map(row => {
        const cells = columns.map(col => {
          const val = col.render ? col.render(row) : row[col.key];
          return `<td>${val ?? '-'}</td>`;
        }).join('');
        return `<tr>${cells}</tr>`;
      }).join('');

      const pager = `
        <div class="d-flex justify-content-between align-items-center mt-3">
          <small>Página ${state.page} de ${totalPages}</small>
          <div>
            <button class="btn btn-sm btn-outline-secondary" ${state.page === 1 ? 'disabled' : ''} data-page="prev">Anterior</button>
            <button class="btn btn-sm btn-outline-secondary" ${state.page === totalPages ? 'disabled' : ''} data-page="next">Siguiente</button>
          </div>
        </div>`;

      container.innerHTML = `
        <table class="rt-table">
          <thead><tr>${headers}</tr></thead>
          <tbody>${body}</tbody>
        </table>
        ${pager}`;

      qsa('th', container).forEach(th => {
        th.addEventListener('click', () => {
          const key = th.getAttribute('data-key');
          if (!key) return;
          if (state.sortKey === key) {
            state.sortDir = state.sortDir === 'asc' ? 'desc' : 'asc';
          } else {
            state.sortKey = key;
            state.sortDir = 'asc';
          }
          render();
        });
      });

      qsa('button[data-page]', container).forEach(btn => {
        btn.addEventListener('click', () => {
          const dir = btn.getAttribute('data-page');
          state.page = dir === 'next' ? Math.min(totalPages, state.page + 1) : Math.max(1, state.page - 1);
          render();
        });
      });
    }

    render();
  }

  global.UI = { qs, qsa, toast, setLoading, renderTable, formatDate };
})(window);
