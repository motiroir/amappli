document.addEventListener("DOMContentLoaded", function () {
    const startDateInput = document.getElementById("startDate");
    const endDateInput = document.getElementById("endDate");
    const fileInput = document.getElementById("image");
    const previewImage = document.querySelector(".image-preview");

    if (!startDateInput || !endDateInput) {
        console.error("Champs startDate ou endDate introuvables !");
        return;
    }

    // Fonction pour obtenir la date actuelle formatée
    function getTodayFormatted() {
        const today = new Date();
        const year = today.getFullYear();
        const month = String(today.getMonth() + 1).padStart(2, "0");
        const day = String(today.getDate()).padStart(2, "0");
        return `${year}-${month}-${day}`;
    }

    // Fonction pour calculer une nouvelle date (minEndDate = startDate + 3 mois)
    function calculateMinEndDate(startDate) {
        const start = new Date(startDate);
        if (isNaN(start.getTime())) {
            console.error("Date de début invalide :", startDate);
            return null;
        }

        const minEndDate = new Date(start);
        minEndDate.setMonth(minEndDate.getMonth() + 3); // Ajoute 3 mois
        const year = minEndDate.getFullYear();
        const month = String(minEndDate.getMonth() + 1).padStart(2, "0");
        const day = String(minEndDate.getDate()).padStart(2, "0");
        return `${year}-${month}-${day}`;
    }

    // Initialisation des champs startDate et endDate
    function initializeDates() {
        const todayFormatted = getTodayFormatted();

        // Paramétrer la date minimale pour startDate
        startDateInput.min = todayFormatted;

        // Si aucune valeur n'est présente, définir la date actuelle comme valeur par défaut
        if (!startDateInput.value) {
            startDateInput.value = todayFormatted;
        }

        // Calculer et définir la date minimale pour endDate
        const minEndDate = calculateMinEndDate(startDateInput.value);
        if (minEndDate) {
            endDateInput.min = minEndDate;
            if (!endDateInput.value || endDateInput.value < minEndDate) {
                endDateInput.value = minEndDate;
            }
        }
    }

    // Mettre à jour endDate lorsque startDate change
    function handleStartDateChange() {
        const newMinEndDate = calculateMinEndDate(startDateInput.value);
        if (newMinEndDate) {
            endDateInput.min = newMinEndDate;
            if (endDateInput.value < newMinEndDate) {
                endDateInput.value = newMinEndDate;
            }
        }
    }

    // Gérer la prévisualisation de l'image téléchargée
    function handleFileInputChange() {
        if (fileInput && previewImage) {
            fileInput.addEventListener("change", function (event) {
                const file = event.target.files[0];
                if (file) {
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        previewImage.src = e.target.result; // Met à jour l'URL de l'image
                    };
                    reader.readAsDataURL(file); // Lit le fichier comme une URL de données
                }
            });
        }
    }

    // Initialisation des valeurs par défaut
    initializeDates();

    // Ajout des écouteurs d'événements
    startDateInput.addEventListener("change", handleStartDateChange);
    handleFileInputChange();
});
