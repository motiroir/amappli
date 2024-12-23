package isika.p3.amappli.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;

import isika.p3.amappli.dto.WorkshopDTO;
import isika.p3.amappli.entities.workshop.Workshop;
import isika.p3.amappli.repo.WorkshopRepository;

@Service
public class WorkshopServiceImpl implements WorkshopService {

    private final WorkshopRepository workshopRepository;

    public WorkshopServiceImpl(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }
    
	@Override
	public void save(WorkshopDTO workshopDTO) {
        Workshop workshop = new Workshop();

        workshop.setWorkshopName(workshopDTO.getWorkshopName());
        workshop.setWorkshopDescription(workshopDTO.getWorkshopDescription());
        workshop.setWorkshopDateTime(workshopDTO.getWorkshopDateTime());
        workshop.setWorkshopPrice(workshopDTO.getWorkshopPrice());
        workshop.setWorkshopDuration(workshopDTO.getWorkshopDuration());
        workshop.setLocation(workshopDTO.getLocation());
        workshop.setMinimumParticipants(workshopDTO.getMinimumParticipants());
        workshop.setMaximumParticipants(workshopDTO.getMaximumParticipants());
        workshop.setIsBookable(workshopDTO.getIsBookable());
        workshop.setDateCreation(LocalDate.now());

		if (!workshopDTO.getImage().isEmpty()) {
			if (workshopDTO.getImage().getSize() > 20971520) {
				throw new IllegalArgumentException("La taille de l'image dépasse la limite de 5MB.");
			}
			try {
				workshop.setImageType(workshopDTO.getImage().getContentType());
				byte[] imageBytes = workshopDTO.getImage().getBytes();
				workshop.setImageData(Base64.getEncoder().encodeToString(imageBytes));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

        workshopRepository.save(workshop);
	}

	@Override
	public List<Workshop> findAll() {
		return workshopRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		workshopRepository.deleteById(id);
	}

	@Override
	public Workshop findById(Long id) {
		// TODO Auto-generated method stub
		return workshopRepository.findById(id).orElse(null);
	}

}
