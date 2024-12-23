package isika.p3.amappli.service;

import java.util.List;

import isika.p3.amappli.dto.WorkshopDTO;
import isika.p3.amappli.entities.workshop.Workshop;

public interface WorkshopService {
	
	void save(WorkshopDTO workshopDTO);

	List<Workshop> findAll();

	void deleteById(Long id);

	Workshop findById(Long id);

}
