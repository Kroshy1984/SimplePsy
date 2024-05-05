document.getElementById('sessionForm').addEventListener('submit', function(event) {
    event.preventDefault();
    alert('New session was created');
});

function goBack() {
    window.history.go(-1);
    return false;
}