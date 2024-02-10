function sendData() {
    const answers = Array.from(document.querySelectorAll('input[type="checkbox"]:checked')).map(input => input.value);
    const url = 'http://localhost:8084/SimplePsyScoring/V1/scoring/checkboxQuestions';

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(answers)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
    window.location.href =
        'http://localhost:8084/SimplePsyScoring/V1/scoring/done'

}