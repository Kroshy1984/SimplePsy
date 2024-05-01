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

function deleteSelectedCard()
{
    var selectedRadioButton = document.querySelector('input[name="selectedItem"]:checked');

    if (selectedRadioButton) {

        var selectedCardId = selectedRadioButton.value;

        var url = 'http://localhost:8080/SimplePsy/V1/customer/' + selectedCardId;

        fetch(url, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
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
                console.log('DELETE request successful', data);
                window.location.href = "/SimplePsySpecialist/V1/specialist/customers"
            })
            .catch(error => {
                // Обработка ошибки
                console.error('DELETE request failed', error);
            });

    } else {
        alert('Выберите карточку перед нажатием "Посмотреть".');
    }
}

function addNewCustomer() {
    window.location.href = '/SimplePsySpecialist/V1/specialist/customer-form';
}