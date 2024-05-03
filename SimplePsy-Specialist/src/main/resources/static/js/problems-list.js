function goBack() {
    var id = document.getElementById("customerIdInput").value;
    window.location.href = 'http://localhost:8081/SimplePsySpecialist/V1/specialist/customer-card/' + id;
}

function addNewProblem() {
    var id = document.getElementById("customerIdInput").value;
    window.location.href = "http://localhost:8081/SimplePsySpecialist/V1/specialist/customer/problem/new/" + id;
}

function copyUrl() {

    var selectedRadioButton = document.querySelector('input[name="selectedItem"]:checked');


    if (selectedRadioButton) {

        var selectedProblemId = selectedRadioButton.value;


        var viewUrl = 'http://localhost:8084/SimplePsyScoring/V1/scoring/' + selectedProblemId;

    } else {
        alert('Выберите проблемы перед копированием ссылки.');
    }
    navigator.clipboard.writeText(viewUrl).then(() => alert("Ссылка скопирована"));
}