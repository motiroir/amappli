package isika.p3.amappli.repo.amap;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isika.p3.amappli.entities.contract.Contract;
import isika.p3.amappli.entities.tenancy.Tenancy;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
	List<Contract> findByTenancy(Tenancy tenancy);
}
