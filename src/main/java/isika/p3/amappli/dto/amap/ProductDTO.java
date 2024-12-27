package isika.p3.amappli.dto.amap;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

	private Long id;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private Integer productStock;
    private LocalDate dateCreation;
    private LocalDate fabricationDate;
    private LocalDate expirationDate;
    private MultipartFile image;
    private String deliveryDay;
    private String deliveryRecurrence; 
}
