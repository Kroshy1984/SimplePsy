function showButtons() {
    // Находим элементы, которые нужно изменить
    var inputsToEnable = document.querySelectorAll('.profile-container input[disabled]');
    var inputsFieldSetToEnable = document.querySelectorAll('.set input[disabled]');
    var saveButton = document.getElementById('save');
    var deleteButton = document.getElementById('delete');

    // Убираем атрибут disabled у всех найденных элементов
    inputsToEnable.forEach(function (input) {
        input.removeAttribute('disabled');
    });
    inputsFieldSetToEnable.forEach(function (input) {
        input.removeAttribute('disabled');
    });
    // Показываем кнопки
    saveButton.style.display = 'inline-block';
    deleteButton.style.display = 'inline-block';
}

function deleteCard()
{
    var id = document.getElementById("customerIdInput").value;
    var url = 'http://localhost:8080/SimplePsy/V1/customer/' + id;
    fetch(url, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },

    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('DELETE request successful', data);
        })
        .catch(error => {
            console.error('DELETE request failed', error);
        });

}

// TODO: Сделать кнопку отправки уведомления на почту заказчика на скоринг
function copyUrl() {
    var id = document.getElementById("customerIdInput").value;
    var url = 'http://localhost:8084/SimplePsyScoring/V1/scoring/' + id;

    navigator.clipboard.writeText(url).then(() => alert("Ссылка скопирована"));
}