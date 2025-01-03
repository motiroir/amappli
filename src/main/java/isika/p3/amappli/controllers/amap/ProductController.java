package isika.p3.amappli.controllers.amap;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import isika.p3.amappli.dto.amap.ContractDTO;
import isika.p3.amappli.dto.amap.ProductDTO;
import isika.p3.amappli.entities.contract.Contract;
import isika.p3.amappli.entities.contract.ContractType;
import isika.p3.amappli.entities.contract.ContractWeight;
import isika.p3.amappli.entities.contract.DeliveryDay;
import isika.p3.amappli.entities.contract.DeliveryRecurrence;
import isika.p3.amappli.entities.product.Product;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.repo.amappli.TenancyRepository;
import isika.p3.amappli.service.amap.AmapAdminUserService;
import isika.p3.amappli.service.amap.ProductService;

@Controller
@RequestMapping("/{tenancyAlias}/backoffice/products")
public class ProductController {

    private final ProductService productService;
	private final TenancyRepository tenancyRepository;
	private final AmapAdminUserService AmapAdminUserService;

    public ProductController(ProductService productService, TenancyRepository tenancyRepository, AmapAdminUserService AmapAdminUserService) {
        this.productService = productService;
        this.tenancyRepository = tenancyRepository;
        this.AmapAdminUserService = AmapAdminUserService;
          
    }
    
	/**
	 * Initializes custom data binding for LocalDate.
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if (text == null || text.isEmpty()) {
					setValue(null);
				} else {
					setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				}
			}
		});
	}
	
	/**
	 * Displays the form for adding a new product.
	 */
	@GetMapping("/form")
	public String showForm(Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		
		List<User> users = AmapAdminUserService.findSuppliers(tenancyAlias);
		String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		// Récupérer l'adresse de la tenancy
		Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
				.orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));
		Address address = tenancy.getAddress();
		
		model.addAttribute("product", new Product());
		model.addAttribute("tenancyAlias", tenancyAlias);
		model.addAttribute("deliveryRecurrence", Arrays.asList(DeliveryRecurrence.values()));
		model.addAttribute("deliveryDay", Arrays.asList(DeliveryDay.values()));
		model.addAttribute("users", users);
	    model.addAttribute("address", address);
		model.addAttribute("currentDate", currentDate);
		return "amap/back/products/product-form";
	}
	
	/**
	 * Saves a new product to the database.
	 */
	@PostMapping("/add")
	public String addProduct(@ModelAttribute("productDTO") ProductDTO productDTO, @PathVariable("tenancyAlias") String tenancyAlias) {
		productService.save(productDTO, tenancyAlias);
	    if (productDTO.getProductName() == null || productDTO.getProductName().isEmpty()) {
	        throw new IllegalArgumentException("Le champ 'Nom du produit' est obligatoire.");
	    }
		return "redirect:/" + tenancyAlias + "/backoffice/products/list";
	}
	
	/**
	 * Displays a list of all products.
	 */
	@GetMapping("/list")
	public String listProducts(Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		
		List<Product> products = productService.findAll();
		List<User> users = AmapAdminUserService.findSuppliers(tenancyAlias);
		
		Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
				.orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));
		
		model.addAttribute("users", users);
		model.addAttribute("products", products);
		model.addAttribute("tenancyAlias", tenancyAlias);
		return "amap/back/products/product-list";
	}
	
	/**
	 * Deletes a contract by its ID.
	 */
	@PostMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long id, @PathVariable("tenancyAlias") String tenancyAlias) {
	    productService.deleteById(id);
	    return "redirect:/" + tenancyAlias + "/backoffice/products/list";
	}
	
	/**
	 * Displays the edit form for a specific product.
	 */
	@GetMapping("/edit/{id}")
	public String editProductForm(@PathVariable("id") Long id, Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
	    Product product = productService.findById(id);

	    if (product == null) {
	        return "redirect:/amap/products/list"; // Redirige si le contrat n'existe pas
	    }

	    model.addAttribute("product", product);
	    model.addAttribute("tenancyAlias", tenancyAlias);
	    model.addAttribute("deliveryRecurrence", Arrays.asList(DeliveryRecurrence.values()));
	    model.addAttribute("deliveryDay", Arrays.asList(DeliveryDay.values()));

	    return "amap/back/products/product-edit"; // Nom de la vue pour le formulaire d'édition
	}

    
	/**
	 * Displays the details of a specific product.
	 */
	@GetMapping("/detail/{id}")
	public String viewProductDetail(@PathVariable("id") Long id, Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		Product product = productService.findById(id);
		if (product == null) {
			throw new IllegalArgumentException("Contrat introuvable pour l'ID : " + id);
		}
		model.addAttribute("product", product);
		model.addAttribute("tenancyAlias", tenancyAlias);
		return "amap/back/products/product-detail";
	}
	
	@PostMapping("/update")
	public String updateProduct(
	    @ModelAttribute("product") ProductDTO updatedProductDTO,
	    @RequestParam(value = "image", required = false) MultipartFile image, @PathVariable("tenancyAlias") String tenancyAlias) {
	    
		productService.updateProduct(updatedProductDTO, image, tenancyAlias);

		return "redirect:/" + tenancyAlias + "/backoffice/products/list";
	}
}
