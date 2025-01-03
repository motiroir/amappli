let switcher = document.getElementById("switch");
let body = document.body;

let cname = "amappliTheme";
let ctheme = getCookie("amappliTheme");
let today = new Date();
let expiry = new Date(today.getTime() + 31 * 24 * 3600 * 1000); // plus 31 days

switcher.addEventListener("click", function() {
    
    if (switcher.checked == true) {
        body.classList.remove('light');
        body.classList.add('dark');
		map.setStyle(styleMapboxDark);
        document.cookie = "amappliTheme=dark; path=/; SameSite=Strict; expires=" + expiry.toUTCString();
		ctheme = getCookie("amappliTheme");
    } else {
        body.classList.remove('dark');
        body.classList.add('light');
		map.setStyle(styleMapboxLight);
        document.cookie = "amappliTheme=light; path=/; SameSite=Strict; expires=" + expiry.toUTCString();
		ctheme = getCookie("amappliTheme");
    }
});

if (ctheme != null) {
    if (ctheme == "dark") {
        body.classList.remove('light');
        body.classList.add('dark');
		switcher.setAttribute("checked",true);
        map.setStyle(styleMapboxDark);
		document.cookie = "amappliTheme=dark; path=/; SameSite=Strict; expires=" + expiry.toUTCString();
    } else {
        body.classList.remove('dark');
        body.classList.add('light');
        map.setStyle(styleMapboxLight);
        document.cookie = "amappliTheme=light; path=/; SameSite=Strict; expires=" + expiry.toUTCString();
    }
} else {
	    document.cookie = "amappliTheme=dark; path=/; SameSite=Strict; expires=" + expiry.toUTCString();
}


function setCookie(name, value, expire) {

    document.cookie = name + "=" + value + "; path=/; SameSite=Strict; expires=" + expire.toUTCString();

}

function getCookie(name) {

    var re = new RegExp(name + "=([^;]+)");
    var value = re.exec(document.cookie);
    return (value != null) ? value[1] : null;

}


function deleteCookie(name) {

    document.cookie = name + "=null; path=/; expires=" + expired.toGMTString();

}