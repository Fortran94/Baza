    // Подсветка активного пункта меню
    const navLinks = document.querySelectorAll('.nav a');
    navLinks.forEach(link => {
      link.addEventListener('click', () => {
        navLinks.forEach(l => l.classList.remove('active'));
        link.classList.add('active');
      });
    });

    // Эффект плавного появления
    window.addEventListener('DOMContentLoaded', () => {
      document.body.style.opacity = 0;
      document.body.style.transition = 'opacity 1s';
      requestAnimationFrame(() => {
        document.body.style.opacity = 1;
      });
    });

    // Анимация при наведении на статистику
    const statBoxes = document.querySelectorAll('.stat-box');
    statBoxes.forEach(box => {
      box.addEventListener('mouseover', () => {
        box.style.transform = 'scale(1.05)';
        box.style.boxShadow = '0 0 15px #0f0';
      });
      box.addEventListener('mouseout', () => {
        box.style.transform = 'scale(1)';
        box.style.boxShadow = 'none';
      });
    });


  // Прячем прелоадер после загрузки
  window.addEventListener('load', () => {
    const loader = document.getElementById('loader');
    loader.style.transition = 'opacity 0.8s ease';
    loader.style.opacity = '0';
    setTimeout(() => loader.remove(), 800);
  });



