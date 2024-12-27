package isika.p3.amappli.repo.amappli;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isika.p3.amappli.entities.tenancy.Tenancy;

@Repository
public interface TenancyRepository extends JpaRepository<Tenancy, Long> {

    Optional<Tenancy> findByTenancyAlias(String alias);
}
