document.addEventListener("DOMContentLoaded", function () {

     // Showing the sample URL
     let tenancyAliasInput = document.getElementById('input-tenancyAlias');
     let tenancyUrlExampleSpan = document.getElementById('amap-url-example');
 
     tenancyAliasInput.addEventListener('input', function() {
         tenancyUrlExampleSpan.innerText = tenancyAliasInput.value;
     });

});