let keysPressed = {};

document.addEventListener('keydown', (event) => {
    keysPressed[event.key] = true;
    
    if (keysPressed['Alt'] && event.key == 't') {
        let inputs = document.querySelectorAll("input");
        let form = document.querySelector("form");
        

        switch (form.getAttribute("action")) {
            case "/Amappli/login":
                inputs.forEach(input => {
                    if (input.getAttribute("id") === "email") {	
                        if (input.value === "amelie.rousseau@example.com") {
							input.value = "lucas.martin@example6.com";
                        }else if (input.value ==="lucas.martin@example6.com") {
							input.value = "marie.durand@example1.com";
                        } else {
                            input.value = "amelie.rousseau@example.com";
                        }
                    }
                    if (input.getAttribute("id") === "motDePasse") {
                        input.value = "AMAPamap11@";
                    }
                });
                break;
        	case "/Amappli/amappli/start/signup":
                inputs.forEach(input => {
					switch(input.getAttribute("id")) {
						case "email":
                            input.value = "amelie.rousseau@example1.com";
                        break;
						case "motDePasse":
                            input.value = "AMAPamap11@";
                        break;
						case "confirmMotDePasse":
                            input.value = "AMAPamap11@";
                        break;
						case "nom":
                            input.value = "Rousseau";
                        break;
						case "prenom":
                            input.value = "Amélie";
                        break;
						case "telephone":
                            input.value = "0796584235";
                        break;
						case "complement":
                            input.value = "1er étage";
                        break;
						case "adresse":
                            input.value = "15 route du canal";
                        break;
						case "codePostal":
                            input.value = "76000";
                        break;
						case "ville":
                            input.value = "Rouen";
                        break;
				    }
				});
                break;
        	case "/Amappli/amap/biocoli/signup":
        	case "/Amappli/amap/agrinov/signup":
        	case "/Amappli/amap/groots/signup":
        	case "/Amappli/amap/greenmaven/signup":
        	case "/Amappli/amap/lacarottechantenay/signup":
        	case "/Amappli/amap/terralocal/signup":
        	case "/Amappli/amap/amapdes5/signup":
        	case "/Amappli/amap/amapdes5-1/signup":
        	case "/Amappli/amap/amapdes5-2/signup":
        	case "/Amappli/amap/amapdes5-3/signup":
        	case "/Amappli/amap/amapdes5-4/signup":
				inputs.forEach(input => {
					switch(input.getAttribute("id")) {
						case "input-email":
                            input.value = "amelie.rousseau@example1.com";
                        break;
						case "input-password-1":
                            input.value = "AMAPamap11@";
                        break;
						case "input-password-2":
                            input.value = "AMAPamap11@";
                        break;
						case "contactinfo-name":
                            input.value = "Rousseau";
                        break;
						case "contactinfo-firstName":
                            input.value = "Amélie";
                        break;
						case "contactinfo-phone":
                            input.value = "0796584235";
                        break;
						case "address-line1":
                            input.value = "1er étage";
                        break;
						case "address-line2":
                            input.value = "15 route du canal";
                        break;
						case "address-postcode":
                            input.value = "76000";
                        break;
						case "address-city":
                            input.value = "Rouen";
                        break;
				    }
				});
            default:
                break;
        }

    } else if (keysPressed['Alt'] && event.key == 'k') {
        window.location.replace("http://localhost:8080/Amappli/amappli/home")
    }
});

document.addEventListener('keyup', (event) => {
    delete keysPressed[event.key];
});