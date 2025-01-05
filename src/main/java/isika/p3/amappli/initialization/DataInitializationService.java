package isika.p3.amappli.initialization;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import isika.p3.amappli.entities.auth.Permission;
import isika.p3.amappli.entities.auth.Role;
import isika.p3.amappli.entities.tenancy.ColorPalette;
import isika.p3.amappli.entities.tenancy.ContentBlock;
import isika.p3.amappli.entities.tenancy.FontChoice;
import isika.p3.amappli.entities.tenancy.Graphism;
import isika.p3.amappli.entities.tenancy.HomePageContent;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.entities.user.ContactInfo;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.repo.amap.RoleRepository;
import isika.p3.amappli.repo.amap.UserRepository;
import isika.p3.amappli.repo.amappli.PermissionRepository;
import isika.p3.amappli.repo.amappli.TenancyRepository;
import jakarta.transaction.Transactional;

@Service
public class DataInitializationService {

	@Autowired
	private TenancyRepository tenancyRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void dataInit() {
		try {
			tenancyInit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userInit();
	}
	
	@Transactional
	public void userInit() {

		// permissions creation
		Permission permission1 = Permission.builder().name("Permission 1").build();
		Permission permission2 = Permission.builder().name("Permission 2").build();

		permissionRepository.save(permission1);
		permissionRepository.save(permission2);

		// roles creation
		Role roleA = Role.builder().name("Role A").build();
		Role roleB = Role.builder().name("Role B").build();

		roleA.getPermissions().add(permission1);
		roleB.getPermissions().add(permission1);
		roleB.getPermissions().add(permission2);

		roleRepository.save(roleA);
		roleRepository.save(roleB);

		// users creation
		User user1 = User.builder().email("marie.durand@example.com").password("AMAPamap11@")
				.address(Address.builder().line2("5 avenue des Roses").line1("Appartement 12").postCode("44000")
						.city("Nantes").build())
				.contactInfo(ContactInfo.builder().name("Durand").firstName("Marie").phoneNumber("0601010101").build())
				.isActive(true).build();
		user1.getRoles().add(roleA);
		saveUser(user1);

		User user2 = User.builder().email("lucas.martin@example.com").password("AMAPamap11@")
				.address(Address.builder().line2("12 rue de la Liberté").line1("Bâtiment B").postCode("44100")
						.city("Nantes").build())
				.contactInfo(ContactInfo.builder().name("Martin").firstName("Lucas").phoneNumber("0612345678").build())
				.isActive(true).build();
		user2.getRoles().add(roleB);
		saveUser(user2);

		User user3 = User.builder().email("jeanne.lemoine@example.com").password("AMAPamap11@")
				.address(Address.builder().line2("15 boulevard des Alpes").line1("").postCode("44120")
						.city("Vertou").build())
				.contactInfo(
						ContactInfo.builder().name("Lemoine").firstName("Jeanne").phoneNumber("0678987654").build())
				.isActive(true).build();
		user3.getRoles().add(roleA);
		saveUser(user3);

		User user4 = User.builder().email("thomas.dupuis@example.com").password("AMAPamap11@")
				.address(Address.builder().line2("28 rue du Marché").line1("").postCode("44200").city("Nantes").build())
				.contactInfo(ContactInfo.builder().name("Dupuis").firstName("Thomas").phoneNumber("0611223344").build())
				.isActive(true).build();
		user4.getRoles().add(roleA);
		saveUser(user4);

		User user5 = User.builder().email("claire.fournier@example.com").password("AMAPamap11@")
				.address(Address.builder().line2("31 rue des Lilas").line1("Appartement 4").postCode("44130")
						.city("Machecoul").build())
				.contactInfo(
						ContactInfo.builder().name("Fournier").firstName("Claire").phoneNumber("0698765432").build())
				.isActive(true).build();
		user5.getRoles().add(roleB);
		saveUser(user5);

		User user6 = User.builder().email("charlotte.petit@example.com").password("AMAPamap11@")
				.address(Address.builder().line2("42 rue du Soleil").line1("").postCode("35000").city("Rennes").build())
				.contactInfo(
						ContactInfo.builder().name("Petit").firstName("Charlotte").phoneNumber("0612345670").build())
				.isActive(true).build();
		user6.getRoles().add(roleA);
		saveUser(user6);

		User user7 = User.builder().email("victor.martin@example.com").password("AMAPamap11@")
				.address(Address.builder().line2("7 place des Halles").line1("").postCode("13000").city("Marseille")
						.build())
				.contactInfo(ContactInfo.builder().name("Martin").firstName("Victor").phoneNumber("0677654321").build())
				.isActive(true).build();
		user7.getRoles().add(roleB);
		saveUser(user7);

		User user8 = User.builder().email("elise.muller@example.com").password("AMAPamap11@")
				.address(Address.builder().line2("56 avenue du Général Leclerc").line1("Batiment A").postCode("60000")
						.city("Beauvais").build())
				.contactInfo(ContactInfo.builder().name("Muller").firstName("Elise").phoneNumber("0698765430").build())
				.isActive(true).build();
		user8.getRoles().add(roleA);
		saveUser(user8);

		User user9 = User.builder().email("jean.benoit@example.com").password("AMAPamap11@")
				.address(Address.builder().line2("23 rue de la République").line1("Appartement 7").postCode("75001")
						.city("Paris").build())
				.contactInfo(ContactInfo.builder().name("Benoit").firstName("Jean").phoneNumber("0687654321").build())
				.isActive(true).build();
		user9.getRoles().add(roleB);
		saveUser(user9);

		User user10 = User.builder().email("amelie.rousseau@example.com").password("AMAPamap11@")
				.address(
						Address.builder().line2("1 avenue de la Mer").line1("").postCode("30000").city("Nîmes").build())
				.contactInfo(
						ContactInfo.builder().name("Rousseau").firstName("Amélie").phoneNumber("0623456789").build())
				.isActive(true).build();
		user10.getRoles().add(roleA);
		saveUser(user10);

		User user11 = User.builder().email("henri.durand@example.com").password("AMAPamap11@")
				.address(Address.builder().line2("19 rue des Fleurs").line1("Appartement 5").postCode("33000")
						.city("Bordeaux").build())
				.contactInfo(ContactInfo.builder().name("Durand").firstName("Henri").phoneNumber("0645671234").build())
				.isActive(true).build();
		user11.getRoles().add(roleA);
		saveUser(user11);

		User user12 = User.builder().email("manon.fabre@example.com").password("AMAPamap11@")
				.address(Address.builder().line2("22 rue des Lilas").line1("Rez-de-chaussée").postCode("92000")
						.city("Nanterre").build())
				.contactInfo(ContactInfo.builder().name("Fabre").firstName("Manon").phoneNumber("0691234567").build())
				.isActive(true).build();
		user12.getRoles().add(roleB);
		saveUser(user12);

		User user13 = User.builder().email("paul.brun@example.com").password("AMAPamap11@")
				.address(Address.builder().line2("8 avenue de la Gare").line1("2ème étage").postCode("44000")
						.city("Nantes").build())
				.contactInfo(ContactInfo.builder().name("Brun").firstName("Paul").phoneNumber("0687987654").build())
				.isActive(true).build();
		user13.getRoles().add(roleA);
		saveUser(user13);

		User user14 = User.builder().email("sophie.martin@example.com").password("AMAPamap11@")
				.address(Address.builder().line2("13 rue de la Liberté").line1("").postCode("44000").city("Nantes")
						.build())
				.contactInfo(ContactInfo.builder().name("Martin").firstName("Sophie").phoneNumber("0678987654").build())
				.isActive(true).build();
		user14.getRoles().add(roleB);
		saveUser(user14);

		User user15 = User.builder().email("lucie.lafaye@example.com").password("AMAPamap11@")
				.address(Address.builder().line2("5 rue du Pont").line1("").postCode("69000").city("Lyon").build())
				.contactInfo(ContactInfo.builder().name("Lafaye").firstName("Lucie").phoneNumber("0694567890").build())
				.isActive(true).build();
		user15.getRoles().add(roleA);
		saveUser(user15);

		User user16 = User.builder().email("alain.dubois@example.com").password("AMAPamap11@")
				.address(Address.builder().line2("20 rue de la Gare").line1("").postCode("75002").city("Paris").build())
				.contactInfo(ContactInfo.builder().name("Dubois").firstName("Alain").phoneNumber("0685559876").build())
				.isActive(true).build();
		user16.getRoles().add(roleB);
		saveUser(user16);

		User user17 = User.builder().email("laura.lemoine@example.com").password("AMAPamap11@")
				.address(
						Address.builder().line2("11 rue de la Paix").line1("").postCode("35000").city("Rennes").build())
				.contactInfo(ContactInfo.builder().name("Lemoine").firstName("Laura").phoneNumber("0622345678").build())
				.isActive(true).build();
		user17.getRoles().add(roleA);
		saveUser(user17);

		User user18 = User.builder().email("antoine.morel@example.com").password("AMAPamap11@")
				.address(Address.builder().line2("14 rue des Champs").line1("").postCode("54000").city("Nancy").build())
				.contactInfo(ContactInfo.builder().name("Morel").firstName("Antoine").phoneNumber("0686123456").build())
				.isActive(true).build();
		user18.getRoles().add(roleA);
		saveUser(user18);

		User user19 = User.builder().email("laurence.vincent@example.com").password("AMAPamap11@")
				.address(Address.builder().line2("5 rue de la Liberté").line1("Appartement 2").postCode("74000")
						.city("Annecy").build())
				.contactInfo(
						ContactInfo.builder().name("Vincent").firstName("Laurence").phoneNumber("0623456789").build())
				.isActive(true).build();
		user19.getRoles().add(roleB);
		saveUser(user19);

		User user20 = User.builder().email("bernard.morvan@example.com").password("AMAPamap11@")
				.address(
						Address.builder().line2("23 rue de la Lune").line1("").postCode("56000").city("Vannes").build())
				.contactInfo(
						ContactInfo.builder().name("Morvan").firstName("Bernard").phoneNumber("0612347890").build())
				.isActive(true).build();
		user20.getRoles().add(roleA);
		saveUser(user20);

	}

	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public void tenancyInit() throws IOException {
		// Création d'une Tenancy
		Tenancy t1 = Tenancy.builder().tenancyName("BioColi").tenancyAlias("biocoli")
				.tenancySlogan("Manger bio, c'est facile avec BioColi!").email("contact@biocoli.fr")
				.address(new Address("A12", "12 avenue de la localité", "69000", "Lyon"))
				.dateCreated(LocalDateTime.now()).dateLastModified(LocalDateTime.now()).tenancyLatitude("45.7400000") // lyon
				.tenancyLongitude("4.6370000").build();

		// Création du ContactInfo pour la Tenancy
		ContactInfo contactInfo1 = ContactInfo.builder().name("Jean").firstName("Dupont").phoneNumber("0612345678")
				.build();
		t1.setContactInfo(contactInfo1);

		// Chargement des images en Base64
		String imagelogo = loadImageFromResources("logobiocoli.png");

		// Création du Graphism pour Tenancy
		Graphism graphism1 = Graphism.builder().colorPalette(ColorPalette.PALETTE1).fontChoice(FontChoice.FUTURA)
				.logoImgType("image/png").logoImg(imagelogo).build();
		t1.setGraphism(graphism1);

		// Création du HomePageContent pour la Tenancy
		HomePageContent homePageContent1 = HomePageContent.builder().subTitle("UN PANIER BIO, LOCAL et de SAISON")
				.showSuppliers(true).tenancy(t1) // Association de HomePageContent avec Tenancy
				.build();

		// Création des 4 ContentBlocks pour la Tenancy
		String imagevalue1 = loadImageFromResources("value1.png");
		String imagevalue2 = loadImageFromResources("value2.png");
		String imagevalue3 = loadImageFromResources("value3.png");

		// 1. Le ContentBlock pour la présentation de l'AMAP
		ContentBlock presentationBlock = ContentBlock.builder().isValue(false).contentTitle("Présentation de BioColi")
				.contentText(
						"BioColi est une AMAP dédiée à la distribution de paniers bio, locaux et de saison, visant à reconnecter les consommateurs avec des produits de qualité, cultivés près de chez eux. À travers notre plateforme, nous mettons en avant des produits frais, issus de fermes locales qui respectent des pratiques agricoles durables. Notre engagement va au-delà de la simple consommation : nous offrons aux membres une expérience enrichissante, où l’échange direct avec les producteurs permet de mieux comprendre l'origine des produits et de soutenir une agriculture plus respectueuse de l'environnement. Rejoindre BioColi, c'est faire le choix d’un avenir plus responsable, local et respectueux de la planète.")
				.contentImgName("value1.png").contentImgTypeMIME("image/png").contentImg(imagelogo) // Chargé depuis les
																									// ressources
																									// internes
				.homePageContent(homePageContent1).build();

		// 2. ContentBlock pour la première valeur de l'AMAP
		ContentBlock valueBlock1 = ContentBlock.builder().isValue(true) // Ce block représente une valeur de l'AMAP
				.contentTitle("Soutien à l'Agriculture Durable")
				.contentText(
						"Chez BioColi, nous nous engageons fermement à soutenir l'agriculture durable en collaborant avec des producteurs locaux qui partagent notre vision d’une agriculture respectueuse de l’environnement. Nos fermes partenaires adoptent des pratiques de culture biologique, préservent la biodiversité et minimisent l’utilisation de produits chimiques. En vous inscrivant chez BioColi, vous contribuez à encourager des pratiques agricoles respectueuses des écosystèmes, permettant ainsi de protéger les sols, l'eau et la santé des générations futures. Nous croyons que chaque geste compte, et que chaque panier de produits bio que vous consommez est un soutien direct à un modèle agricole plus vertueux.")
				.contentImgName("value1.png").contentImgTypeMIME("image/png").contentImg(imagevalue1) // Chargé depuis
																										// les
																										// ressources
																										// internes
				.homePageContent(homePageContent1).build();

		// 3. ContentBlock pour la deuxième valeur de l'AMAP
		ContentBlock valueBlock2 = ContentBlock.builder().isValue(true) // Ce block représente une valeur de l'AMAP
				.contentTitle("Transparence et Traçabilité")
				.contentText(
						"La transparence et la traçabilité sont au cœur de notre démarche chez BioColi. Chaque produit que vous recevez dans votre panier est issu de fermes locales partenaires, et nous nous assurons que vous puissiez suivre l’intégralité du parcours de vos produits. Grâce à une chaîne de production et de distribution maîtrisée, nous garantissons la qualité et la provenance de chaque ingrédient. Cette transparence vous permet de savoir exactement d'où provient ce que vous consommez, de la ferme jusqu’à votre assiette, et de soutenir des pratiques agricoles éthiques et responsables. Chez BioColi, nous pensons que la confiance se construit sur des informations claires et accessibles, et nous mettons tout en œuvre pour que vous ayez l’assurance que vos choix sont les bons.")
				.contentImgName("value1.png").contentImgTypeMIME("image/png").contentImg(imagevalue2) // Chargé depuis
																										// les
																										// ressources
																										// internes
				.homePageContent(homePageContent1).build();

		// 4. ContentBlock pour la troisième valeur de l'AMAP
		ContentBlock valueBlock3 = ContentBlock.builder().isValue(true) // Ce block représente une valeur de l'AMAP
				.contentTitle("Frais et Local")
				.contentText(
						"Nos produits sont soigneusement récoltés à leur apogée, garantissant leur fraîcheur optimale, et sont livrés directement des fermes locales à votre domicile, en respectant les circuits courts. Cette approche non seulement vous assure des produits ultra-frais, mais réduit également notre empreinte carbone en limitant les transports longue distance. En choisissant BioColi, vous faites le choix de soutenir l'économie locale tout en réduisant l'impact environnemental des produits que vous consommez. Nos producteurs, fiers de leurs savoir-faire, cultivent des fruits, légumes et autres produits dans le respect des saisons, vous offrant ainsi des aliments naturellement savoureux, tout en préservant la planète.")
				.contentImgName("value1.png").contentImgTypeMIME("image/png").contentImg(imagevalue3)
				.homePageContent(homePageContent1).build();

		// Ajouter les ContentBlocks dans HomePageContent
		homePageContent1.getContents().add(presentationBlock);
		homePageContent1.getContents().add(valueBlock1);
		homePageContent1.getContents().add(valueBlock2);
		homePageContent1.getContents().add(valueBlock3);

		// Associer le HomePageContent à la Tenancy
		t1.setHomePageContent(homePageContent1);

		// Sauvegarder la Tenancy avec ses ContentBlocks et HomePageContent
		tenancyRepository.save(t1);

		// SECOND TENANCY
		// Création d'une Tenancy
		Tenancy t2 = Tenancy.builder().tenancyName("Agrinov").tenancyAlias("agrinov")
				.tenancySlogan("L'innovation au service de l'agriculture de proximité").email("agrinov@gmail.com")
				.address(new Address("", "Cave voutée de la Garenne Valentin", "63000", "Clermont-Ferrand"))
				.dateCreated(LocalDateTime.now()).dateLastModified(LocalDateTime.now()).tenancyLatitude("45.6150000") // clermont-ferrand
				.tenancyLongitude("3.2680000").build();

		// Création du ContactInfo pour la Tenancy
		ContactInfo contactInfo2 = ContactInfo.builder().name("Jean").firstName("Dupont").phoneNumber("0622345678")
				.build();
		t2.setContactInfo(contactInfo2);

		// Création du Graphism pour Tenancy
		Graphism graphism2 = Graphism.builder().colorPalette(ColorPalette.PALETTE2).fontChoice(FontChoice.NUNITO)
				.logoImgType("image/png").logoImg(Base64.getEncoder().encodeToString("logoBytesPlaceholder".getBytes()))
				.tenancy(t2).build();
		t2.setGraphism(graphism2);

		// Création du HomePageContent pour la Tenancy
		HomePageContent homePageContent2 = HomePageContent.builder().subTitle("Un panier bio, local et de saison.")
				.showSuppliers(true).tenancy(t2) // Association de HomePageContent avec Tenancy
				.build();

		// Création des 4 ContentBlock pour la Tenancy

		// 1. Le ContentBlock pour la présentation de l'AMAP
		ContentBlock presentationBlock2 = ContentBlock.builder().isValue(false).contentTitle("Présentation d'Agrinov")
				.contentText(
						"Agrinov est une AMAP qui a pour mission de promouvoir l’agriculture locale, bio et responsable, en facilitant l’accès à des paniers de produits de saison. En tant que plateforme, Agrinov permet aux consommateurs de soutenir directement les producteurs locaux et de participer à un modèle de consommation plus durable et respectueux de l'environnement. Nous mettons en valeur une sélection de produits frais, cultivés dans des fermes locales qui respectent des méthodes agricoles écologiques. Chaque panier livré chez vous porte l’engagement d’une agriculture plus verte, plus solidaire et plus transparente, contribuant ainsi à un cercle vertueux entre producteurs et consommateurs. Rejoindre Agrinov, c’est prendre part à un projet de société, où l'alimentation saine et locale est au cœur de notre action pour un monde plus durable.")
				.contentImgName("agrinov-presentation.jpg").contentImgTypeMIME("image/jpeg")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent2).build();

		// 2. ContentBlock pour la première valeur de l'AMAP
		ContentBlock valueBlock21 = ContentBlock.builder().isValue(true) // Ce block représente une valeur de l'AMAP
				.contentTitle("Soutien à l'Agriculture Durable")
				.contentText(
						"Agrinov s’engage pleinement à soutenir l’agriculture durable en collaborant avec des fermes locales pratiquant une agriculture respectueuse de l’environnement. Nos partenaires utilisent des techniques agricoles biologiques qui préservent les sols, la biodiversité et l’eau, tout en réduisant les émissions de gaz à effet de serre. En choisissant Agrinov, vous contribuez à une production alimentaire plus responsable, tout en soutenant un modèle agricole qui respecte la nature et les générations futures. Nos membres participent à une démarche active de préservation de l’environnement, en consommant des produits frais et locaux, en saison, réduisant ainsi leur empreinte écologique.")
				.contentImgName("value1.jpg").contentImgTypeMIME("image/jpeg")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent2) // Association avec HomePageContent
				.build();

