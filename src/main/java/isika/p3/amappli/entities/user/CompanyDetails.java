package isika.p3.amappli.entities.user;



import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDetails {

	@Column(nullable = true)
    private String companyName;
	
	@Column(nullable = true)
    private String siretNumber;
}
