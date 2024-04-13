// Инициализация значений переменных из localStorage (или sessionStorage)
let userFormAnswers = JSON.parse(localStorage.getItem('userFormAnswers'));
let textAnswers = JSON.parse(localStorage.getItem('textAnswers'));
let checkboxAnswers = JSON.parse(localStorage.getItem('checkboxAnswers'));

function collectUserFormAnswers() {
    userFormAnswers = Array.from(document.querySelectorAll('input[type="text"]')).map(input => input.value);
    localStorage.setItem('userFormAnswers', JSON.stringify(userFormAnswers));
    window.location.href = 'http://localhost:8084/SimplePsyScoring/V1/scoring/textQuestions';
}

function openUserFormPage() {
    window.location.href = 'http://localhost:8084/SimplePsyScoring/V1/scoring/userForm';
}

function openTextQuestionsPage() {
    window.location.href = 'http://localhost:8084/SimplePsyScoring/V1/scoring/textQuestions';
}

function sendData() {
    // From textQuestions
    textAnswers = Array.from(document.querySelectorAll('input[type="text"]')).map(input => input.value);
    localStorage.setItem('textAnswers', JSON.stringify(textAnswers));

    // From checkboxQuestions
    checkboxAnswers = Array.from(document.querySelectorAll('input[type="checkbox"]:checked')).map(input => input.value);
    localStorage.setItem('checkboxAnswers', JSON.stringify(checkboxAnswers));

    let newArray = [].concat(textAnswers, checkboxAnswers);
    const url = 'http://localhost:8084/SimplePsyScoring/V1/scoring/saveAnswers';

    localStorage.removeItem('textAnswers');
    localStorage.removeItem('checkboxAnswers');

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newArray)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            var name = document.getElementById("name").value;
            var email = document.getElementById("email").value;
            var url = 'http://localhost:8085/emails/scoring';
            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify([name, email])
            })
                .then(response => response.text())
                .then(data => {
                    console.log('Success:', data);
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        })
        .catch(error => {
            console.error('Error:', error);
        });
    window.location.href = 'http://localhost:8084/SimplePsyScoring/V1/scoring/done';
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