package isika.p3.amappli.controllers;

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

import isika.p3.amappli.dto.ContractDTO;
import isika.p3.amappli.dto.ProductDTO;
import isika.p3.amappli.entities.contract.Contract;
import isika.p3.amappli.entities.contract.ContractType;
import isika.p3.amappli.entities.contract.ContractWeight;
import isika.p3.amappli.entities.contract.DeliveryDay;
import isika.p3.amappli.entities.contract.DeliveryRecurrence;
import isika.p3.amappli.entities.product.Product;
import isika.p3.amappli.service.ProductService;

@Controller
@RequestMapping("/amap/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
          
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
	public String showForm(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("deliveryRecurrence", Arrays.asList(DeliveryRecurrence.values()));
		model.addAttribute("deliveryDay", Arrays.asList(DeliveryDay.values()));
		String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		model.addAttribute("currentDate", currentDate);
		return "amap/product-form";
	}
	
	/**
	 * Saves a new product to the database.
	 */
	@PostMapping("/add")
	public String addProduct(@ModelAttribute("productDTO") ProductDTO productDTO) {
		productService.save(productDTO);
		return "redirect:/amap/products/list";
	}
	
	/**
	 * Displays a list of all products.
	 */
	@GetMapping("/list")
	public String listProducts(Model model) {
		List<Product> products = productService.findAll();
		model.addAttribute("products", products);
		return "amap/product-list";
	}
	
	/**
	 * Deletes a contract by its ID.
	 */
	@PostMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long id) {
	    productService.deleteById(id);
	    return "redirect:/amap/products/list";
	}

    
	/**
	 * Displays the details of a specific product.
	 */
	@GetMapping("/detail/{id}")
	public String viewProductDetail(@PathVariable("id") Long id, Model model) {
		Product product = productService.findById(id);
		model.addAttribute("product", product);
		return "amap/product-detail";
	}
}
