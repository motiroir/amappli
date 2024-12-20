package isika.p3.amappli.entities.contract;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import isika.p3.amappli.entities.tenancy.Graphism;
import isika.p3.amappli.entities.tenancy.HomePageContent;
import isika.p3.amappli.entities.tenancy.Options;
import isika.p3.amappli.entities.tenancy.ServiceSubscription;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.entities.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "contracts")
public class Contract {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String contractName;

	@Enumerated(EnumType.STRING)
	public ContractType contractType;

	@Column(length = 500)
	private String contractDescription;

	@Enumerated(EnumType.STRING)
	private ContractWeight contractWeight;

	private BigDecimal contractPrice;

	private String imageUrl;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateCreation;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	
	@Enumerated(EnumType.STRING)
	private DeliveryRecurrence deliveryRecurrence;
	
	@Enumerated(EnumType.STRING)
	private DeliveryDay deliveryDay;
	
	private Integer quantity;
	

}