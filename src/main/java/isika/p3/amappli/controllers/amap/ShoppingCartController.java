package isika.p3.amappli.controllers.amap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import isika.p3.amappli.entities.contract.Contract;
import isika.p3.amappli.entities.order.ShoppingCart;
import isika.p3.amappli.entities.product.Product;
import isika.p3.amappli.entities.workshop.Workshop;
import isika.p3.amappli.security.CustomUserDetails;
import isika.p3.amappli.service.amap.GraphismService;
import isika.p3.amappli.service.amap.ShoppingCartService;

@Controller
@RequestMapping("/{tenancyAlias}/cart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private GraphismService graphismService;

	@GetMapping("/{cartId}")
	public String viewCart(@PathVariable("cartId") Long userId, @PathVariable("tenancyAlias") String alias,
			Model model) {
		// get user info from context (connected user) or userId = 1 when logout
		userId = getUserIdFromContext();

		ShoppingCart cart = shoppingCartService.getCartByUserId(userId);

		addGraphismAttributes(alias, model);
		model.addAttribute("cartId", userId); 
		model.addAttribute("cart", cart);
		model.addAttribute("total", shoppingCartService.calculateTotal(cart.getShoppingCartId()));

		// totals formulas for CONTRACTS and PRODUCTS
		double totalContracts = cart.getShoppingCartItems().stream()
				.filter(item -> item.getShoppable() instanceof Contract)
				.mapToDouble(item -> item.getShoppable().getPrice() * item.getQuantity()).sum();

		double totalProducts = cart.getShoppingCartItems().stream()
				.filter(item -> item.getShoppable() instanceof Product)
				.mapToDouble(item -> item.getShoppable().getPrice() * item.getQuantity()).sum();

		double totalWorkshops = cart.getShoppingCartItems().stream()
				.filter(item -> item.getShoppable() instanceof Workshop)
				.mapToDouble(item -> item.getShoppable().getPrice() * item.getQuantity()).sum();

		model.addAttribute("totalContracts", totalContracts);
		model.addAttribute("totalProducts", totalProducts);
		model.addAttribute("totalWorkshops", totalWorkshops);

		return "amap/front/shopping-cart";
	}

	@PostMapping("/{cartId}/add")
	public String addItem(@PathVariable("cartId") Long cartId, @PathVariable("tenancyAlias") String alias,
			@RequestParam("shoppableId") Long shoppableId, @RequestParam("shoppableType") String shoppableType,
			@RequestParam("quantity") int quantity) {
		shoppingCartService.addItemToCart(cartId, shoppableId, shoppableType, quantity);
		return "redirect:/{tenancyAlias}/cart/" + cartId;
	}

	@PostMapping("/{cartId}/updateQuantity/{itemId}")
	public String updateItemQuantity(@PathVariable("cartId") Long cartId, @PathVariable("tenancyAlias") String alias,
			@PathVariable("itemId") Long itemId, @ModelAttribute("action") String action) {
		if ("increase".equals(action)) {
			shoppingCartService.increaseItemQuantity(cartId, itemId);
		} else if ("decrease".equals(action)) {
			shoppingCartService.decreaseItemQuantity(cartId, itemId);
		}
		return "redirect:/{tenancyAlias}/cart/" + cartId;
	}

	@GetMapping("/{cartId}/init")
	public String initializeCart(@PathVariable("cartId") Long cartId, @PathVariable("tenancyAlias") String alias) {
		shoppingCartService.initShoppingCart();
		return "redirect:/{tenancyAlias}/cart/{cartId}";
	}
	
	public void addGraphismAttributes(String alias, Model model) {
		// get map style depending on tenancy
		model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(alias));
		model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(alias));
		model.addAttribute("latitude", graphismService.getLatitudeByTenancyAlias(alias));
		model.addAttribute("longitude", graphismService.getLongitudeByTenancyAlias(alias));
		// get tenancy info for header footer
		model.addAttribute("tenancy", graphismService.getTenancyByAlias(alias));
		// get color palette
		model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(alias));
		// get font choice
		model.addAttribute("font", graphismService.getFontByTenancyAlias(alias));
	}
	
	public Long getUserIdFromContext() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		//condition for when we don't want to login at each dev iteration
		if (principal instanceof CustomUserDetails) {
			CustomUserDetails loggedUserInfo = (CustomUserDetails) principal;
			return (Long) loggedUserInfo.getAdditionalInfoByKey("userId");
		} else return 1L;
	}

}
