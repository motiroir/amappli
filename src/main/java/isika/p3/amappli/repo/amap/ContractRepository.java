package isika.p3.amappli.repo.amap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isika.p3.amappli.entities.contract.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

}
