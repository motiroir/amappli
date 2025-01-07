// Fonction générique de tri
function sortItems(items, key, order = 'asc') {
    return items.sort((a, b) => {
        let valA = a[key];
        let valB = b[key];

        if (typeof valA === 'string') {
            valA = valA.toLowerCase();
            valB = valB.toLowerCase();
        }

        if (order === 'asc') {
            return valA > valB ? 1 : valA < valB ? -1 : 0;
        } else {
            return valA < valB ? 1 : valA > valB ? -1 : 0;
        }
    });
}

// Tri pour les contrats
function sortContracts(criteria) {
    const contracts = Array.from(document.querySelectorAll('.contract-card'));
    const contractData = contracts.map(contract => ({
        element: contract,
        contractName: contract.querySelector('.card-title').innerText,
        contractPrice: parseFloat(contract.querySelector('.card-text b').innerText.replace('€', '').trim()),
    }));

    let sortedContracts;
    switch (criteria) {
        case 'name':
            sortedContracts = sortItems(contractData, 'contractName', 'asc');
            break;
        case 'creditAsc':
            sortedContracts = sortItems(contractData, 'contractPrice', 'asc');
            break;
        case 'creditDesc':
            sortedContracts = sortItems(contractData, 'contractPrice', 'desc');
            break;
    }

    updateDisplay(sortedContracts, '.contract-card');
}

// Tri pour les produits
function sortProducts(criteria) {
    const products = Array.from(document.querySelectorAll('.contract-card'));
    const productData = products.map(product => ({
        element: product,
        productName: product.querySelector('.card-title').innerText,
        productPrice: parseFloat(product.querySelector('.card-text b').innerText.replace('€', '').trim()),
        expirationDate: new Date(product.querySelector('.card-text.text-muted').innerText.replace('DLC:', '').trim()),
    }));

    let sortedProducts;
    switch (criteria) {
        case 'name':
            sortedProducts = sortItems(productData, 'productName', 'asc');
            break;
        case 'creditAsc':
            sortedProducts = sortItems(productData, 'productPrice', 'asc');
            break;
        case 'creditDesc':
            sortedProducts = sortItems(productData, 'productPrice', 'desc');
            break;
        case 'expirationDate':
            sortedProducts = sortItems(productData, 'expirationDate', 'asc');
            break;
    }

    updateDisplay(sortedProducts, '.contract-card');
}

// Tri pour les ateliers
function sortWorkshops(criteria) {
    const workshops = Array.from(document.querySelectorAll('.contract-card'));
    const workshopData = workshops.map(workshop => ({
        element: workshop,
        workshopName: workshop.querySelector('.card-title').innerText,
        workshopPrice: parseFloat(workshop.querySelector('.card-text b').innerText.replace('€', '').trim()),
        workshopDateTime: new Date(workshop.querySelector('.card-text.text-muted').innerText),
    }));

    let sortedWorkshops;
    switch (criteria) {
        case 'name':
            sortedWorkshops = sortItems(workshopData, 'workshopName', 'asc');
            break;
        case 'creditAsc':
            sortedWorkshops = sortItems(workshopData, 'workshopPrice', 'asc');
            break;
        case 'creditDesc':
            sortedWorkshops = sortItems(workshopData, 'workshopPrice', 'desc');
            break;
        case 'workshopDateTime':
            sortedWorkshops = sortItems(workshopData, 'workshopDateTime', 'asc');
            break;
    }

    updateDisplay(sortedWorkshops, '.contract-card');
}

// Fonction pour mettre à jour l'affichage
function updateDisplay(sortedData, selector) {
    const container = document.querySelector(selector).parentElement;
    container.innerHTML = '';
    sortedData.forEach(item => {
        container.appendChild(item.element);
    });
}

// Gestion des événements
const sortByContracts = document.getElementById('sortByContracts');
sortByContracts.addEventListener('change', event => {
    const criteria = event.target.value;
    sortContracts(criteria);
});

const sortByProducts = document.getElementById('sortByProducts');
sortByProducts.addEventListener('change', event => {
    const criteria = event.target.value;
    sortProducts(criteria);
});

const sortByWorkshops = document.getElementById('sortByWorkshops');
sortByWorkshops.addEventListener('change', event => {
    const criteria = event.target.value;
    sortWorkshops(criteria);
});
