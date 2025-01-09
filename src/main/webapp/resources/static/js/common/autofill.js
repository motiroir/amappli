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
        	case "/Amappli/amap/biocoli/signup":
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