const box = document.getElementById("role-box-3");
const section = document.getElementById("supplier-section")

if (box.checked == true) {
    section.classList.remove('d-none');
}

box.addEventListener("click", function() {

    if (box.checked == true) {
        section.classList.remove('d-none');
    } else {
        section.classList.add('d-none');
    }
});