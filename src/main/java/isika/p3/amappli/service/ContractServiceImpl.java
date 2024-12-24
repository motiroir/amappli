package isika.p3.amappli.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	public void deleteById(Long id) {
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

		//Image treatment
		if (!contractDTO.getImage().isEmpty()) {
			if (contractDTO.getImage().getSize() > 20971520) {
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
	
	@Override
	public void updateContract(ContractDTO updatedContractDTO, MultipartFile image) {
	    Contract existingContract = contractRepository.findById(updatedContractDTO.getId()).orElse(null);

	    if (existingContract == null) {
	        throw new IllegalArgumentException("Le contrat avec l'ID " + updatedContractDTO.getId() + " n'existe pas.");
	    }

	    // Mise à jour des champs non liés à l'image
	    existingContract.setContractName(updatedContractDTO.getContractName() != null ? updatedContractDTO.getContractName() : existingContract.getContractName());
	    existingContract.setContractType(updatedContractDTO.getContractType() != null ? ContractType.valueOf(updatedContractDTO.getContractType()) : existingContract.getContractType());
	    existingContract.setContractDescription(updatedContractDTO.getContractDescription() != null ? updatedContractDTO.getContractDescription() : existingContract.getContractDescription());
	    existingContract.setContractWeight(updatedContractDTO.getContractWeight() != null ? ContractWeight.valueOf(updatedContractDTO.getContractWeight()) : existingContract.getContractWeight());
	    existingContract.setContractPrice(updatedContractDTO.getContractPrice() != null ? updatedContractDTO.getContractPrice() : existingContract.getContractPrice());
	    existingContract.setStartDate(updatedContractDTO.getStartDate() != null ? updatedContractDTO.getStartDate() : existingContract.getStartDate());
	    existingContract.setEndDate(updatedContractDTO.getEndDate() != null ? updatedContractDTO.getEndDate() : existingContract.getEndDate());
	    existingContract.setDeliveryRecurrence(updatedContractDTO.getDeliveryRecurrence() != null ? DeliveryRecurrence.valueOf(updatedContractDTO.getDeliveryRecurrence()) : existingContract.getDeliveryRecurrence());
	    existingContract.setDeliveryDay(updatedContractDTO.getDeliveryDay() != null ? DeliveryDay.valueOf(updatedContractDTO.getDeliveryDay()) : existingContract.getDeliveryDay());
	    existingContract.setQuantity(updatedContractDTO.getQuantity() != null ? updatedContractDTO.getQuantity() : existingContract.getQuantity());

	    // Gestion de l'image
	    if (image != null && !image.isEmpty()) {
	        try {
	            existingContract.setImageType(image.getContentType());
	            byte[] imageBytes = image.getBytes();
	            existingContract.setImageData(Base64.getEncoder().encodeToString(imageBytes));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    // Sauvegarde du contrat mis à jour
	    contractRepository.save(existingContract);
	}


}
