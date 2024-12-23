package isika.p3.amappli.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import isika.p3.amappli.entities.workshop.Workshop;

public interface WorkshopRepository extends JpaRepository<Workshop, Long> {

}
