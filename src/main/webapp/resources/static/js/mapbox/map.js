// Initialisation de Mapbox
mapboxgl.accessToken = 'pk.eyJ1IjoidGlyb2lybW9yZ2FuZSIsImEiOiJja2cyOXRqYWkwcDdsMnRwaWtzYWR3Zjc2In0.yp4z_Ui7Ukj7I4p6au750g';

//valeurs initiales
let zoom = 14;
let userLatitude = 42.1880896; // c'est la corse btw
let userLongitude = 9.0684138;

// Création de la carte
const map1 = new mapboxgl.Map({
    container: 'map', // ID du conteneur
    style: 'mapbox://styles/tiroirmorgane/cm4pjeh6h007k01r0fhs20pkd', // Style de la carte
    center: [userLongitude, userLatitude], // Coordonnées (longitude, latitude)
    zoom: zoom // Niveau de zoom
});

// Création de la carte
const map2 = new mapboxgl.Map({
    container: 'map', // ID du conteneur
    style: 'mapbox://styles/tiroirmorgane/cm4pijthd007v01sfhvoad40c', // Style de la carte
    center: [userLongitude, userLatitude], // Coordonnées (longitude, latitude)
    zoom: zoom // Niveau de zoom
});

var map = map1;

// Mettre à jour la carte avec les nouvelles coordonnées
function updateMap(coordinates) {
    map.flyTo({
        center: [coordinates.lon, coordinates.lat], // Coordonnées (longitude, latitude)
        essential: true, // Animation essentielle
        zoom: zoom // Conserve le niveau de zoom actuel
    });
}

// Désactiver toutes les interactions
map.scrollZoom.disable();
map.boxZoom.disable();
map.dragPan.disable();
map.dragRotate.disable();
map.keyboard.disable();
map.doubleClickZoom.disable();
map.touchZoomRotate.disable();

let copyrights = document.getElementsByClassName("mapboxgl-ctrl-bottom-right")[0];
let watermark = document.getElementsByClassName("mapboxgl-ctrl-bottom-left")[0];

copyrights.remove();
watermark.remove();