// Fonction pour mettre à jour la date minimale de endDate
function updateEndDate() {
    const startDateInput = document.getElementById("startDate");
    const endDateInput = document.getElementById("endDate");

    // Récupère la valeur de la date de début
    const startDateValue = startDateInput.value;

    if (startDateValue) {
        // Activer le champ endDate
        endDateInput.disabled = false;

        // Calculer la date minimum pour endDate (+3 mois)
        const startDate = new Date(startDateValue);
        const minEndDate = new Date(startDate.setMonth(startDate.getMonth() + 3));

        // Format YYYY-MM-DD pour endDate
        const year = minEndDate.getFullYear();
        const month = String(minEndDate.getMonth() + 1).padStart(2, '0');
        const day = String(minEndDate.getDate()).padStart(2, '0');
        const minDate = `${year}-${month}-${day}`;

        // Appliquer la valeur minimale à endDate
        endDateInput.min = minDate;

        // Réinitialiser la valeur de endDate si elle est inférieure à minDate
        if (endDateInput.value && endDateInput.value < minDate) {
            endDateInput.value = minDate;
        }
    } else {
        // Désactiver endDate si startDate n'est pas sélectionnée
        endDateInput.disabled = true;
        endDateInput.value = "";
    }
}

// Désactiver endDate au chargement initial de la page
document.addEventListener("DOMContentLoaded", function () {
    const startDateInput = document.getElementById("startDate");
    const endDateInput = document.getElementById("endDate");

    // Désactiver endDate si startDate est vide au chargement
    if (!startDateInput.value) {
        endDateInput.disabled = true;
    }

    // Ajouter un listener sur startDate pour appeler updateEndDate
    startDateInput.addEventListener("change", updateEndDate);
});
