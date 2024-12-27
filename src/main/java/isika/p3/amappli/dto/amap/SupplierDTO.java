package isika.p3.amappli.dto.amap;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierDTO {

    @Size(max = 70, message = "L'email doit faire 70 caractères maximum.")
    @Email(message = "L'email est invalide.")
    private String email;

    @Size(max=70, message = "Le complément d'adresse doit faire 70 caractères maximum.")
    private String line1;

    @NotBlank( message = "L'adresse est obligatoire.")
	@Size(max=100, message = "L'adresse doit faire 100 caractères maximum.")
    private String line2;

    @Pattern( regexp = "^((0[1-9])|([1-9][0-9]))[0-9]{3}$", message = "Veuillez saisir un code postal français valide.")
    private String postCode;

    @NotBlank( message = "La ville est obligatoire.")
	@Size(max=70, message = "La ville doit faire 70 caractères maximum.")
    private String city;

    @NotBlank( message = "Le nom est obligatoire.")
	@Size(max=70, message = "Le nom doit faire 70 caractères maximum.")
    private String name;

    @NotBlank( message = "Le prénom est obligatoire.")
	@Size(max=70, message = "Le prénom doit faire 70 caractères maximum.")
    private String firstName;

    @Pattern(regexp = "^(0[1-9])([ .-]?[0-9]{2}){4}$|^\\+33([ .-]?[0-9]{2}){4}$", message = "Numéro de téléphone français invalide.")
    private String phoneNumber;
    
	@Column(nullable = true)
    private String companyName;
	
	@Column(nullable = true)
    private String siretNumber;
}
