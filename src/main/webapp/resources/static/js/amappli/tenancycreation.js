document.addEventListener("DOMContentLoaded", function () {
    // Form Nav
    let allFormParts = document.querySelectorAll('.form-part');
    let currentIndex = 0;
    let progressBar = document.getElementById("the-progress-bar");
    let progressBarIncrements = 100/allFormParts.length;

    let goBackButton = document.getElementById("go-back-button");
    let continueButton = document.getElementById("continue-button");

    continueButton.addEventListener("click", function() {
        if (currentIndex < allFormParts.length - 1) {
                currentIndex++;
                updateVisibility();
                if(currentIndex == allFormParts.length - 2) {
                    computeOptionChoice();
                }
            }
    });

    goBackButton.addEventListener("click", function() {
        if (currentIndex > 0){
            currentIndex--;
            updateVisibility();
            if(currentIndex == allFormParts.length - 2) {
                computeOptionChoice(); 
            }
        }
    });

    updateVisibility();

    computeOptionChoice();

    function updateVisibility() {
        //update progress bar
        progressBar.style.width = progressBarIncrements*currentIndex+"%";
        progressBar.ariaValueNow = progressBarIncrements*currentIndex;
        
        // show current form part
        allFormParts.forEach((part, index) => {
        if (index === currentIndex) {
            part.style.display = "block"; // Show the current part
        } else {
            part.style.display = "none"; // Hide all other parts
        }
        });

        // hide go back or continue button if first or last part of the form
        if (currentIndex == 0){
            goBackButton.style.display = "none";
        }
        else{
            goBackButton.style.display = "block";
        }

        if (currentIndex == allFormParts.length-1){
            continueButton.style.display = "none";
        }
        else{
            continueButton.style.display = "block";
        }

    };

    // Showing the sample URL
    let tenancyAliasInput = document.getElementById('input-tenancy-alias');
    let tenancyUrlExampleSpan = document.getElementById('amap-url-example');

    tenancyAliasInput.addEventListener('input', function() {
        tenancyUrlExampleSpan.innerText = tenancyAliasInput.value;
    });

    // Choosing options based on quizz
    
    function computeOptionChoice() {
        let productsMarketPlaceResponse = document.querySelector('input[name="question-1"]:checked').value;
        let discountsResponse= document.querySelector('input[name="question-2"]:checked').value;
        let eventsResponse = document.querySelector('input[name="question-3"]:checked').value;
        let onlinePaymentResponse = document.querySelector('input[name="question-4"]:checked').value;
        let supplierAccountResponse = document.querySelector('input[name="question-5"]:checked').value;
        let customRolesResponse = document.querySelector('input[name="question-6"]:checked').value;
        let statisticsResponse =  document.querySelector('input[name="question-7"]:checked').value;

        let recommandation = document.createElement("div");
        recommandation.innerHTML = "<div class='alert alert-primary' role='alert'>Cette option vous permettra d'avoir toutes les fonctionnalités souhaitées!</div>";
        if(statisticsResponse||  customRolesResponse || discountsResponse ){
            let option3button = document.getElementById("option-3");
            option3button.insertAdjacentElement("beforebegin", recommandation);
        }
        else if(productsMarketPlaceResponse || onlinePaymentResponse || supplierAccountResponse || eventsResponse) {
            let option2button = document.getElementById("option-2");
            option2button.insertAdjacentElement("beforebegin", recommandation);
        }
        else {
            let option1button = document.getElementById("option-1");
            option1button.insertAdjacentElement("beforebegin", recommandation);
        }
    };

});
