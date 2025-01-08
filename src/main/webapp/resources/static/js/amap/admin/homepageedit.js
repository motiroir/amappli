document.addEventListener("DOMContentLoaded", function () {

     // Showing the sample URL
     let tenancyAliasInput = document.getElementById('input-tenancyAlias');
     let tenancyUrlExampleSpan = document.getElementById('amap-url-example');
 
     tenancyAliasInput.addEventListener('input', function() {
         tenancyUrlExampleSpan.innerText = tenancyAliasInput.value;
     });


    // Previewing the logo
    const inputFile = document.getElementById('input-tenancyLogo');
    const previewImage = document.getElementById('preview-logo');
    const cancelButton = document.getElementById('cancel-preview-logo');

    // Store the original logo
    const originalImageSrc = previewImage.src;

    let temporaryImageUrl = null; // for uploaded file

    inputFile.addEventListener('change', function (event) {
        const file = event.target.files[0];
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

});