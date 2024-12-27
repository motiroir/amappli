let switcher = document.getElementById("switch");
let body = document.body;

console.log(map);

switcher.addEventListener("click", function(e) {

    if (switcher.checked == true) {
        body.classList.remove('light');
        body.classList.add('dark');
		map.setStyle(styleMapboxDark);
		
    } else {
        body.classList.remove('dark');
        body.classList.add('light');
		map.setStyle(styleMapboxLight);

    }
});