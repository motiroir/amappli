package isika.p3.amappli.entities.workshop;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "workshops")
public class Workshop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String workshopName;

	private String workshopDescription;

	private LocalDateTime workshopDateTime;

	private BigDecimal workshopPrice;

	private Integer workshopDuration;

	private String location;

	private Integer minimumParticipants;

	private Integer maximumParticipants;

	private Boolean isBookable;

	@Column(name = "image_type")
	private String imageType;

	@Lob
	@Column(name = "image_data", columnDefinition = "LONGTEXT")
	private String imageData;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateCreation;

}
