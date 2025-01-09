document.addEventListener("DOMContentLoaded", function () {

     // Showing the sample URL
     let tenancyAliasInput = document.getElementById('input-tenancyAlias');
     let tenancyUrlExampleSpan = document.getElementById('amap-url-example');
 
     tenancyAliasInput.addEventListener('input', function() {
         tenancyUrlExampleSpan.innerText = tenancyAliasInput.value;
     });


    // Previewing the logo
    let inputFile = document.getElementById('input-tenancyLogo');
    let previewImage = document.getElementById('preview-logo');
    let cancelButton = document.getElementById('cancel-preview-logo');

    // Store the original logo
    let originalImageSrc = previewImage.src;

    let temporaryImageUrl = null; // for uploaded file

    inputFile.addEventListener('change', function (event) {
        let file = event.target.files[0];
        if (file) {
            // erase previously upload img if necessary
            if (temporaryImageUrl) {
                URL.revokeObjectURL(temporaryImageUrl);
            }

            // Create a new object URL for the uploaded file
            temporaryImageUrl = URL.createObjectURL(file);

            // Update the preview image
            previewImage.src = temporaryImageUrl;

            // Show the cancel button
            cancelButton.style.display = 'block';
        }
    });

    cancelButton.addEventListener('click', function () {
        // Put back the original logo
        previewImage.src = originalImageSrc;

        // Cleanup
        inputFile.value = '';

        cancelButton.style.display = 'none';

        if (temporaryImageUrl) {
            URL.revokeObjectURL(temporaryImageUrl);
            temporaryImageUrl = null;
        }
    });

    // Preselecting the current font and color
    selectRadioButtons(fontValue,colorValue);

    function selectRadioButtons(fontValue, colorValue) {
    // Select the radio button for font
        let fontRadio = document.querySelector(`input[name="fontChoice"][value="${fontValue}"]`);
        if (fontRadio) {
            fontRadio.checked = true;
        } else {
            console.warn(`Font value "${fontValue}" not found!`);
        }

        // Select the radio button for color palette
        let colorRadio = document.querySelector(`input[name="colorPalette"][value="${colorValue}"]`);
        if (colorRadio) {
            colorRadio.checked = true;
        } else {
            console.warn(`Color palette value "${colorValue}" not found!`);
        }
    }

});