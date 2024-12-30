document.addEventListener("DOMContentLoaded", () => {
	const searchBar = document.getElementById("searchBar");
	const sortBy = document.getElementById("sortBy");
	const tableBody = document.querySelector("tbody");

	// Fonction pour trier les lignes
	function sortRows(criteria) {
		const rows = Array.from(tableBody.querySelectorAll("tr"));
		rows.sort((a, b) => {
			const valueA = getValue(a, criteria);
			const valueB = getValue(b, criteria);

			if (criteria === "priceAsc") {
				return parseFloat(valueA) - parseFloat(valueB);
			} else if (criteria === "priceDesc") {
				return parseFloat(valueB) - parseFloat(valueA);
			} else {
				return valueA.localeCompare(valueB);
			}
		});

		rows.forEach(row => tableBody.appendChild(row));
	}

	function getValue(row, criteria) {
		if (criteria === "name") return row.cells[1].innerText.trim();
		if (criteria === "producer") return row.cells[3].innerText.trim();
		if (criteria === "priceAsc" || criteria === "priceDesc") return row.cells[4].innerText.trim().replace("€", "");
		return "";
	}

	// Fonction pour filtrer les lignes
	function filterRows(query) {
		const rows = tableBody.querySelectorAll("tr");
		rows.forEach(row => {
			const name = row.cells[1].innerText.toLowerCase();
			const producer = row.cells[3].innerText.toLowerCase();
			if (name.includes(query) || producer.includes(query)) {
				row.style.display = "";
			} else {
				row.style.display = "none";
			}
		});
	}

	// Événements
	sortBy.addEventListener("change", () => {
		sortRows(sortBy.value);
	});

	searchBar.addEventListener("input", () => {
		const query = searchBar.value.toLowerCase();
		filterRows(query);
	});
});