		// 3. ContentBlock pour la deuxième valeur de l'AMAP
		ContentBlock valueBlock22 = ContentBlock.builder().isValue(true) // Ce block représente une valeur de l'AMAP
				.contentTitle("Transparence et Traçabilité")
				.contentText(
						"À Agrinov, la transparence est au cœur de notre démarche. Tous les produits que nous distribuons proviennent de fermes locales et sont totalement traçables. Nous garantissons à nos membres une transparence totale sur l’origine de chaque produit, de la ferme à l’assiette. Cette transparence vous permet de savoir exactement qui produit ce que vous consommez, et sous quelles conditions. Chaque produit est accompagné de son parcours, du champ jusqu’à la livraison, afin que vous puissiez être certain de soutenir une agriculture respectueuse, éthique et locale. Nous croyons fermement que la confiance se bâtit sur la transparence et l'accès à l'information.")
				.contentImgName("value2.jpg").contentImgTypeMIME("image/jpeg")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent2) // Association avec HomePageContent
				.build();

		// 4. ContentBlock pour la troisième valeur de l'AMAP
		ContentBlock valueBlock23 = ContentBlock.builder().isValue(true) // Ce block représente une valeur de l'AMAP
				.contentTitle("Frais et Local")
				.contentText(
						"Les produits que vous recevez via Agrinov sont toujours frais, car ils proviennent directement des fermes locales, cueillis à leur apogée de maturité. Ce modèle de distribution permet de garantir une qualité exceptionnelle et une fraîcheur incomparable, tout en réduisant l’impact environnemental lié au transport. En choisissant nos paniers, vous participez activement à une économie circulaire, en soutenant des fermes locales qui cultivent des produits en fonction des saisons. Chaque panier est une invitation à découvrir des produits nouveaux, frais et locaux, qui respectent les cycles naturels de la terre et vous offrent un goût authentique.")
				.contentImgName("value3.jpg").contentImgTypeMIME("image/jpeg")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent2).build();

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
		Tenancy t3 = Tenancy.builder().tenancyName("Groots").tenancyAlias("groots")
				.tenancySlogan("Avec vous jusqu'au bout des racines").email("groots@gmail.com")
				.address(new Address("", "16 rue D'Astorg", "44100", "Nantes")).dateCreated(LocalDateTime.now())
				.dateLastModified(LocalDateTime.now()).tenancyLatitude("46.5050000") // nantes -> exemple peu de
																						// dénivelés
				.tenancyLongitude("-0.5140000").build();

		// Création du ContactInfo pour la Tenancy
		ContactInfo contactInfo3 = ContactInfo.builder().name("Jean").firstName("Dupont").phoneNumber("0612345678")
				.build();
		t3.setContactInfo(contactInfo3);

		// Création du Graphism pour Tenancy
		Graphism graphism3 = Graphism.builder().colorPalette(ColorPalette.PALETTE3).fontChoice(FontChoice.AUDIOWIDE)
				.logoImgType("image/jpeg")
				.logoImg(Base64.getEncoder().encodeToString("/resources/img/value2.jpg".getBytes())).tenancy(t3)
				.build();
		t3.setGraphism(graphism3);

		// Création du HomePageContent pour la Tenancy
		HomePageContent homePageContent3 = HomePageContent.builder().subTitle("Un panier bio, local et de saison.")
				.showSuppliers(true).tenancy(t3) // Association de HomePageContent avec Tenancy
				.build();

		// Création des 4 ContentBlock pour la Tenancy

		// 1. Le ContentBlock pour la présentation de l'AMAP
		ContentBlock presentationBlock3 = ContentBlock.builder().isValue(false)
				.contentTitle("Groots - Connectez-vous aux producteurs locaux")
				.contentText(
						"Groots est une AMAP innovante qui vous connecte directement avec vos producteurs locaux. Notre objectif est de faciliter l'accès à des produits frais, bio et locaux tout en garantissant une meilleure transparence dans la chaîne de distribution. Chaque panier de produits est l'occasion de soutenir l'agriculture locale, de découvrir des produits de saison cultivés dans le respect de l'environnement, et de renforcer les liens entre les consommateurs et les producteurs. Grâce à Groots, vous avez la possibilité de participer activement à un modèle économique durable et solidaire, où la qualité des produits et la transparence de la provenance sont au cœur de notre démarche.")
				.contentImgName("value2.jpg").contentImgTypeMIME("image/jpeg")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent3).build();

		// 2. ContentBlock pour la première valeur de l'AMAP
		ContentBlock valueBlock31 = ContentBlock.builder().isValue(true) // Ce block représente une valeur de l'AMAP
				.contentTitle("Soutien à l'Agriculture Durable")
				.contentText(
						"Groots soutient une agriculture durable qui respecte l'environnement et les principes de l'agroécologie. En favorisant les fermes locales qui adoptent des méthodes de culture biologiques et respectueuses des sols, nous participons activement à la préservation de la biodiversité et des ressources naturelles. Nos membres soutiennent des producteurs qui travaillent en harmonie avec la nature, en utilisant des pratiques agricoles visant à réduire l'empreinte écologique et à favoriser la santé des écosystèmes. Chaque panier acheté est un pas de plus vers un avenir plus vert et plus respectueux de notre planète.")
				.contentImgName("value2.jpg").contentImgTypeMIME("image/jpeg")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent3) // Association avec HomePageContent
				.build();

		// 3. ContentBlock pour la deuxième valeur de l'AMAP
		ContentBlock valueBlock32 = ContentBlock.builder().isValue(true) // Ce block représente une valeur de l'AMAP
				.contentTitle("Transparence et Traçabilité")
				.contentText(
						"À Groots, nous croyons que la transparence est essentielle pour établir une relation de confiance entre les producteurs et les consommateurs. C'est pourquoi tous nos produits sont entièrement traçables. Chaque panier que vous recevez est une promesse de qualité et de transparence : vous pouvez suivre le parcours de chaque produit, depuis la ferme où il a été cultivé jusqu'à votre table. Grâce à notre plateforme, vous accédez à toutes les informations nécessaires pour connaître l'origine de vos produits et soutenir une économie locale et responsable.")
				.contentImgName("value2.jpg").contentImgTypeMIME("image/jpeg")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent3) // Association avec HomePageContent
				.build();

		// 4. ContentBlock pour la troisième valeur de l'AMAP
		ContentBlock valueBlock33 = ContentBlock.builder().isValue(true) // Ce block représente une valeur de l'AMAP
				.contentTitle("Frais et Local")
				.contentText(
						"Groots s'engage à vous offrir des produits frais, cultivés localement et livrés directement des fermes aux consommateurs. En choisissant Groots, vous choisissez la fraîcheur, la qualité et la proximité. Nos fermes partenaires cultivent des produits de saison, cueillis à maturité et livrés dans les plus brefs délais pour garantir une fraîcheur optimale. Ce modèle de distribution réduit l'empreinte carbone, soutient l'agriculture locale et permet à nos membres de savourer des produits en pleine saison, tout en renforçant les liens avec les producteurs locaux.")
				.contentImgName("value2.jpg").contentImgTypeMIME("image/jpeg")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent3).build();

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
		Tenancy t4 = Tenancy.builder().tenancyName("GreenMaven").tenancyAlias("greenmaven")
				.tenancySlogan("Un havre de fraîcheur pour vos produits locaux").email("greenHaven@gmail.com")
				.address(new Address("", "24 rue des Lilas", "65240", "Aulon")).dateCreated(LocalDateTime.now())
				.dateLastModified(LocalDateTime.now()).tenancyLatitude("46.0170000") // aulon, creuse
				.tenancyLongitude("1.6550000").build();

		// Création du ContactInfo pour la Tenancy
		ContactInfo contactInfo4 = ContactInfo.builder().name("Jean").firstName("Dupont").phoneNumber("0612345678")
				.build();
		t4.setContactInfo(contactInfo4);

		Graphism graphism4 = Graphism.builder().colorPalette(ColorPalette.PALETTE4).fontChoice(FontChoice.GRENZE)
				.logoImgType("image/png").logoImg(Base64.getEncoder().encodeToString("logoBytesPlaceholder".getBytes()))
				.tenancy(t4).build();
		t4.setGraphism(graphism4);

		HomePageContent homePageContent4 = HomePageContent.builder().subTitle("Manger sainement avec GreenHaven")
				.showSuppliers(true).tenancy(t4).build();

		// 1. Le ContentBlock pour la présentation de GreenHaven
		ContentBlock presentationBlock4 = ContentBlock.builder().isValue(false)
				.contentTitle("Présentation de GreenHaven")
				.contentText(
						"GreenHaven est une AMAP dédiée à la durabilité, à la qualité des produits et à la préservation de notre environnement. Nous proposons des paniers de produits locaux, cultivés dans le respect des cycles naturels et des principes de l'agriculture durable. Chaque panier contient des produits frais, soigneusement sélectionnés, cultivés par nos fermiers partenaires dans une démarche écoresponsable. En rejoignant GreenHaven, vous soutenez des pratiques agricoles qui respectent la biodiversité et l'équilibre écologique tout en bénéficiant de produits de saison, gages de qualité et de fraîcheur.")
				.contentImgName("greenhaven-presentation.jpg").contentImgTypeMIME("image/png")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent4).build();

		// 2. ContentBlock pour la première valeur de l'AMAP : Respect de la Nature
		ContentBlock valueBlock41 = ContentBlock.builder().isValue(true).contentTitle("Respect de la Nature")
				.contentText(
						"À GreenHaven, nous plaçons le respect de la nature au cœur de nos pratiques. Chaque producteur avec lequel nous collaborons adopte des méthodes de culture qui préservent la biodiversité, limitent l'utilisation de produits chimiques et favorisent des solutions naturelles. Nos fermiers prennent soin de la terre, respectent les cycles de la nature et utilisent des techniques qui restaurent la fertilité des sols. En choisissant GreenHaven, vous soutenez un modèle agricole durable qui protège notre planète pour les générations futures.")
				.contentImgName("value1.jpg").contentImgTypeMIME("image/png")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent4).build();

		// 3. ContentBlock pour la deuxième valeur de l'AMAP : Produits de Qualité
		ContentBlock valueBlock42 = ContentBlock.builder().isValue(true).contentTitle("Produits de Qualité")
				.contentText(
						"La qualité de nos produits est une priorité absolue. Chaque panier que nous proposons contient des produits soigneusement sélectionnés pour leur fraîcheur, leur saveur et leur qualité nutritionnelle. Nos fermiers, partenaires de confiance, cultivent des fruits, légumes et produits dérivés selon des critères stricts, garantissant des produits de saison et respectueux des normes les plus élevées en matière d'agriculture biologique et durable. En choisissant GreenHaven, vous êtes assuré de consommer des produits sains et de qualité supérieure, cultivés dans le respect de l'environnement.")
				.contentImgName("value2.jpg").contentImgTypeMIME("image/png")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent4).build();

		// 4. ContentBlock pour la troisième valeur de l'AMAP : Communauté Locale
		ContentBlock valueBlock43 = ContentBlock.builder().isValue(true).contentTitle("Communauté Locale").contentText(
				"GreenHaven est une AMAP ancrée dans sa communauté locale. Nous travaillons en étroite collaboration avec des producteurs locaux passionnés qui partagent notre vision d'une agriculture durable et responsable. Notre objectif est de renforcer les liens entre les membres de la communauté, de soutenir les fermiers locaux et de favoriser une économie circulaire. En rejoignant GreenHaven, vous devenez une partie prenante de ce réseau solidaire, où les produits de qualité se rencontrent avec des valeurs humaines fortes.")
				.contentImgName("value3.jpg").contentImgTypeMIME("image/png")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent4).build();

		homePageContent4.getContents().add(presentationBlock4);
		homePageContent4.getContents().add(valueBlock41);
		homePageContent4.getContents().add(valueBlock42);
		homePageContent4.getContents().add(valueBlock43);

		t4.setHomePageContent(homePageContent4);

		tenancyRepository.save(t4);

		// CINQUIÈME TENANCY
		Tenancy t5 = Tenancy.builder().tenancyName("La carotte de Chantenay").tenancyAlias("lacarottechantenay")
				.tenancySlogan("Des champs verts pour des générations durables").email("lacarottedechantenay@gmail.com")
				.address(new Address("", "25 avenue des Champs Verts", "74000", "Annecy"))
				.dateCreated(LocalDateTime.now()).dateLastModified(LocalDateTime.now()).tenancyLatitude("45.8440000") // annecy,
				.tenancyLongitude("6.1940000").build();

		// Création du ContactInfo pour la Tenancy
		ContactInfo contactInfo5 = ContactInfo.builder().name("Jean").firstName("Dupont").phoneNumber("0612345678")
				.build();
		t5.setContactInfo(contactInfo5);

		// Création du Graphism pour la Tenancy
		Graphism graphism5 = Graphism.builder().colorPalette(ColorPalette.PALETTE5).fontChoice(FontChoice.CAVEAT)
				.logoImgType("image/png").logoImg(Base64.getEncoder().encodeToString("logoBytesPlaceholder".getBytes()))
				.tenancy(t5).build();
		t5.setGraphism(graphism5);

		// Création du HomePageContent pour la Tenancy
		HomePageContent homePageContent5 = HomePageContent.builder().subTitle("Cultivons ensemble un avenir durable.")
				.showSuppliers(true).tenancy(t5).build();

		// Création des ContentBlock
		// 1. Le ContentBlock pour la présentation de La carotte de Chantenay
		ContentBlock presentationBlock5 = ContentBlock.builder().isValue(false)
				.contentTitle("Présentation de La carotte de Chantenay")
				.contentText(
						"La carotte de Chantenay est une AMAP dédiée à la production de légumes biologiques, locaux et de qualité, ancrée dans un engagement durable envers l'environnement. Nous mettons en avant la carotte de Chantenay, un produit emblématique, tout en diversifiant notre offre de légumes de saison cultivés avec passion. En rejoignant notre communauté, vous soutenez l'agriculture biologique tout en accédant à des produits frais et savoureux, cultivés dans le respect de la nature.")
				.contentImgName("greenfields-presentation.jpg").contentImgTypeMIME("image/jpeg")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent5).build();

		// 2. ContentBlock pour la première valeur de l'AMAP : Écologie et Durabilité
		ContentBlock valueBlock51 = ContentBlock.builder().isValue(true).contentTitle("Écologie et Durabilité")
				.contentText(
						"À La carotte de Chantenay, chaque produit soutient une agriculture écologique et durable. Nous nous engageons à respecter les principes de l'agriculture biologique et à utiliser des méthodes respectueuses de l'environnement. Nous préservons la biodiversité et limitons l'utilisation d'intrants chimiques, afin de cultiver des produits sains et respectueux des cycles naturels. Choisir notre AMAP, c'est participer activement à la protection de la planète en soutenant des pratiques agricoles responsables.")
				.contentImgName("value1-greenfields.jpg").contentImgTypeMIME("image/jpeg")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent5).build();

		// 3. ContentBlock pour la deuxième valeur de l'AMAP : Engagement Communautaire
		ContentBlock valueBlock52 = ContentBlock.builder().isValue(true).contentTitle("Engagement Communautaire")
				.contentText(
						"Nous croyons fermement en l'importance de l'engagement communautaire et en la collaboration avec les acteurs locaux. La carotte de Chantenay œuvre pour impliquer la communauté dans des actions concrètes en faveur de l'environnement et de l'agriculture durable. Nous organisons des événements, des ateliers et des rencontres avec nos producteurs afin de renforcer les liens entre les membres de l'AMAP et de sensibiliser à l'importance de soutenir l'agriculture locale. Ensemble, nous construisons un réseau solidaire qui profite à tous.")
				.contentImgName("value2-greenfields.jpg").contentImgTypeMIME("image/jpeg")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent5).build();

		// 4. ContentBlock pour la troisième valeur de l'AMAP : Qualité Supérieure
		ContentBlock valueBlock53 = ContentBlock.builder().isValue(true).contentTitle("Qualité Supérieure").contentText(
				"La carotte de Chantenay et les autres produits que nous proposons sont soigneusement sélectionnés pour leur goût et leur qualité. Chaque légume est cultivé avec soin dans des conditions optimales pour garantir une fraîcheur incomparable et des saveurs authentiques. Nos producteurs sont des experts passionnés, soucieux de produire des aliments sains et savoureux tout en respectant l'environnement. En choisissant notre AMAP, vous profitez de produits de saison, locaux et de qualité supérieure.")
				.contentImgName("value3-greenfields.jpg").contentImgTypeMIME("image/jpeg")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent5).build();

		homePageContent5.getContents().add(presentationBlock5);
		homePageContent5.getContents().add(valueBlock51);
		homePageContent5.getContents().add(valueBlock52);
		homePageContent5.getContents().add(valueBlock53);

		t5.setHomePageContent(homePageContent5);

		// Sauvegarder la Tenancy
		tenancyRepository.save(t5);

		// SIXIÈME TENANCY
		Tenancy t6 = Tenancy.builder().tenancyName("TerraLocal").tenancyAlias("terralocal")
				.tenancySlogan("Des produits de la Terre pour les gens d'ici").email("terralocal@gmail.com")
				.address(new Address("", "10 rue des Cultures", "13260", "Cassis")).dateCreated(LocalDateTime.now())
				.dateLastModified(LocalDateTime.now()).tenancyLatitude("43.2140000") // cassis, bord de mer
				.tenancyLongitude("5.5370000").build();

		// Création du ContactInfo pour la Tenancy
		ContactInfo contactInfo6 = ContactInfo.builder().name("Jean").firstName("Dupont").phoneNumber("0612345678")
				.build();
		t6.setContactInfo(contactInfo6);

		// Création du Graphism pour la Tenancy
		Graphism graphism6 = Graphism.builder().colorPalette(ColorPalette.PALETTE6).fontChoice(FontChoice.LIMELIGHT)
				.logoImgType("image/jpeg")
				.logoImg(Base64.getEncoder().encodeToString("logoBytesPlaceholder".getBytes())).tenancy(t6).build();
		t6.setGraphism(graphism6);

		// Création du HomePageContent pour la Tenancy
		HomePageContent homePageContent6 = HomePageContent.builder().subTitle("Vivez local, mangez local.")
				.showSuppliers(true).tenancy(t6).build();

		// 1. Le ContentBlock pour la présentation de TerraLocal
		ContentBlock presentationBlock6 = ContentBlock.builder().isValue(false)
				.contentTitle("Présentation de TerraLocal")
				.contentText(
						"TerraLocal est une AMAP engagée à mettre en avant les produits locaux afin de soutenir les agriculteurs de notre région. Nous croyons que chaque consommateur mérite d'avoir accès à des produits de qualité, cultivés près de chez lui, dans un respect total de l'environnement et des producteurs. Notre mission est de rapprocher les citoyens des producteurs locaux pour un futur plus durable, en offrant des produits frais, sains et responsables. Grâce à notre AMAP, vous soutenez directement les fermes locales tout en bénéficiant de produits locaux de haute qualité.")
				.contentImgName("terralocal-presentation.jpg").contentImgTypeMIME("image/jpeg")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent6).build();

		// 2. ContentBlock pour la première valeur de l'AMAP : Origine Garantie
		ContentBlock valueBlock61 = ContentBlock.builder().isValue(true).contentTitle("Origine Garantie").contentText(
				"À TerraLocal, nous certifions l'origine locale et biologique de tous nos produits. Chaque article que nous proposons provient directement des fermes de notre région, cultivé dans le respect des normes agricoles biologiques les plus strictes. Nos producteurs sont des artisans passionnés, soucieux de préserver l'environnement et de produire des aliments sains et goûteux. Choisir TerraLocal, c'est avoir la garantie d'une traçabilité totale et d'un respect profond des cycles naturels.")
				.contentImgName("value1-terralocal.jpg").contentImgTypeMIME("image/jpeg")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent6).build();

		// 3. ContentBlock pour la deuxième valeur de l'AMAP : Convivialité et Partage
		ContentBlock valueBlock62 = ContentBlock.builder().isValue(true).contentTitle("Convivialité et Partage")
				.contentText(
						"Nous mettons un accent particulier sur la convivialité et le partage au sein de notre communauté. TerraLocal organise régulièrement des événements, des rencontres et des ateliers pour renforcer les liens entre nos membres et nos producteurs locaux. Ces moments de partage permettent d’échanger autour de la Terre, de la production locale et de l'importance de consommer responsable. Nous croyons que la solidarité et la collaboration sont des piliers essentiels pour un monde plus juste et durable.")
				.contentImgName("value2-terralocal.jpg").contentImgTypeMIME("image/jpeg")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent6).build();

		// 4. ContentBlock pour la troisième valeur de l'AMAP : Respect des Saisons
		ContentBlock valueBlock63 = ContentBlock.builder().isValue(true).contentTitle("Respect des Saisons")
				.contentText(
						"Chez TerraLocal, nous respectons les cycles naturels des saisons pour vous offrir uniquement des produits de saison, cultivés dans les meilleures conditions. Nous croyons que chaque légume ou fruit a son moment idéal pour pousser et se récolter, et nous nous engageons à respecter ces cycles pour vous offrir des produits toujours frais, pleins de saveurs et bénéfiques pour votre santé. En consommant des produits de saison, vous contribuez à préserver l'environnement tout en soutenant les producteurs locaux.")
				.contentImgName("value3-terralocal.jpg").contentImgTypeMIME("image/jpeg")
				.contentImg(Base64.getEncoder().encodeToString("imageBytesPlaceholder".getBytes()))
				.homePageContent(homePageContent6).build();

		homePageContent6.getContents().add(presentationBlock6);
		homePageContent6.getContents().add(valueBlock61);
		homePageContent6.getContents().add(valueBlock62);
		homePageContent6.getContents().add(valueBlock63);

		t6.setHomePageContent(homePageContent6);

		// Sauvegarder la Tenancy
		tenancyRepository.save(t6);

	}

	private String loadImageFromResources(String imageName) throws IOException {
		InputStream imageStream = getClass().getClassLoader().getResourceAsStream("image/" + imageName);
		if (imageStream == null) {
			throw new IOException("Image not found in resources: " + imageName);
		}
		byte[] imageBytes = imageStream.readAllBytes();
		return Base64.getEncoder().encodeToString(imageBytes);
	}

}
