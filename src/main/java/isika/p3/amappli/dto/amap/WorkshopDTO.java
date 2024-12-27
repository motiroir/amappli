package isika.p3.amappli.dto.amap;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkshopDTO {

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
    private MultipartFile image;
    private LocalDate dateCreation;
}
