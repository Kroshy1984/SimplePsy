function showCustomers() {
    window.location.href = 'http://localhost:8081/SimplePsySpecialist/V1/specialist/customers';
}

function cancelButton() {
    var id = document.getElementById("customerIdInput").value;
    document.getElementById('cancel').style.display = 'none';
    window.location.href = 'http://localhost:8081/SimplePsySpecialist/V1/specialist/customer-card/' + id;
}

function showButtons() {
    // Находим элементы, которые нужно изменить
    var inputsToEnable = document.querySelectorAll('.profile-container input[disabled]');
    var inputsFieldSetToEnable = document.querySelectorAll('.set input[disabled]');
    var saveButton = document.getElementById('save');
    var deleteButton = document.getElementById('delete');
    var editButton = document.getElementById('edit');
    var cancelButton = document.getElementById('cancel');

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
    editButton.style.display = 'none';
    cancelButton.style.display = 'inline-block';
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

function copyUrl() {
    var id = document.getElementById("customerIdInput").value;
    var url = 'http://localhost:8084/SimplePsyScoring/V1/scoring/' + id;

    navigator.clipboard.writeText(url).then(() => alert("Ссылка скопирована"));
}
function answersWindow()
{
    let id = document.getElementById("customerIdInput").value;
    window.location.href = 'http://localhost:8081/SimplePsySpecialist/V1/specialist/scoring/result' + id;
}

function sendNotification() {
    var id = document.getElementById("customerIdInput").value;
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var url = 'http://localhost:8085/emails/scoring-invitation';
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify([id, name, email])
    })
        .then(response => response.text())
        .then(data => {
            console.log('Success:', data);
            alert("Ссылка была успешно отправлена!");
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function showProblemsList() {
    var id = document.getElementById("customerIdInput").value;
    window.location.href = 'http://localhost:8081/SimplePsySpecialist/V1/specialist/customer/problems/' + id;
}