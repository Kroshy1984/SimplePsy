function goBack() {
    var id = document.getElementById("customerIdInput").value;
    window.location.href = '/SimplePsySpecialist/V1/specialist/customer-card/' + id;
}

function addNewProblem() {
    var id = document.getElementById("customerIdInput").value;
    window.location.href = '/SimplePsySpecialist/V1/specialist/customer/problem/new/' + id;
}

function copyUrl() {

    const scoringUrl = document.getElementById("scoringUrl").value;

    var selectedRadioButton = document.querySelector('input[name="selectedItem"]:checked');

    if (selectedRadioButton) {

        var selectedProblemId = selectedRadioButton.value;

        var viewUrl =  'http://localhost:8084/SimplePsyScoring/V1/scoring/' + selectedProblemId;

        navigator.clipboard.writeText(viewUrl).then(() => alert("Ссылка скопирована"));
    } else {
        alert('Выберите проблему перед копированием ссылки!');
    }
}

function showScoring() {
    var selectedRadioButton = document.querySelector('input[name="selectedItem"]:checked');


    if (selectedRadioButton) {

        var selectedProblemId = selectedRadioButton.value;


        var viewUrl = '/SimplePsySpecialist/V1/specialist/customer/scoring/' + selectedProblemId;


        window.location.href = viewUrl;
    } else {
        alert('Выберите карточку перед нажатием "Посмотреть".');
    }
}