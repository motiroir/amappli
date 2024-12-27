document.addEventListener("DOMContentLoaded", function() {
	const startDateInput = document.getElementById("startDate");
	const endDateInput = document.getElementById("endDate");

	if (!startDateInput || !endDateInput) {
		console.error("Champs startDate ou endDate introuvables !");
		return;
	}

	// Fonction pour calculer la date minimum de endDate (+3 mois après startDate)
	function calculateMinEndDate(startDate) {
		const start = new Date(startDate);
		const minEndDate = new Date(start.setMonth(start.getMonth() + 3));
		const year = minEndDate.getFullYear();
		const month = String(minEndDate.getMonth() + 1).padStart(2, "0");
		const day = String(minEndDate.getDate()).padStart(2, "0");
		return `${year}-${month}-${day}`;
	}

	// Initialiser startDate et endDate au chargement
	const today = new Date();
	const year = today.getFullYear();
	const month = String(today.getMonth() + 1).padStart(2, "0");
	const day = String(today.getDate()).padStart(2, "0");
	const todayFormatted = `${year}-${month}-${day}`;

	if (!startDateInput.value) {
		startDateInput.value = todayFormatted;
	}

	const minEndDate = calculateMinEndDate(startDateInput.value);
	endDateInput.min = minEndDate; // Applique le min dès le départ
	if (!endDateInput.value) {
		endDateInput.value = minEndDate;
	}

	// Mettre à jour endDate lorsque startDate change
	startDateInput.addEventListener("change", function() {
		const newMinEndDate = calculateMinEndDate(startDateInput.value);
		endDateInput.min = newMinEndDate;

		if (endDateInput.value < newMinEndDate) {
			endDateInput.value = newMinEndDate;
		}
	});
});
