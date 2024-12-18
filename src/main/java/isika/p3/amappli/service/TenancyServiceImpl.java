package isika.p3.amappli.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
