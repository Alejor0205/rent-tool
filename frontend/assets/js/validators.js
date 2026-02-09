(function (global) {
  function required(value) {
    return value !== undefined && value !== null && String(value).trim() !== '';
  }

  function email(value) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(String(value));
  }

  function dateRange(start, end) {
    if (!start || !end) return false;
    const s = new Date(start);
    const e = new Date(end);
    return s <= e;
  }

  global.Validators = { required, email, dateRange };
})(window);
