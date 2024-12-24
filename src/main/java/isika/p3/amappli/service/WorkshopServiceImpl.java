package isika.p3.amappli.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	@Override
	public void updateWorkshop(WorkshopDTO updatedWorkshopDTO, MultipartFile image) {

		 Workshop existingWorkshop = workshopRepository.findById(updatedWorkshopDTO.getId()).orElse(null);

		    if (existingWorkshop == null) {
		        throw new IllegalArgumentException("L'atelier avec l'ID " + updatedWorkshopDTO.getId() + " n'existe pas.");
		    }

		    // Mise à jour des champs non liés à l'image
		    existingWorkshop.setWorkshopName(updatedWorkshopDTO.getWorkshopName() != null ? updatedWorkshopDTO.getWorkshopName() : existingWorkshop.getWorkshopName());
		    existingWorkshop.setWorkshopDescription(updatedWorkshopDTO.getWorkshopDescription() != null ? updatedWorkshopDTO.getWorkshopDescription() : existingWorkshop.getWorkshopDescription());
		    existingWorkshop.setWorkshopDateTime(updatedWorkshopDTO.getWorkshopDateTime() != null ? updatedWorkshopDTO.getWorkshopDateTime() : existingWorkshop.getWorkshopDateTime());
		    existingWorkshop.setWorkshopPrice(updatedWorkshopDTO.getWorkshopPrice() != null ? updatedWorkshopDTO.getWorkshopPrice() : existingWorkshop.getWorkshopPrice());
		    existingWorkshop.setWorkshopDuration(updatedWorkshopDTO.getWorkshopDuration() != null ? updatedWorkshopDTO.getWorkshopDuration() : existingWorkshop.getWorkshopDuration());
		    existingWorkshop.setLocation(updatedWorkshopDTO.getLocation() != null ? updatedWorkshopDTO.getLocation() : existingWorkshop.getLocation());
		    existingWorkshop.setMinimumParticipants(updatedWorkshopDTO.getMinimumParticipants() != null ? updatedWorkshopDTO.getMinimumParticipants() : existingWorkshop.getMinimumParticipants());
		    existingWorkshop.setMaximumParticipants(updatedWorkshopDTO.getMaximumParticipants() != null ? updatedWorkshopDTO.getMaximumParticipants() : existingWorkshop.getMaximumParticipants());
		    existingWorkshop.setIsBookable(updatedWorkshopDTO.getIsBookable() != null ? updatedWorkshopDTO.getIsBookable() : existingWorkshop.getIsBookable());

		    // Gestion de l'image
		    if (image != null && !image.isEmpty()) {
		        try {
		            existingWorkshop.setImageType(image.getContentType());
		            byte[] imageBytes = image.getBytes();
		            existingWorkshop.setImageData(Base64.getEncoder().encodeToString(imageBytes));
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }

		    // Sauvegarde de l'atelier mis à jour
		    workshopRepository.save(existingWorkshop);
	}

}
