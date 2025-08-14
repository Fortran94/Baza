    const navLinks = document.querySelectorAll('.nav a');
    navLinks.forEach(link => {
      link.addEventListener('click', () => {
        navLinks.forEach(l => l.classList.remove('active'));
        link.classList.add('active');
      });
    });

    window.addEventListener('DOMContentLoaded', () => {
      document.body.style.opacity = 0;
      document.body.style.transition = 'opacity 1s';
      requestAnimationFrame(() => {
        document.body.style.opacity = 1;
      });
    });

    document.addEventListener('DOMContentLoaded', () => {
      document.querySelectorAll('tbody tr').forEach(row => {
        const href = row.getAttribute('data-href');
        if (href) {
          row.style.cursor = 'pointer';
          row.addEventListener('click', () => {
            window.location.href = href;
          });
        }
      });
    });
