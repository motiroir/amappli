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
public class ContactInfo {
    
    @NotBlank( message = "Le nom est obligatoire.")
	@Size(max=70, message = "Le nom doit faire 70 caractères maximum.")
    private String name;

    @NotBlank( message = "Le prénom est obligatoire.")
	@Size(max=70, message = "Le prénom doit faire 70 caractères maximum.")
    private String firstName;

    @Pattern(regexp = "^(0[1-9])([ .-]?[0-9]{2}){4}$|^\\+33([ .-]?[0-9]{2}){4}$", message = "Numéro de téléphone français invalide.")
    private String phoneNumber;

}
