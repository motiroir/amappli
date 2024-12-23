package isika.p3.amappli.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContractDTO {
    private String contractName;
    private String contractType;
    private String contractDescription;
    private String contractWeight;
    private BigDecimal contractPrice;
    private LocalDate startDate;
    private LocalDate endDate;
    private MultipartFile image; // Champ pour l'image
    private String deliveryRecurrence; // Attribut ajouté
    private String deliveryDay;        // Attribut ajouté
    private Integer quantity;
    private LocalDate dateCreation;
}
