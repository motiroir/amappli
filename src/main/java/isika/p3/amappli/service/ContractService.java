package isika.p3.amappli.service;

import java.util.List;

import isika.p3.amappli.entities.contract.Contract;

public interface ContractService {

	Contract save (Contract contract);
	List<Contract> findAll();
}
