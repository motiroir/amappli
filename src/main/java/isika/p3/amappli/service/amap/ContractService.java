package isika.p3.amappli.service.amap;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import isika.p3.amappli.dto.amap.ContractDTO;
import isika.p3.amappli.entities.contract.Contract;
import isika.p3.amappli.entities.user.User;

public interface ContractService {

	void save(ContractDTO contractDTO, String tenancyAlias);

	List<Contract> findAll();

	void deleteById(Long id);

	Contract findById(Long id);

	void updateContract(ContractDTO updatedContractDTO, MultipartFile image);

	List<Contract> findAll(Long tenancyId);
	
	List<Contract> findAll(String tenancyAlias);

}
