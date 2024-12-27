package isika.p3.amappli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isika.p3.amappli.entities.user.ContactInfo;
import isika.p3.amappli.repo.ContactInfoRepository;

@Service
public class ContactInfoServiceImpl implements ContactInfoService {

	@Autowired
	private final ContactInfoRepository repo;
	
	public ContactInfoServiceImpl(ContactInfoRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public List<ContactInfo> findAll() {
		return repo.findAll();
	}

	@Override
	public ContactInfo save(ContactInfo contactInfo) {
		return repo.save(contactInfo);
	}

	@Override
	public ContactInfo findById(Long contactInfoId) {
		return repo.findById(contactInfoId).orElse(null);
	}

	@Override
	public void deleteById(Long contactInfoId) {
		repo.deleteById(contactInfoId);
	}

}
