let inputPass = document.getElementById('password');
let inputCheck = document.getElementById('check');

inputCheck.addEventListener('change', function () {
    inputPass.type = this.checked ? 'text' : 'password';
});