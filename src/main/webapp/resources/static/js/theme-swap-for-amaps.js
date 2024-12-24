let switcher = document.getElementById("switch");
let body = document.body;

console.log(map);

switcher.addEventListener("click", function(e) {

    if (switcher.checked == true) {
        body.classList.remove('light');
        body.classList.add('dark');

        map = new mapboxgl.Map({
            container: 'map', // ID du conteneur
            style: styleMapboxDark, // Style de la carte
            center: [userLongitude, userLatitude], // Coordonnées (longitude, latitude)
            zoom: zoom // Niveau de zoom
        });
    } else {
        body.classList.remove('dark');
        body.classList.add('light');
		
        map = new mapboxgl.Map({
            container: 'map', // ID du conteneur
            style: styleMapboxLight, // Style de la carte
            center: [userLongitude, userLatitude], // Coordonnées (longitude, latitude)
            zoom: zoom // Niveau de zoom
        });
    }
	
	let copyrights = document.getElementsByClassName("mapboxgl-ctrl-bottom-right")[0];
	let watermark = document.getElementsByClassName("mapboxgl-ctrl-bottom-left")[0];
    copyrights.remove();
    watermark.remove();
});