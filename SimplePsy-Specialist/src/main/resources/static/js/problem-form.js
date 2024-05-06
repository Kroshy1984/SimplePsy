document.getElementById('problemForm').addEventListener('submit', function(event) {
    event.preventDefault();
    alert('New problem was created!');
});

function goBack() {
    window.history.go(-1);
    return false;
}