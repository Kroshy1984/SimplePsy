let selectedAnswers = [];

function selectAnswer(questionNumber, answer) {
    selectedAnswers[questionNumber - 1] = answer;
    markSelected(questionNumber);
}

function markSelected(questionNumber) {
    const buttons = document.querySelectorAll(`.question:nth-of-type(${questionNumber}) .answer`);
    buttons.forEach((button, index) => {
        if (selectedAnswers[questionNumber - 1] === button.innerText.toLowerCase()) {
            button.classList.add("selected");
        } else {
            button.classList.remove("selected");
        }
    });
}

function saveAnswers() {
    fetch('http://localhost:8080/SimplePsy/V1/specialist/saveAnswers', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(selectedAnswers),
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}