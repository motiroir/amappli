package isika.p3.amappli.service;

import java.util.List;

import org.springframework.stereotype.Service;

import isika.p3.amappli.entities.contract.Contract;
import isika.p3.amappli.repo.ContractRepository;

@Service
public class ContractServiceImpl implements ContractService{

	private final ContractRepository contractRepository; 
	
	public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }
	
	@Override
	public Contract save(Contract contract) {
		
		return contractRepository.save(contract);
	}

	@Override
	public List<Contract> findAll() {
		return contractRepository.findAll();
	}

}
