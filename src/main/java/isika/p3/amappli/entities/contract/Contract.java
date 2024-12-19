package isika.p3.amappli.entities.contract;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "contracts")
public class Contract {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotBlank(message = "Le nom du contrat est obligatoire.")
    @Size(max = 100, message = "Le nom du contrat ne doit pas dépasser 100 caractères.")
	private String contractName;

    @NotNull(message = "Le type de contrat est obligatoire.")
	@Enumerated(EnumType.STRING)
	public ContractType contractType;

    @NotBlank(message = "La description est obligatoire.")
    @Size(max = 500, message = "La description ne doit pas dépasser 500 caractères.")
	private String contractDescription;

    @NotNull(message = "La taille du contrat est obligatoire.")
	@Enumerated(EnumType.STRING)
	private ContractWeight contractWeight;

    @NotNull(message = "Le prix est obligatoire.")
    @DecimalMin(value = "0.01", message = "Le prix doit être supérieur à 0.")
    @Column(precision = 10, scale = 2)
    private BigDecimal contractPrice;

    @Size(max = 255, message = "L'URL de l'image ne doit pas dépasser 255 caractères.")
	private String imageUrl;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateCreation;

    @NotNull(message = "La date de début est obligatoire.")
    @FutureOrPresent(message = "La date de début doit être dans le futur ou aujourd'hui.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;

    @NotNull(message = "La date de fin est obligatoire.")
    @Future(message = "La date de fin doit être dans le futur.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	
    @NotNull(message = "La fréquence de livraison est obligatoire.")
    @Enumerated(EnumType.STRING)
	private DeliveryRecurrence deliveryRecurrence;
	
    @NotNull(message = "Le jour de livraison est obligatoire.")
    @Enumerated(EnumType.STRING)
	private DeliveryDay deliveryDay;
	
    @NotNull(message = "La quantité est obligatoire.")
    @Min(value = 1, message = "La quantité doit être au moins de 1 panier.")
	private Integer quantity;
	

	public Long getId() {
		return id;
	}

	public String getContractName() {
		return contractName;
	}

	public String getContractDescription() {
		return contractDescription;
	}

	public ContractWeight getContractWeight() {
		return contractWeight;
	}

	public BigDecimal getContractPrice() {
		return contractPrice;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public LocalDate getDateCreation() {
		return dateCreation;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public ContractType getContractType() {
		return contractType;
	}

	public DeliveryRecurrence getDeliveryRecurrence() {
		return deliveryRecurrence;
	}

	public DeliveryDay getDeliveryDay() {
		return deliveryDay;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setDeliveryRecurrence(DeliveryRecurrence deliveryRecurrence) {
		this.deliveryRecurrence = deliveryRecurrence;
	}

	public void setDeliveryDay(DeliveryDay deliveryDay) {
		this.deliveryDay = deliveryDay;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public void setContractDescription(String contractDescription) {
		this.contractDescription = contractDescription;
	}

	public void setContractWeight(ContractWeight contractWeight) {
		this.contractWeight = contractWeight;
	}

	public void setContractPrice(BigDecimal contractPrice) {
		this.contractPrice = contractPrice;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
