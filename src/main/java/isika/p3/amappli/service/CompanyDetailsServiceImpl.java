package isika.p3.amappli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isika.p3.amappli.entities.user.CompanyDetails;
import isika.p3.amappli.repo.CompanyDetailsRepository;

@Service
public class CompanyDetailsServiceImpl implements CompanyDetailsService {

	@Autowired
	private final CompanyDetailsRepository repo;
	
	public CompanyDetailsServiceImpl(CompanyDetailsRepository repo) {
		this.repo = repo;
	}

	@Override
	public List<CompanyDetails> findAll() {
		return repo.findAll();
	}

	@Override
	public CompanyDetails save(CompanyDetails companyDetails) {
		return repo.save(companyDetails);
	}

	@Override
	public CompanyDetails findById(Long companyDetailsId) {
		return repo.findById(companyDetailsId).orElse(null);
	}

	@Override
	public void deleteById(Long companyDetailsId) {
		repo.deleteById(companyDetailsId);
	}

}
