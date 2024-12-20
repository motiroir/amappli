package isika.p3.amappli.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import isika.p3.amappli.entities.order.ShoppingCart;
import isika.p3.amappli.entities.order.ShoppingCartItem;
import isika.p3.amappli.service.ShoppingCartServiceImpl;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartServiceImpl shoppingCartService;
	
    @GetMapping("/{cartId}")
    public String viewCart(@PathVariable("cartId") Long cartId, Model model) {
        ShoppingCart cart = shoppingCartService.getOrCreateCart(cartId);
        model.addAttribute("cart", cart);
        model.addAttribute("total", shoppingCartService.calculateTotal(cartId));
        return "amap/shopping-cart";
    }

    @PostMapping("/{cartId}/add")
    public String addItem(@PathVariable("cartId") Long cartId, @ModelAttribute ShoppingCartItem item) {
        shoppingCartService.addItemToCart(cartId, item);
        return "redirect:/cart/" + cartId;
    }

    @PostMapping("/{cartId}/remove/{itemId}")
    public String removeItem(@PathVariable("cartId") Long cartId, @PathVariable Long itemId) {
        shoppingCartService.removeItemFromCart(cartId, itemId);
        return "redirect:/cart/" + cartId;
    }
    
    
    @GetMapping("/init")
    public String initializeCart() {
    	shoppingCartService.initShoppingCart();
    	return "redirect:/cart";
    }
    
    
	
}
