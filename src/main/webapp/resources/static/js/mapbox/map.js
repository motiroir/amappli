// Initialisation de Mapbox
mapboxgl.accessToken = 'pk.eyJ1IjoidGlyb2lybW9yZ2FuZSIsImEiOiJja2cyOXRqYWkwcDdsMnRwaWtzYWR3Zjc2In0.yp4z_Ui7Ukj7I4p6au750g';

//valeurs initiales
let zoom = 14;
let userLatitude = 42.1880896; // c'est la corse btw
let userLongitude = 9.0684138;
const fallbackStyle = 'mapbox://styles/tiroirmorgane/cm4pjeh6h007k01r0fhs20pkd'; //dark theme as fallback (when no tenancy)
//checks if styleMabox is defined, if not shows the fallback style (if we don't define it in the jsp file like in amapplis jsp for eg)
const mapStyle = typeof styleMapboxLight !== 'undefined' ? styleMapboxLight : fallbackStyle;

// Création de la carte
const map = new mapboxgl.Map({
    container: 'map', // ID du conteneur
    style: mapStyle, // Style de la carte
    center: [userLongitude, userLatitude], // Coordonnées (longitude, latitude)
    zoom: zoom // Niveau de zoom
});

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