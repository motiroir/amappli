package isika.p3.amappli.entities.user;

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
public class Address {
    
    private String line1;
    private String line2;
    private String postCode;
    private String city;
}
