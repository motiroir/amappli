package isika.p3.amappli.service.amappli.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isika.p3.amappli.entities.tenancy.HomePageContent;
import isika.p3.amappli.dto.amappli.NewTenancyDTO;
import isika.p3.amappli.dto.amappli.ValueDTO;
import isika.p3.amappli.entities.tenancy.ColorPalette;
import isika.p3.amappli.entities.tenancy.ContentBlock;
import isika.p3.amappli.entities.tenancy.FontChoice;
import isika.p3.amappli.entities.tenancy.Graphism;
import isika.p3.amappli.entities.tenancy.Options;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.repo.amappli.TenancyRepository;
import isika.p3.amappli.service.amappli.TenancyService;

@Service
public class TenancyServiceImpl implements TenancyService {

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
                                .tenancyAlias("biocoli")
                                .tenancySlogan("Manger bio, c'est facile avec BioColi!")
                                .address(new Address("A12", "12 avenue de la localité", "75000", "Paris"))
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
                                .isValue(true) // Ce block représente une valeur de l'AMAP
                                .contentTitle("Soutien à l'Agriculture Durable")
                                .contentText("Nous soutenons les pratiques agricoles respectueuses de l'environnement.")
                                .contentImgName("value1.jpg")
                                .contentImgTypeMIME("image/jpeg")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent1) // Association avec HomePageContent
                                .build();

                // 3. ContentBlock pour la deuxième valeur de l'AMAP
                ContentBlock valueBlock2 = ContentBlock.builder()
                                .isValue(true) // Ce block représente une valeur de l'AMAP
                                .contentTitle("Transparence et Traçabilité")
                                .contentText("Tous nos produits sont traçables et proviennent de fermes locales.")
                                .contentImgName("value2.jpg")
                                .contentImgTypeMIME("image/jpeg")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent1) // Association avec HomePageContent
                                .build();

                // 4. ContentBlock pour la troisième valeur de l'AMAP
                ContentBlock valueBlock3 = ContentBlock.builder()
                                .isValue(true) // Ce block représente une valeur de l'AMAP
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

                // SECOND TENANCY
                // Création d'une Tenancy
                Tenancy t2 = Tenancy.builder()
                                .tenancyName("Agrinov")
                                .tenancyAlias("agrinov")
                                .tenancySlogan("L'innovation au service de l'agriculture de proximité")
                                .address(new Address("", "Cave voutée de la Garenne Valentin", "44190", "Clisson"))
                                .dateCreated(LocalDateTime.now())
                                .dateLastModified(LocalDateTime.now())
                                .build();

                // Création du Graphism pour Tenancy
                Graphism graphism2 = Graphism.builder()
                                .colorPalette(ColorPalette.PALETTE2)
                                .fontChoice(FontChoice.NUNITO)
                                .logoImgType("image/jpeg")
                                .logoImg(Base64.getEncoder().encodeToString("logoBytesPlaceholder".getBytes()))
                                .tenancy(t2)
                                .build();
                t2.setGraphism(graphism2);

                // Création du HomePageContent pour la Tenancy
                HomePageContent homePageContent2 = HomePageContent.builder()
                                .subTitle("Un panier bio, local et de saison.")
                                .showSuppliers(true)
                                .tenancy(t2) // Association de HomePageContent avec Tenancy
                                .build();

                // Création des 4 ContentBlock pour la Tenancy

                // 1. Le ContentBlock pour la présentation de l'AMAP
                ContentBlock presentationBlock2 = ContentBlock.builder()
                                .isValue(false)
                                .contentTitle("Présentation d'Agrinov")
                                .contentText("Agrinov est une AMAP dédiée à la distribution de paniers bio, locaux et de saison.")
                                .contentImgName("agrinov-presentation.jpg")
                                .contentImgTypeMIME("image/jpeg")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent2)
                                .build();

                // 2. ContentBlock pour la première valeur de l'AMAP
                ContentBlock valueBlock21 = ContentBlock.builder()
                                .isValue(true) // Ce block représente une valeur de l'AMAP
                                .contentTitle("Soutien à l'Agriculture Durable")
                                .contentText("Nous soutenons les pratiques agricoles respectueuses de l'environnement.")
                                .contentImgName("value1.jpg")
                                .contentImgTypeMIME("image/jpeg")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent2) // Association avec HomePageContent
                                .build();

                // 3. ContentBlock pour la deuxième valeur de l'AMAP
                ContentBlock valueBlock22 = ContentBlock.builder()
                                .isValue(true) // Ce block représente une valeur de l'AMAP
                                .contentTitle("Transparence et Traçabilité")
                                .contentText("Tous nos produits sont traçables et proviennent de fermes locales.")
                                .contentImgName("value2.jpg")
                                .contentImgTypeMIME("image/jpeg")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent2) // Association avec HomePageContent
                                .build();

                // 4. ContentBlock pour la troisième valeur de l'AMAP
                ContentBlock valueBlock23 = ContentBlock.builder()
                                .isValue(true) // Ce block représente une valeur de l'AMAP
                                .contentTitle("Frais et Local")
                                .contentText("Nos produits sont livrés directement des fermes locales aux consommateurs.")
                                .contentImgName("value3.jpg")
                                .contentImgTypeMIME("image/jpeg")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent2)
                                .build();

                // Ajouter les ContentBlock dans HomePageContent
                homePageContent2.getContents().add(presentationBlock2);
                homePageContent2.getContents().add(valueBlock21);
                homePageContent2.getContents().add(valueBlock22);
                homePageContent2.getContents().add(valueBlock23);

                // Associer le HomePageContent à la Tenancy
                t2.setHomePageContent(homePageContent2);

                // Sauvegarder la Tenancy avec ses ContentBlock et HomePageContent
                tenancyRepository.save(t2);

                // THIRD TENANCY
                // Création d'une Tenancy
                Tenancy t3 = Tenancy.builder()
                                .tenancyName("Groots")
                                .tenancyAlias("groots")
                                .tenancySlogan("Avec vous jusqu'au bout des racines")
                                .address(new Address("", "16 rue D'Astorg", "31000", "Toulouse"))
                                .dateCreated(LocalDateTime.now())
                                .dateLastModified(LocalDateTime.now())
                                .build();

                // Création du Graphism pour Tenancy
                Graphism graphism3 = Graphism.builder()
                                .colorPalette(ColorPalette.PALETTE3)
                                .fontChoice(FontChoice.AUDIOWIDE)
                                .logoImgType("image/jpeg")
                                .logoImg(Base64.getEncoder().encodeToString("logoBytesPlaceholder".getBytes()))
                                .tenancy(t3)
                                .build();
                t3.setGraphism(graphism3);

                // Création du HomePageContent pour la Tenancy
                HomePageContent homePageContent3 = HomePageContent.builder()
                                .subTitle("Un panier bio, local et de saison.")
                                .showSuppliers(true)
                                .tenancy(t3) // Association de HomePageContent avec Tenancy
                                .build();

                // Création des 4 ContentBlock pour la Tenancy

                // 1. Le ContentBlock pour la présentation de l'AMAP
                ContentBlock presentationBlock3 = ContentBlock.builder()
                                .isValue(false)
                                .contentTitle("Présentation de Groots")
                                .contentText("Groots est une amap qui vous connectera avec vos producteurs locaux, pour une meilleure transparence et distribution des richesses")
                                .contentImgName("groots-presentation.jpg")
                                .contentImgTypeMIME("image/jpeg")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent3)
                                .build();

                // 2. ContentBlock pour la première valeur de l'AMAP
                ContentBlock valueBlock31 = ContentBlock.builder()
                                .isValue(true) // Ce block représente une valeur de l'AMAP
                                .contentTitle("Soutien à l'Agriculture Durable")
                                .contentText("Nous soutenons les pratiques agricoles respectueuses de l'environnement.")
                                .contentImgName("value1.jpg")
                                .contentImgTypeMIME("image/jpeg")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent3) // Association avec HomePageContent
                                .build();

                // 3. ContentBlock pour la deuxième valeur de l'AMAP
                ContentBlock valueBlock32 = ContentBlock.builder()
                                .isValue(true) // Ce block représente une valeur de l'AMAP
                                .contentTitle("Transparence et Traçabilité")
                                .contentText("Tous nos produits sont traçables et proviennent de fermes locales.")
                                .contentImgName("value2.jpg")
                                .contentImgTypeMIME("image/jpeg")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent3) // Association avec HomePageContent
                                .build();

                // 4. ContentBlock pour la troisième valeur de l'AMAP
                ContentBlock valueBlock33 = ContentBlock.builder()
                                .isValue(true) // Ce block représente une valeur de l'AMAP
                                .contentTitle("Frais et Local")
                                .contentText("Nos produits sont livrés directement des fermes locales aux consommateurs.")
                                .contentImgName("value3.jpg")
                                .contentImgTypeMIME("image/jpeg")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent3)
                                .build();

                // Ajouter les ContentBlock dans HomePageContent
                homePageContent3.getContents().add(presentationBlock3);
                homePageContent3.getContents().add(valueBlock31);
                homePageContent3.getContents().add(valueBlock32);
                homePageContent3.getContents().add(valueBlock33);

                // Associer le HomePageContent à la Tenancy
                t3.setHomePageContent(homePageContent3);

                // Sauvegarder la Tenancy avec ses ContentBlock et HomePageContent
                tenancyRepository.save(t3);

                // FOURTH TENANCY
                Tenancy t4 = Tenancy.builder()
                                .tenancyName("GreenHaven")
                                .tenancyAlias("greenmaven")
                                .tenancySlogan("Un havre de fraîcheur pour vos produits locaux")
                                .address(new Address("", "24 rue des Lilas", "69000", "Lyon"))
                                .dateCreated(LocalDateTime.now())
                                .dateLastModified(LocalDateTime.now())
                                .build();

                Graphism graphism4 = Graphism.builder()
                                .colorPalette(ColorPalette.PALETTE4)
                                .fontChoice(FontChoice.GRENZE)
                                .logoImgType("image/png")
                                .logoImg(Base64.getEncoder().encodeToString("logoBytesPlaceholder".getBytes()))
                                .tenancy(t4)
                                .build();
                t4.setGraphism(graphism4);

                HomePageContent homePageContent4 = HomePageContent.builder()
                                .subTitle("Manger sainement avec GreenHaven")
                                .showSuppliers(true)
                                .tenancy(t4)
                                .build();

                ContentBlock presentationBlock4 = ContentBlock.builder()
                                .isValue(false)
                                .contentTitle("Présentation de GreenHaven")
                                .contentText("GreenHaven est une AMAP centrée sur la durabilité et la fraîcheur des produits.")
                                .contentImgName("greenhaven-presentation.jpg")
                                .contentImgTypeMIME("image/png")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent4)
                                .build();

                ContentBlock valueBlock41 = ContentBlock.builder()
                                .isValue(true)
                                .contentTitle("Respect de la Nature")
                                .contentText("Nos méthodes de production respectent la biodiversité.")
                                .contentImgName("value1.jpg")
                                .contentImgTypeMIME("image/png")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent4)
                                .build();

                ContentBlock valueBlock42 = ContentBlock.builder()
                                .isValue(true)
                                .contentTitle("Produits de Qualité")
                                .contentText("Chaque produit est soigneusement sélectionné.")
                                .contentImgName("value2.jpg")
                                .contentImgTypeMIME("image/png")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent4)
                                .build();

                ContentBlock valueBlock43 = ContentBlock.builder()
                                .isValue(true)
                                .contentTitle("Communauté Locale")
                                .contentText("Nous travaillons en collaboration avec les producteurs locaux.")
                                .contentImgName("value3.jpg")
                                .contentImgTypeMIME("image/png")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent4)
                                .build();

                homePageContent4.getContents().add(presentationBlock4);
                homePageContent4.getContents().add(valueBlock41);
                homePageContent4.getContents().add(valueBlock42);
                homePageContent4.getContents().add(valueBlock43);

                t4.setHomePageContent(homePageContent4);

                tenancyRepository.save(t4);

                // CINQUIÈME TENANCY
                Tenancy t5 = Tenancy.builder()
                                .tenancyName("GreenFields")
                                .tenancyAlias("greenfields")
                                .tenancySlogan("Des champs verts pour des générations durables")
                                .address(new Address("", "25 avenue des Champs Verts", "69000", "Lyon"))
                                .dateCreated(LocalDateTime.now())
                                .dateLastModified(LocalDateTime.now())
                                .build();

                // Création du Graphism pour la Tenancy
                Graphism graphism5 = Graphism.builder()
                                .colorPalette(ColorPalette.PALETTE5)
                                .fontChoice(FontChoice.CAVEAT)
                                .logoImgType("image/png")
                                .logoImg(Base64.getEncoder().encodeToString("logoBytesPlaceholder".getBytes()))
                                .tenancy(t5)
                                .build();
                t5.setGraphism(graphism5);

                // Création du HomePageContent pour la Tenancy
                HomePageContent homePageContent5 = HomePageContent.builder()
                                .subTitle("Cultivons ensemble un avenir durable.")
                                .showSuppliers(true)
                                .tenancy(t5)
                                .build();

                // Création des ContentBlock
                ContentBlock presentationBlock5 = ContentBlock.builder()
                                .isValue(false)
                                .contentTitle("Présentation de GreenFields")
                                .contentText("GreenFields œuvre pour un monde plus vert avec des produits bio et locaux pour tous.")
                                .contentImgName("greenfields-presentation.jpg")
                                .contentImgTypeMIME("image/jpeg")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent5)
                                .build();

                ContentBlock valueBlock51 = ContentBlock.builder()
                                .isValue(true)
                                .contentTitle("Écologie et Durabilité")
                                .contentText("Chaque produit soutient l'agriculture écologique et durable.")
                                .contentImgName("value1-greenfields.jpg")
                                .contentImgTypeMIME("image/jpeg")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent5)
                                .build();

                ContentBlock valueBlock52 = ContentBlock.builder()
                                .isValue(true)
                                .contentTitle("Engagement Communautaire")
                                .contentText("Impliquer la communauté dans des actions locales pour l'environnement.")
                                .contentImgName("value2-greenfields.jpg")
                                .contentImgTypeMIME("image/jpeg")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent5)
                                .build();

                ContentBlock valueBlock53 = ContentBlock.builder()
                                .isValue(true)
                                .contentTitle("Qualité Supérieure")
                                .contentText("Des produits sélectionnés pour leur goût et leur qualité.")
                                .contentImgName("value3-greenfields.jpg")
                                .contentImgTypeMIME("image/jpeg")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent5)
                                .build();

                homePageContent5.getContents().add(presentationBlock5);
                homePageContent5.getContents().add(valueBlock51);
                homePageContent5.getContents().add(valueBlock52);
                homePageContent5.getContents().add(valueBlock53);

                t5.setHomePageContent(homePageContent5);

                // Sauvegarder la Tenancy
                tenancyRepository.save(t5);

                // SIXIÈME TENANCY
                Tenancy t6 = Tenancy.builder()
                                .tenancyName("TerraLocal")
                                .tenancyAlias("terralocal")
                                .tenancySlogan("Des produits de la Terre pour les gens d'ici")
                                .address(new Address("", "10 rue des Cultures", "33000", "Bordeaux"))
                                .dateCreated(LocalDateTime.now())
                                .dateLastModified(LocalDateTime.now())
                                .build();

                // Création du Graphism pour la Tenancy
                Graphism graphism6 = Graphism.builder()
                                .colorPalette(ColorPalette.PALETTE6)
                                .fontChoice(FontChoice.LIMELIGHT)
                                .logoImgType("image/jpeg")
                                .logoImg(Base64.getEncoder().encodeToString("logoBytesPlaceholder".getBytes()))
                                .tenancy(t6)
                                .build();
                t6.setGraphism(graphism6);

                // Création du HomePageContent pour la Tenancy
                HomePageContent homePageContent6 = HomePageContent.builder()
                                .subTitle("Vivez local, mangez local.")
                                .showSuppliers(true)
                                .tenancy(t6)
                                .build();

                // Création des ContentBlock
                ContentBlock presentationBlock6 = ContentBlock.builder()
                                .isValue(false)
                                .contentTitle("Présentation de TerraLocal")
                                .contentText("TerraLocal met en avant les produits locaux pour soutenir les agriculteurs d'ici.")
                                .contentImgName("terralocal-presentation.jpg")
                                .contentImgTypeMIME("image/jpeg")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent6)
                                .build();

                ContentBlock valueBlock61 = ContentBlock.builder()
                                .isValue(true)
                                .contentTitle("Origine Garantie")
                                .contentText("Nous certifions l'origine locale et bio de tous nos produits.")
                                .contentImgName("value1-terralocal.jpg")
                                .contentImgTypeMIME("image/jpeg")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent6)
                                .build();

                ContentBlock valueBlock62 = ContentBlock.builder()
                                .isValue(true)
                                .contentTitle("Convivialité et Partage")
                                .contentText("Des événements et des rencontres pour créer du lien autour de la Terre.")
                                .contentImgName("value2-terralocal.jpg")
                                .contentImgTypeMIME("image/jpeg")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent6)
                                .build();

                ContentBlock valueBlock63 = ContentBlock.builder()
                                .isValue(true)
                                .contentTitle("Respect des Saisons")
                                .contentText("Des produits uniquement en accord avec les cycles naturels.")
                                .contentImgName("value3-terralocal.jpg")
                                .contentImgTypeMIME("image/jpeg")
                                .contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
                                .homePageContent(homePageContent6)
                                .build();

                homePageContent6.getContents().add(presentationBlock6);
                homePageContent6.getContents().add(valueBlock61);
                homePageContent6.getContents().add(valueBlock62);
                homePageContent6.getContents().add(valueBlock63);

                t6.setHomePageContent(homePageContent6);

                // Sauvegarder la Tenancy
                tenancyRepository.save(t6);

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
                Graphism graphism = new Graphism();
                graphism.setColorPalette(newTenancyDTO.getColorPalette());
                graphism.setFontChoice(newTenancyDTO.getFontChoice());
                if (!newTenancyDTO.getLogo().isEmpty()) {
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
                if (newTenancyDTO.isOption1()) {
                        options.setOption1Active(true);
                        options.setOption2Active(false);
                        options.setOption3Active(false);
                } else if (newTenancyDTO.isOption2()) {
                        options.setOption1Active(true);
                        options.setOption2Active(true);
                        options.setOption3Active(false);
                } else if (newTenancyDTO.isOption3()) {
                        options.setOption1Active(true);
                        options.setOption2Active(true);
                        options.setOption3Active(true);
                }

                options.setTenancy(tenancy);
                tenancy.setOptions(options);
                // HomePageContent
                HomePageContent homePageContent = new HomePageContent();
                // homePageContent.setTenancy(tenancy);
                // The first block
                ContentBlock cbi = new ContentBlock();
                cbi.setContentTitle(newTenancyDTO.getFirstHomePageTitle());
                cbi.setContentText(newTenancyDTO.getFirstHomePageText());
                if (!newTenancyDTO.getFirstHomePagePic().isEmpty()) {
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

                for (ValueDTO v : newTenancyDTO.getValues()) {
                        if (v.getName().length() > 0) {
                                ContentBlock cb = ContentBlock.builder()
                                                .isValue(true)
                                                .contentTitle(v.getName())
                                                .contentText(v.getDescription())
                                                .build();
                                if (!v.getFile().isEmpty()) {
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

        @Override
        public Tenancy getTenancyByAlias(String alias) {
                return tenancyRepository.findByTenancyAlias(alias).get();
        }

        @Override
        public HomePageContent getHomePageContentByTenancyAlias(String alias) {
                Tenancy tenancy = tenancyRepository.findByTenancyAlias(alias).orElse(null);

                if (tenancy != null) {
                        return tenancy.getHomePageContent();
                }

                return null;
        }
}
