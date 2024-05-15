function approveScoring() {
    let problemId = document.getElementById("problemId").value;
    window.location.href = '/SimplePsySpecialist/V1/specialist/scoring/approve/' + problemId;
}

function showCheckboxQuestions() {
    document.getElementById('textQuestions').style.display = 'none';
    document.getElementById('checkboxQuestions').style.display = 'block';
}

function showTextQuestions() {
    document.getElementById('checkboxQuestions').style.display = 'none';
    document.getElementById('textQuestions').style.display = 'block';
}

function goBack() {
    let customerId = document.getElementById("customerId").value;
    window.location.href = '/SimplePsySpecialist/V1/specialist/customer/problems/' + customerId;
}