package isika.p3.amappli.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isika.p3.amappli.dto.NewTenancyDTO;
import isika.p3.amappli.dto.ValueDTO;
import isika.p3.amappli.entities.tenancy.ContentBlock;
import isika.p3.amappli.entities.tenancy.Graphism;
import isika.p3.amappli.entities.tenancy.HomePageContent;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.repo.TenancyRepository;

@Service
public class TenancyServiceImpl implements TenancyService{
	
	@Autowired
    private TenancyRepository tenancyRepository;

    @Override
    public Tenancy createTenancy(Tenancy tenancy) {
        return tenancyRepository.save(tenancy);
    }

    @Override
    public List<Tenancy> getAllTenancies() {
        return (List<Tenancy>) tenancyRepository.findAll();
    }

    @Override
    public Tenancy getTenancyById(Long id) {
        return tenancyRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteTenancy(Long id) {
        tenancyRepository.deleteById(id);
    }
    
    // Méthode pour ajouter des tenancies de test
    public void addTestTenancies() {
        Tenancy t1 = Tenancy.builder()
                .tenancyName("Tenancy A")
                .address(new Address("123 Rue A", "Paris", "75000", "France"))
                .dateCreated(LocalDateTime.now())
                .dateLastModified(LocalDateTime.now())
                .build();

        Tenancy t2 = Tenancy.builder()
                .tenancyName("Tenancy B")
                .address(new Address("456 Rue B", "Lyon", "69000", "France"))
                .dateCreated(LocalDateTime.now())
                .dateLastModified(LocalDateTime.now())
                .build();

        Tenancy t3 = Tenancy.builder()
                .tenancyName("Tenancy C")
                .address(new Address("789 Rue C", "Marseille", "13000", "France"))
                .dateCreated(LocalDateTime.now())
                .dateLastModified(LocalDateTime.now())
                .build();

        // Sauvegarder ces tenancies dans la base de données
        tenancyRepository.save(t1);
        tenancyRepository.save(t2);
        tenancyRepository.save(t3);
    }

    @Override
    public void createTenancyFromWelcomeForm(NewTenancyDTO newTenancyDTO) {
        Tenancy tenancy = new Tenancy();
        Graphism graphism  = new Graphism();
        HomePageContent homePageContent = new HomePageContent();
        for(ValueDTO v : newTenancyDTO.getValues()) {
            if( v.getName().length() > 0) {
                ContentBlock cb = ContentBlock.builder()
                            .isValue(true)
                            .contentTitle(v.getName())
                            .contentText(v.getDescription())
                            .build();
                if(!v.getFile().isEmpty()) {
                    cb.setContentImgName(v.getFile().getOriginalFilename());
                    cb.setContentImgTypeMIME(v.getFile().getContentType());
                    try {
                       byte[] thebytes = v.getFile().getBytes();
                       cb.setContentImg(Base64.getEncoder().encodeToString(thebytes));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                cb.setHomePageContent(homePageContent);
                //contentBlockService.save(cb);
                homePageContent.getContents().add(cb);
            }
        }
        tenancy.setHomePageContent(homePageContent);
        tenancy.setTenancyName(newTenancyDTO.getTenancyName());
        graphism.setColorPalette(newTenancyDTO.getColorPalette());
        tenancy.setGraphism(graphism);
        tenancyRepository.save(tenancy);
    }

}
