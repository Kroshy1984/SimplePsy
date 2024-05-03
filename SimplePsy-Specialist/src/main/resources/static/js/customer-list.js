function viewSelectedCard() {

    var selectedRadioButton = document.querySelector('input[name="selectedItem"]:checked');

    if (selectedRadioButton) {

        var selectedCardId = selectedRadioButton.value;

        var viewUrl = '/SimplePsySpecialist/V1/specialist/customer-card/' + selectedCardId;

        window.location.href = viewUrl;
    } else {
        alert('Выберите карточку перед нажатием "Посмотреть".');
    }
}

function deleteById(obj) {
    var customerId = obj.value

    var url = 'http://localhost:8081/SimplePsySpecialist/V1/specialist/delete-customer/' + customerId;

    var token = document.querySelector("[name='_csrf']").value;

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': token
        },
        body: JSON.stringify({customerId}),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(data => {
            // Обработка успешного ответа
            console.log('POST request successful', data);
            window.location.href = "/SimplePsySpecialist/V1/specialist/customers"
        })
        .catch(error => {
            // Обработка ошибки
            console.error('POST request failed', error);
        });
}

function deleteSelectedCard()
{
    var selectedRadioButton = document.querySelector('input[name="selectedItem"]:checked');

    if (selectedRadioButton) {

        var selectedCardId = selectedRadioButton.value;

        var url = 'http://localhost:8081/SimplePsySpecialist/V1/specialist/delete-customer/' + selectedCardId;

        var token = document.querySelector('input[name="_csrf"]').value;

        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': token
            },
            body: JSON.stringify({selectedCardId}),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then(data => {
                // Обработка успешного ответа
                console.log('POST request successful', data);
                window.location.href = "/SimplePsySpecialist/V1/specialist/customers"
            })
            .catch(error => {
                // Обработка ошибки
                console.error('POST request failed', error);
            });

    } else {
        alert('Выберите карточку перед нажатием "Посмотреть".');
    }
}

function addNewCustomer() {
    window.location.href = '/SimplePsySpecialist/V1/specialist/customer-form';
}