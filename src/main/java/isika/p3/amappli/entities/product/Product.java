package isika.p3.amappli.entities.product;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import isika.p3.amappli.entities.contract.DeliveryDay;
import isika.p3.amappli.entities.contract.DeliveryRecurrence;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    
    private String productDescription;

    @DecimalMin(value = "0.01", message = "Le prix doit être supérieur à 0.")
    private BigDecimal productPrice;

    private Integer productStock;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateCreation;

    @Column(name = "image_type")
    private String imageType;

    @Lob
    @Column(name = "image_data", columnDefinition = "LONGTEXT")
    private String imageData;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fabricationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
    
	@Enumerated(EnumType.STRING)
	private DeliveryDay deliveryDay;
	
	@Enumerated(EnumType.STRING)
	private DeliveryRecurrence deliveryRecurrence;
}
