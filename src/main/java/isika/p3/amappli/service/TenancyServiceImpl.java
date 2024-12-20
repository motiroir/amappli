package isika.p3.amappli.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isika.p3.amappli.entities.tenancy.HomePageContent;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.repo.HomePageContentRepository;
import isika.p3.amappli.repo.TenancyRepository;
@Service
public class TenancyServiceImpl implements TenancyService{
	
	@Autowired
    private TenancyRepository tenancyRepository;
	
	 @Autowired
	    private HomePageContentRepository homePageContentRepository; 
	
    @Override
    public HomePageContent getHomePageContentByTenancyId(Long id) {
        Tenancy tenancy = tenancyRepository.findById(id).orElse(null);
        
        if (tenancy != null) {
            return tenancy.getHomePageContent(); 
        }
        
        return null; 
    }

    
    @Override
    public Tenancy createTenancy(Tenancy tenancy) {
    	 // Vérifier si un HomePageContent doit être créé
        if (tenancy.getHomePageContent() == null) {
            HomePageContent homePageContent = HomePageContent.builder()
                    .subTitle("Default Subtitle")
                    .showSuppliers(false)
                    .tenancy(tenancy) // Associer au tenancy
                    .build();
            tenancy.setHomePageContent(homePageContent);
        }

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
                .tenancyName("BioColi")
                .address(new Address("123 Rue A", "Paris", "75000", "France"))
                .dateCreated(LocalDateTime.now())
                .dateLastModified(LocalDateTime.now())
                .build();
        
        HomePageContent homePageContent1 = HomePageContent.builder()
                .subTitle("UN PANIER BIO, LOCAL et de SAISON")
                .showSuppliers(true)
                .tenancy(t1)  
                .build();
        
        t1.setHomePageContent(homePageContent1);
        tenancyRepository.save(t1);
        
        
        Tenancy t2 = Tenancy.builder()
                .tenancyName("AgriNov")
                .address(new Address("456 Rue B", "Lyon", "69000", "France"))
                .dateCreated(LocalDateTime.now())
                .dateLastModified(LocalDateTime.now())
                .build();
        
        
        HomePageContent homePageContent2 = HomePageContent.builder()
                .subTitle("UN PANIER BIO, LOCAL et de SAISON")
                .showSuppliers(false)
                .tenancy(t2)
                .build();
   
        t2.setHomePageContent(homePageContent2);
        tenancyRepository.save(t2);
        
        
        Tenancy t3 = Tenancy.builder()
                .tenancyName("Groots")
                .address(new Address("789 Rue C", "Marseille", "13000", "France"))
                .dateCreated(LocalDateTime.now())
                .dateLastModified(LocalDateTime.now())
                .build();
        
        
        HomePageContent homePageContent3 = HomePageContent.builder()
                .subTitle("UN PANIER BIO, LOCAL et de SAISON")
                .showSuppliers(true)
                .tenancy(t3)
                .build();
      
        t3.setHomePageContent(homePageContent3);
        tenancyRepository.save(t3);
    }
}
