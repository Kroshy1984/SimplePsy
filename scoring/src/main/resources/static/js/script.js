// Инициализация значений переменных из localStorage (или sessionStorage)
let userFormAnswers = JSON.parse(localStorage.getItem('userFormAnswers'));
let textAnswers = JSON.parse(localStorage.getItem('textAnswers'));
let checkboxAnswers = JSON.parse(localStorage.getItem('checkboxAnswers'));

function collectUserFormAnswers() {
    userFormAnswers = Array.from(document.querySelectorAll('input[type="text"]')).map(input => input.value);
    localStorage.setItem('userFormAnswers', JSON.stringify(userFormAnswers));
    window.location.href = 'http://localhost:8084/SimplePsyScoring/V1/scoring/textQuestions';
}

function collectTextAnswers() {
    textAnswers = Array.from(document.querySelectorAll('input[type="text"]')).map(input => input.value);
    localStorage.setItem('textAnswers', JSON.stringify(textAnswers));
    window.location.href = 'http://localhost:8084/SimplePsyScoring/V1/scoring/checkboxQuestions';
}

function openUserFormPage() {
    window.location.href = 'http://localhost:8084/SimplePsyScoring/V1/scoring/userForm';
}

function openTextQuestionsPage() {
    window.location.href = 'http://localhost:8084/SimplePsyScoring/V1/scoring/textQuestions';
}

function sendData() {
    checkboxAnswers = Array.from(document.querySelectorAll('input[type="checkbox"]:checked')).map(input => input.value);
    localStorage.setItem('checkboxAnswers', JSON.stringify(checkboxAnswers));

    // Очищаем localStorage после отправки данных, если нужно
    // localStorage.removeItem('userFormAnswers');
    // localStorage.removeItem('textAnswers');
    // localStorage.removeItem('checkboxAnswers');

    let newArray = [].concat(userFormAnswers, textAnswers, checkboxAnswers);
    const url = 'http://localhost:8084/SimplePsyScoring/V1/scoring/saveAnswers';

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
        })
        .catch(error => {
            console.error('Error:', error);
        });
    window.location.href = 'http://localhost:8084/SimplePsyScoring/V1/scoring/done';
}