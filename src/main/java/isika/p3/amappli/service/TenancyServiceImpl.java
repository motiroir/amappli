package isika.p3.amappli.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isika.p3.amappli.entities.tenancy.HomePageContent;
import isika.p3.amappli.dto.NewTenancyDTO;
import isika.p3.amappli.dto.ValueDTO;
import isika.p3.amappli.entities.tenancy.ColorPalette;
import isika.p3.amappli.entities.tenancy.ContentBlock;
import isika.p3.amappli.entities.tenancy.FontChoice;
import isika.p3.amappli.entities.tenancy.Graphism;
import isika.p3.amappli.entities.tenancy.Options;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.repo.TenancyRepository;

@Service
public class TenancyServiceImpl implements TenancyService{
	
	@Autowired
    private TenancyRepository tenancyRepository;
	
	
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
        // Création d'une Tenancy
        Tenancy t1 = Tenancy.builder()
                .tenancyName("BioColi")
                .tenancySlogan("Manger bio, c'est facile avec BioColi!")  
                .address(new Address("123 Rue A", "Paris", "75000", "France"))
                .dateCreated(LocalDateTime.now())
                .dateLastModified(LocalDateTime.now())
                .build();
        
        // Création du Graphism pour Tenancy
        Graphism graphism1 = Graphism.builder()
                .colorPalette(ColorPalette.PALETTE1)  
                .fontChoice(FontChoice.FUTURA)     
                .logoImgType("image/jpeg")
                .logoImg(Base64.getEncoder().encodeToString("logoBytesPlaceholder".getBytes())) 
                .tenancy(t1)
                .build();
        t1.setGraphism(graphism1);

        // Création du HomePageContent pour la Tenancy
        HomePageContent homePageContent1 = HomePageContent.builder()
                .subTitle("UN PANIER BIO, LOCAL et de SAISON")
                .showSuppliers(true)
                .tenancy(t1) // Association de HomePageContent avec Tenancy
                .build();
        
        // Création des 4 ContentBlock pour la Tenancy

        // 1. Le ContentBlock pour la présentation de l'AMAP
        ContentBlock presentationBlock = ContentBlock.builder()
                .isValue(false)  
                .contentTitle("Présentation de BioColi")
                .contentText("BioColi est une AMAP dédiée à la distribution de paniers bio, locaux et de saison.")
                .contentImgName("biocoli-presentation.jpg")
                .contentImgTypeMIME("image/jpeg")
                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes())) 
                .homePageContent(homePageContent1) 
                .build();
        
        // 2. ContentBlock pour la première valeur de l'AMAP
        ContentBlock valueBlock1 = ContentBlock.builder()
                .isValue(true)  // Ce block représente une valeur de l'AMAP
                .contentTitle("Soutien à l'Agriculture Durable")
                .contentText("Nous soutenons les pratiques agricoles respectueuses de l'environnement.")
                .contentImgName("value1.jpg")
                .contentImgTypeMIME("image/jpeg")
                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes())) 
                .homePageContent(homePageContent1) // Association avec HomePageContent
                .build();
        
        // 3. ContentBlock pour la deuxième valeur de l'AMAP
        ContentBlock valueBlock2 = ContentBlock.builder()
                .isValue(true)  // Ce block représente une valeur de l'AMAP
                .contentTitle("Transparence et Traçabilité")
                .contentText("Tous nos produits sont traçables et proviennent de fermes locales.")
                .contentImgName("value2.jpg")
                .contentImgTypeMIME("image/jpeg")
                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes())) 
                .homePageContent(homePageContent1) // Association avec HomePageContent
                .build();
        
        // 4. ContentBlock pour la troisième valeur de l'AMAP
        ContentBlock valueBlock3 = ContentBlock.builder()
                .isValue(true)  // Ce block représente une valeur de l'AMAP
                .contentTitle("Frais et Local")
                .contentText("Nos produits sont livrés directement des fermes locales aux consommateurs.")
                .contentImgName("value3.jpg")
                .contentImgTypeMIME("image/jpeg")
                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes())) 
                .homePageContent(homePageContent1) 
                .build();
        
        // Ajouter les ContentBlock dans HomePageContent
        homePageContent1.getContents().add(presentationBlock);
        homePageContent1.getContents().add(valueBlock1);
        homePageContent1.getContents().add(valueBlock2);
        homePageContent1.getContents().add(valueBlock3);

        // Associer le HomePageContent à la Tenancy
        t1.setHomePageContent(homePageContent1);

        // Sauvegarder la Tenancy avec ses ContentBlock et HomePageContent
        tenancyRepository.save(t1);
    }


    @Override
    public void createTenancyFromWelcomeForm(NewTenancyDTO newTenancyDTO) {
        Tenancy tenancy = new Tenancy();

        // Name
        tenancy.setTenancyName(newTenancyDTO.getTenancyName());
        // Address
        tenancy.setAddress(newTenancyDTO.getAddress());

        // Slogan
        tenancy.setTenancySlogan(newTenancyDTO.getTenancySlogan());

        // MembershipFee Price
        tenancy.setMembershipFeePrice(newTenancyDTO.getMembershipFeePrice());

        // Date created and last modified
        tenancy.setDateCreated(LocalDateTime.now());
        tenancy.setDateLastModified(LocalDateTime.now());

        // Graphism
        Graphism graphism  = new Graphism();
        graphism.setColorPalette(newTenancyDTO.getColorPalette());
        graphism.setFontChoice(newTenancyDTO.getFontChoice());
        if(!newTenancyDTO.getLogo().isEmpty()) {
            graphism.setLogoImgType(newTenancyDTO.getLogo().getContentType());
            try {
                byte[] thebytes = newTenancyDTO.getLogo().getBytes();
                graphism.setLogoImg(Base64.getEncoder().encodeToString(thebytes));
             } catch (IOException e) {
                 e.printStackTrace();
             }
        }
        graphism.setTenancy(tenancy);
        tenancy.setGraphism(graphism);

        // Options
        Options options = new Options();
        if(newTenancyDTO.isOption1()){
            options.setOption1Active(true);
            options.setOption2Active(false);
            options.setOption3Active(false);
        }
        else if(newTenancyDTO.isOption2()){
            options.setOption1Active(true);
            options.setOption2Active(true);
            options.setOption3Active(false);
        }
        else if(newTenancyDTO.isOption3()) {
            options.setOption1Active(true);
            options.setOption2Active(true);
            options.setOption3Active(true);
        }

        options.setTenancy(tenancy);
        tenancy.setOptions(options);
        // HomePageContent
        HomePageContent homePageContent = new HomePageContent();
        //homePageContent.setTenancy(tenancy);
        // The first block
        ContentBlock cbi = new ContentBlock();
        cbi.setContentTitle(newTenancyDTO.getFirstHomePageTitle());
        cbi.setContentText(newTenancyDTO.getFirstHomePageText());
        if(!newTenancyDTO.getFirstHomePagePic().isEmpty()){
            cbi.setContentImgName(newTenancyDTO.getFirstHomePagePic().getOriginalFilename());
            cbi.setContentImgTypeMIME(newTenancyDTO.getFirstHomePagePic().getContentType());
            try {
                    byte[] thebytes = newTenancyDTO.getFirstHomePagePic().getBytes();
                    cbi.setContentImg(Base64.getEncoder().encodeToString(thebytes));
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        cbi.setHomePageContent(homePageContent);

        homePageContent.getContents().add(cbi);

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
                homePageContent.getContents().add(cb);
            }
        }
        tenancy.setHomePageContent(homePageContent);
        
        tenancyRepository.save(tenancy);
    }

}
