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
                        input.setAttribute("value", "lucas.martin@example1.com");
                    }
                    if (input.getAttribute("id") === "motDePasse") {
                        input.setAttribute("value", "AMAPamap11@");
                    }
                });
                break;
        
            default:
                break;
        }

    }
});

document.addEventListener('keyup', (event) => {
    delete keysPressed[event.key];
});