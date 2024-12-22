package isika.p3.amappli.dto;

import java.math.BigDecimal;

import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.entities.user.CompanyDetails;
import isika.p3.amappli.entities.user.ContactInfo;
import isika.p3.amappli.validation.annotation.PasswordMatches;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PasswordMatches
public class SupplierDTO {

    @NotBlank(message = "L'email est obligatoire.")
    @Size(max = 70, message = "L'email doit faire 70 caractères maximum.")
    @Email(message = "L'email est invalide.")
    private String email;

    @NotBlank(message = "Champ obligatoire")
    private String password;

    @NotBlank(message = "Champ obligatoire.")
    private String confirmPassword;

    private BigDecimal creditBalance;

    @Valid
    private Address address;

    @Valid
    private ContactInfo contactInfo;
    
    @Valid
    private CompanyDetails companyDetails;
}
