package isika.p3.amappli.service;

import java.util.List;

import isika.p3.amappli.entities.tenancy.Tenancy;

public interface TenancyService {

	Tenancy createTenancy(Tenancy tenancy);

	List<Tenancy> getAllTenancies();

	Tenancy getTenancyById(Long id);

	void deleteTenancy(Long id);

	void addTestTenancies();

}
