package isika.p3.amappli.entities.user;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import isika.p3.amappli.entities.auth.Role;
import isika.p3.amappli.entities.tenancy.Tenancy;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
	
	@NotBlank(message = "L'email est obligatoire.")
	@Size(max=70, message = "L'email doit faire 70 caractères maximum.")
    @Email( message = "L'email est invalide.")
    @Column(nullable = false, unique = true)
    private String email;
	
	@NotBlank(message = "Champ obligatoire.")
    @Column(nullable = false)
    private String password;
    

    private BigDecimal creditBalance;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    private ContactInfo contactInfo;

    private boolean isActive;

    @ManyToOne
    @JoinColumn( name = "tenancyId")
    private Tenancy tenancy;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (
        name = "User_Role_Association",
        joinColumns = @JoinColumn(name = "userId"),
        inverseJoinColumns = @JoinColumn( name = "roleId")
    )
    private Set<Role> roles = new HashSet<Role>();
    
    @OneToOne(cascade = CascadeType.ALL)
    private CompanyDetails companyDetails;
}
