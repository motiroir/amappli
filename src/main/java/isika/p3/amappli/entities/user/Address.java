package isika.p3.amappli.entities.user;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    
    @Size(max=70, message = "Le complément d'adresse doit faire 70 caractères maximum.")
    private String line1;

    @NotBlank( message = "L''adresse est obligatoire.")
	@Size(max=100, message = "L'adresse doit faire 100 caractères maximum.")
    private String line2;

    @Pattern( regexp = "^((0[1-9])|([1-9][0-9]))[0-9]{3}$", message = "Veuillez saisir un code postal français valide.")
    private String postCode;

    @NotBlank( message = "La ville est obligatoire.")
	@Size(max=70, message = "La ville doit faire 70 caractères maximum.")
    private String city;
}
