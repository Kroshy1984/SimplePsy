let selectedAnswers = [];

function selectAnswer(questionNumber, answer) {
    selectedAnswers[questionNumber - 1] = answer;
    markSelected(questionNumber);
}

function markSelected(questionNumber) {
    const buttons = document.querySelectorAll(`.question:nth-child(${questionNumber + 1}) .answer`);
    buttons.forEach((button, index) => {
        if (selectedAnswers[questionNumber - 1] === button.innerText.toLowerCase()) {
            button.classList.add("selected");
        } else {
            button.classList.remove("selected");
        }
    });
}