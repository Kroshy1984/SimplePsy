function goBack() {
    var id = document.getElementById("customerIdInput").value;
    window.location.href = 'http://localhost:8081/SimplePsySpecialist/V1/specialist/customer-card/' + id;
}

function addNewProblem() {
    var id = document.getElementById("customerIdInput").value;
    window.location.href = "http://localhost:8081/SimplePsySpecialist/V1/specialist/customer/problem/new/" + id;
}

function showScoring() {

}