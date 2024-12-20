package isika.p3.amappli.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;

import isika.p3.amappli.dto.ContractDTO;
import isika.p3.amappli.entities.contract.Contract;
import isika.p3.amappli.entities.contract.ContractType;
import isika.p3.amappli.entities.contract.ContractWeight;
import isika.p3.amappli.entities.contract.DeliveryDay;
import isika.p3.amappli.entities.contract.DeliveryRecurrence;
import isika.p3.amappli.repo.ContractRepository;

@Service
public class ContractServiceImpl implements ContractService {

	private final ContractRepository contractRepository;

	public ContractServiceImpl(ContractRepository contractRepository) {
		this.contractRepository = contractRepository;
	}

	@Override
	public List<Contract> findAll() {
		return contractRepository.findAll();
	}

	@Override
	public void deletedById(Long id) {
		contractRepository.deleteById(id);
	}

	@Override
	public Contract findById(Long id) {
		return contractRepository.findById(id).orElse(null);
	}

	@Override
	public void save(ContractDTO contractDTO) {
		Contract contract = new Contract();

		contract.setContractName(contractDTO.getContractName());
		contract.setContractType(ContractType.valueOf(contractDTO.getContractType()));
		contract.setContractDescription(contractDTO.getContractDescription());
		contract.setContractWeight(ContractWeight.valueOf(contractDTO.getContractWeight()));
		contract.setContractPrice(contractDTO.getContractPrice());
		contract.setStartDate(contractDTO.getStartDate());
		contract.setEndDate(contractDTO.getEndDate());
		contract.setDeliveryRecurrence(DeliveryRecurrence.valueOf(contractDTO.getDeliveryRecurrence()));
		contract.setDeliveryDay(DeliveryDay.valueOf(contractDTO.getDeliveryDay()));
		contract.setQuantity(contractDTO.getQuantity());
		contract.setDateCreation(LocalDate.now());
		

		// Traitement de l'image
		if (!contractDTO.getImage().isEmpty()) {
			if (contractDTO.getImage().getSize() > 5242880) { // Limite de 5MB
				throw new IllegalArgumentException("La taille de l'image dépasse la limite de 5MB.");
			}
			try {
				contract.setImageType(contractDTO.getImage().getContentType());
				byte[] imageBytes = contractDTO.getImage().getBytes();
				contract.setImageData(Base64.getEncoder().encodeToString(imageBytes));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		contractRepository.save(contract);
	}

}
