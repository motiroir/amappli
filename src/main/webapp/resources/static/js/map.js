// Initialisation de Mapbox
mapboxgl.accessToken = 'pk.eyJ1IjoidGlyb2lybW9yZ2FuZSIsImEiOiJja2cyOXRqYWkwcDdsMnRwaWtzYWR3Zjc2In0.yp4z_Ui7Ukj7I4p6au750g';

//valeurs initiales
let zoom = 14;
let userLatitude = 42.1880896; // c'est la corse btw
let userLongitude = 9.0684138;

// Création de la carte
const map = new mapboxgl.Map({
    container: 'map', // ID du conteneur
    style: 'mapbox://styles/tiroirmorgane/cm4pjeh6h007k01r0fhs20pkd', // Style de la carte
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



// Performs a geocoding request to the Nominatim API;
async function geocodeLocation(query) {
    try {
        const response = await fetch(`https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(query)}&limit=5`);
        if (!response.ok) throw new Error('Geocoding error');
        return await response.json();
    } catch (error) {
        console.error("Error during geocoding request:", error);
        return [];
    }
}

// Displays location suggestions based on the API response
function displaySuggestions(data, suggestionsList) {
    suggestionsList.innerHTML = '';
    if (data.length > 0) {
        suggestionsList.style.display = 'block';
        data.forEach(item => {
            const listItem = document.createElement('li');
            listItem.classList.add('list-group-item');
            listItem.textContent = item.display_name;
            listItem.addEventListener('click', () => {
                userCoordinates = { lat: parseFloat(item.lat), lon: parseFloat(item.lon) };
                document.getElementById("locationInput").value = item.display_name;
                suggestionsList.style.display = 'none';
                // here save logic ?
            });
            suggestionsList.appendChild(listItem);
        });
    } else {
        suggestionsList.style.display = 'none';
    }
}

// Handles the search logic when the user submits a query
async function handleSearch(event, locationInput, suggestionsList) {
    event.preventDefault();
    const query = locationInput.value.trim();
    if (query) {
        const data = await geocodeLocation(query);
        if (data.length > 0) {
            userCoordinates = { lat: parseFloat(data[0].lat), lon: parseFloat(data[0].lon) };
            // here save logic ?
        } else {
            alert("Location not found. Please try another search.");
        }
        suggestionsList.style.display = 'none';
    } else {
        alert("Please enter a location to search.");
    }
}


// Initializes event listeners when the page loads
document.addEventListener("DOMContentLoaded", () => {
    const locationInput = document.getElementById("locationInput");
    const suggestionsList = document.getElementById("suggestionsList");
    const searchButton = document.getElementById("searchButton");

    // Event listeners pour les suggestions
    locationInput.addEventListener("input", async (event) => {
        const query = event.target.value.trim();
        if (query.length > 2) {
            const data = await geocodeLocation(query);
            displaySuggestions(data, suggestionsList);
        } else {
            suggestionsList.style.display = 'none';
        }
    });

    // Mise à jour de la carte à la sélection d'une suggestion
    suggestionsList.addEventListener("click", (event) => {
        if (event.target.tagName === 'LI') {
            const selectedLocation = event.target;
            const selectedData = selectedLocation.dataset;
            const coordinates = { lat: parseFloat(selectedData.lat), lon: parseFloat(selectedData.lon) };
            updateMap(coordinates);
            locationInput.value = selectedLocation.textContent; // Affiche l'adresse sélectionnée
            suggestionsList.style.display = 'none';
        }
    });

    // Mise à jour de la carte à la recherche via bouton
    searchButton.addEventListener("click", async (event) => {
        event.preventDefault();
        const query = locationInput.value.trim();
        if (query) {
            const data = await geocodeLocation(query);
            if (data.length > 0) {
                const coordinates = { lat: parseFloat(data[0].lat), lon: parseFloat(data[0].lon) };
                updateMap(coordinates);
            } else {
                alert("Location not found. Please try another search.");
            }
        } else {
            alert("Please enter a location to search.");
        }
    });
});
