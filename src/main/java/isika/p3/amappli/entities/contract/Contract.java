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

@Entity
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