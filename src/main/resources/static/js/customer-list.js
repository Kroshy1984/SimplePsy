function viewSelectedCard() {

    var selectedRadioButton = document.querySelector('input[name="selectedItem"]:checked');


    if (selectedRadioButton) {

        var selectedCardId = selectedRadioButton.value;


        var viewUrl = '/SimplePsy/V1/specialist/customer-card/' + selectedCardId;


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

        var url = 'http://localhost:8080/SimplePsy/V1/specialist/' + selectedCardId;

        fetch(url, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json', // Указывайте тип контента, если это необходимо
                // Дополнительные заголовки, если необходимо
            },
            body: JSON.stringify({selectedCardId}),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // Обработка успешного ответа
                console.log('DELETE request successful', data);
            })
            .catch(error => {
                // Обработка ошибки
                console.error('DELETE request failed', error);
            });

    } else {
        alert('Выберите карточку перед нажатием "Посмотреть".');
    }
}