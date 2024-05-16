function approveScoring() {
    let problemId = document.getElementById("problemId").value;
    let token = document.querySelector("[name='_csrf']").value;
    let url = '/SimplePsySpecialist/V1/specialist/scoring/approve/' + problemId;
    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': token
        },

    }).then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
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