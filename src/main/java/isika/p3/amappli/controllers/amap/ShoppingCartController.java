package isika.p3.amappli.controllers.amap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import isika.p3.amappli.entities.order.ShoppingCart;
import isika.p3.amappli.service.amap.GraphismService;
import isika.p3.amappli.service.amap.impl.ShoppingCartServiceImpl;

@Controller
@RequestMapping("/{tenancyId}/cart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartServiceImpl shoppingCartService;
	@Autowired
	private GraphismService graphismService;
	
    @GetMapping("/{cartId}")
    public String viewCart(@PathVariable("cartId") Long cartId, @PathVariable("tenancyId") Long tenancyId, Model model) {
        ShoppingCart cart = shoppingCartService.getShoppingCartById(cartId);
        model.addAttribute("cart", cart);
        model.addAttribute("total", shoppingCartService.calculateTotal(cartId));
        
        //get map style depending on tenancy
        model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyId(tenancyId));
        model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyId(tenancyId));
        //get tenancy info for header footer
        model.addAttribute("tenancy", graphismService.getTenancyById(tenancyId));
        //get color palette
        model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyId(tenancyId));
        //get font choice
        model.addAttribute("font", graphismService.getFontByTenancyId(tenancyId));
        
        return "amap/shopping-cart";
    }

    @PostMapping("/{cartId}/add")
    public String addItem(@PathVariable("cartId") Long cartId, @PathVariable("tenancyId") Long tenancyId,
                          @RequestParam("shoppableId") Long shoppableId,
                          @RequestParam("quantity") int quantity) {
    	System.out.println("Received cartId: " + cartId + ", shoppableId: " + shoppableId + ", quantity: " + quantity);
        shoppingCartService.addItemToCart(cartId, shoppableId, quantity);
        return "redirect:/{tenancyId}/cart/" + cartId;
    }

    @PostMapping("/{cartId}/updateQuantity/{itemId}")
    public String updateItemQuantity(
            @PathVariable("cartId") Long cartId,  @PathVariable("tenancyId") Long tenancyId,
            @PathVariable("itemId") Long itemId, 
            @ModelAttribute("action") String action) {
        if ("increase".equals(action)) {
            shoppingCartService.increaseItemQuantity(cartId, itemId);
        } else if ("decrease".equals(action)) {
            shoppingCartService.decreaseItemQuantity(cartId, itemId);
        }
        return "redirect:/{tenancyId}/cart/" + cartId;
    }
    
    @GetMapping("/{cartId}/init")
    public String initializeCart(@PathVariable("cartId") Long cartId, @PathVariable("tenancyId") Long tenancyId) {
    	shoppingCartService.initShoppingCart();
    	return "redirect:/{tenancyId}/cart/{cartId}";
    }
    
    
	
}
