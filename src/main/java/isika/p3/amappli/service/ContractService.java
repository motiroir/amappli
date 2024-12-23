package isika.p3.amappli.service;

import java.util.List;

import isika.p3.amappli.dto.ContractDTO;
import isika.p3.amappli.entities.contract.Contract;

public interface ContractService {

	void save(ContractDTO contractDTO);

	List<Contract> findAll();

	void deleteById(Long id);

	Contract findById(Long id);
}
