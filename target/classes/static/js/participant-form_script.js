  const input = document.getElementById("avatarInput");
  const preview = document.getElementById("preview");

  input.addEventListener("change", function () {
    preview.innerHTML = ""; // очистка
    if (input.files && input.files[0]) {
      const file = input.files[0];
      const img = document.createElement("img");
      img.src = URL.createObjectURL(file);
      img.style.maxWidth = "100px";
      img.style.maxHeight = "100px";
      img.style.border = "1px solid #0f0";
      img.style.borderRadius = "4px";
      img.onload = () => URL.revokeObjectURL(img.src); // чистим URL
      preview.appendChild(img);
    }
  });
