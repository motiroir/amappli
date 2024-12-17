package isika.p3.amappli.entities.tenancy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Options {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionsId;

    private Boolean option1Active;

    private Boolean option2Active;

    private Boolean  option3Active;

    @OneToOne
    @JoinColumn( name="tenancyId")
    private Tenancy tenancy;

}
