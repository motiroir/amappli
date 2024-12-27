package isika.p3.amappli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.repo.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private final AddressRepository repo;
	
	public AddressServiceImpl(AddressRepository repo) {
		this.repo = repo;
	}

	@Override
	public List<Address> findAll() {
		return repo.findAll();
	}

	@Override
	public Address save(Address address) {
		return repo.save(address);
	}

	@Override
	public Address findById(Long addressId) {
		return repo.findById(addressId).orElse(null);
	}

	@Override
	public void deleteById(Long addressId) {
		repo.deleteById(addressId);
	}
}
