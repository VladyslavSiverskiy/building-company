function changeLanguage() {
    let langSelect = document.getElementById("lang");
    let selectedLang = langSelect.options[langSelect.selectedIndex].value;
    window.location.href = '/changeLanguage?lang=' + selectedLang;
}