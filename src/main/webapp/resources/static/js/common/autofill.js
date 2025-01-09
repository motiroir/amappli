let keysPressed = {};
let inputs = document.querySelectorAll("input");
let form = document.querySelector("form");

document.addEventListener('keydown', (event) => {
    keysPressed[event.key] = true;

    if (keysPressed['Alt'] && event.key == 't') {
        

        switch (form.getAttribute("action")) {
            case "/Amappli/login":
                inputs.forEach(input => {
                    if (input.getAttribute("id") === "email") {					
                        if (input.value==="lucas.martin@example6.com") {
							input.value = "marie.durand@example1.com";
                        } else {
                            input.value = "lucas.martin@example6.com";
                        }
                    }
                    if (input.getAttribute("id") === "motDePasse") {
                        input.value = "AMAPamap11@";
                    }
                });
                break;
        	case "/Amappli/amappli/signup":
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
						case "email":
                            input.value = "marie.durand@example1.com";
                        break;
					}
				    if (input.getAttribute("id") === "email") {					
				        if (input.value==="lucas.martin@example6.com") {
				        } else {
				            input.value = "lucas.martin@example6.com";
				        }
				    }
				    if (input.getAttribute("id") === "motDePasse") {
				        input.value = "AMAPamap11@";
				    }
				});
            default:
                break;
        }

    }
});

document.addEventListener('keyup', (event) => {
    delete keysPressed[event.key];
});