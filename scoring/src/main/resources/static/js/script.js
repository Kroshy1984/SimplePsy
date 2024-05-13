// Инициализация значений переменных из localStorage (или sessionStorage)
let userFormAnswers = JSON.parse(localStorage.getItem('userFormAnswers'));
let textAnswers = JSON.parse(localStorage.getItem('textAnswers'));
let checkboxAnswers = JSON.parse(localStorage.getItem('checkboxAnswers'));

function collectUserFormAnswers() {
    userFormAnswers = Array.from(document.querySelectorAll('input[type="text"]')).map(input => input.value);
    localStorage.setItem('userFormAnswers', JSON.stringify(userFormAnswers));
    let scoringId = document.getElementById("scoringId").value;
    console.log(scoringId)
    window.location.href = '/SimplePsyScoring/V1/scoring/textQuestions';
}

function openUserFormPage() {
    window.location.href = '/SimplePsyScoring/V1/scoring/userForm';
}

function openTextQuestionsPage() {
    window.location.href = '/SimplePsyScoring/V1/scoring/textQuestions';
}

function sendData() {
    // From textQuestions
    textAnswers = Array.from(document.querySelectorAll('input[type="text"]')).map(input => input.value);
    localStorage.setItem('textAnswers', JSON.stringify(textAnswers));

    // From checkboxQuestions
    checkboxAnswers = Array.from(document.querySelectorAll('input[type="checkbox"]')).map(function (input) {
        return input.checked ? input.value : "";
    });
    localStorage.setItem('checkboxAnswers', JSON.stringify(checkboxAnswers));

    let problemId = document.getElementById("problemId").value;
    let scoringId = document.getElementById("scoringId").value;
    let newArray = [].concat(textAnswers, checkboxAnswers);
    const scoringUrl = 'http://localhost:8084'
    const clientUrl = 'http://localhost:8086'
    console.log(scoringUrl);
    console.log(clientUrl);
    const url = scoringUrl + '/SimplePsyScoring/V1/scoring/saveAnswers?scoringId=' + scoringId;
    let saveNewClientUrl = clientUrl + '/SimplePsyClient/V1/client/newClient/' + problemId;
    console.log(scoringId)

    localStorage.removeItem('textAnswers');
    localStorage.removeItem('checkboxAnswers');

            var scoringDoneUrl = scoringUrl + '/SimplePsyScoring/V1/scoring/find-customer/byProblemId/' + problemId + "?scoringId=" + scoringId;
            fetch(scoringDoneUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({scoringId : scoringId})
            })
                .then(response => response.text())
                .then(data => {
                    console.log('Success:', data);
                    window.location.href = '/SimplePsyScoring/V1/scoring/done';
                })
                .catch(error => {
                    console.error('Error:', error);
                });
}

function checkInput(input) {
    let question = input.value.trim();
    let pattern = /^[А-Яа-яЁё\s.,!\-?]*$/;
    let errorDiv = input.nextElementSibling;

    if (question.match(pattern)) {
        input.classList.remove('error');
        errorDiv.style.display = 'none';
    } else {
        input.classList.add('error');
        errorDiv.innerText = "Пожалуйста, используйте только буквы русского алфавита";
        errorDiv.style.display = 'block';
    }

    checkFormValidity();
}

function showCheckboxQuestions() {
    document.getElementById('textQuestions').style.display = 'none';
    document.getElementById('checkboxQuestions').style.display = 'block';
}

function showTextQuestions() {
    document.getElementById('checkboxQuestions').style.display = 'none';
    document.getElementById('textQuestions').style.display = 'block';
}