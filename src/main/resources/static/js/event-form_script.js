    function toggleResultField() {
            const typeSelect = document.getElementById('type');
            const resultField = document.getElementById('result-field');
            if (typeSelect.value === 'Tournament') {
                resultField.style.display = 'block';
            } else {
                resultField.style.display = 'none';
            }
    }

    document.addEventListener("DOMContentLoaded", function () {
        const typeSelect = document.getElementById('type');
        typeSelect.addEventListener('change', toggleResultField);
        toggleResultField(); // показать/скрыть при первой загрузке
    });

    const form = document.getElementById('addParticipantForm');
    form.addEventListener('submit', (e) => {
      e.preventDefault();

      const data = {
        name: form.name.value.trim(),
        callsign: form.callsign.value.trim(),
        age: parseInt(form.age.value, 10),
        status: form.status.value
      };

      // Тут можно отправить данные на сервер через fetch/ajax
      alert(`Добавлен участник:\nИмя: ${data.name}\nПозывной: ${data.callsign}\nВозраст: ${data.age}\nСтатус: ${data.status}`);

      form.reset();
    });

     // Уведомление при нажатии на кнопки
    const buttons = document.querySelectorAll('.btn');
    buttons.forEach(button => {
      button.addEventListener('click', () => {
        showToast(`Мероприятие добавлено!`);
      });
    });

    // Простое уведомление
    function showToast(text) {
      const toast = document.createElement('div');
      toast.textContent = text;
      toast.style.position = 'fixed';
      toast.style.bottom = '20px';
      toast.style.right = '20px';
      toast.style.backgroundColor = '#0f0';
      toast.style.color = '#000';
      toast.style.padding = '10px 20px';
      toast.style.borderRadius = '8px';
      toast.style.fontWeight = 'bold';
      toast.style.boxShadow = '0 0 10px #0f0';
      toast.style.opacity = '0';
      toast.style.transition = 'opacity 0.5s ease';

      document.body.appendChild(toast);
      setTimeout(() => toast.style.opacity = '1', 100);

      setTimeout(() => {
        toast.style.opacity = '0';
        setTimeout(() => toast.remove(), 500);
      }, 3000);
    }
