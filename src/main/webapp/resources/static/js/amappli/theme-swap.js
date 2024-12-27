let switcher = document.getElementById("switch");
let body = document.body;

switcher.addEventListener("click", function(e) {

    if (switcher.checked == true) {
        body.classList.remove('light');
        body.classList.add('dark');

        map = map1;
    } else {
        body.classList.remove('dark');
        body.classList.add('light');
        map = map2;
    }
    copyrights.remove();
    watermark.remove();
});