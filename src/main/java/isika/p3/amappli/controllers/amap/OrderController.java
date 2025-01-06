package isika.p3.amappli.controllers.amap;

import java.util.List;

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

import isika.p3.amappli.entities.order.Order;
import isika.p3.amappli.entities.order.ShoppingCart;
import isika.p3.amappli.security.CustomUserDetails;
import isika.p3.amappli.service.amap.GraphismService;
import isika.p3.amappli.service.amap.impl.OrderServiceImpl;

@Controller
@RequestMapping("amap/{tenancyAlias}")
public class OrderController {
	
	@Autowired
	private OrderServiceImpl orderService;
	@Autowired
	private GraphismService graphismService;
	
	@GetMapping("/account/my-orders/{userId}")
	public String viewOrderByUser(@PathVariable("userId") Long userId, @PathVariable("tenancyAlias") String alias, Model model) {
		userId = getUserIdFromContext();
		
		List<Order> orderByUser = orderService.getListOrdersByUser(userId);
		model.addAttribute("orders", orderByUser);
		
		addGraphismAttributes(alias, model);
        return "amap/front/user-profile/my-orders";
	}

	@GetMapping("/admin/orders")
	public String viewOrdersForAdmin(@PathVariable("tenancyAlias") String alias, Model model) {
		List<Order> orders = orderService.getOrdersByTenancyAlias(alias);
		model.addAttribute("orders", orders);
		
		addGraphismAttributes(alias, model);
		return "amap/back/orders/orders-list";
	}
	
	@GetMapping("/account/my-orders/order-details/{orderId}")
	public String viewOrderDetails(@PathVariable("tenancyAlias") String alias, @PathVariable("orderId") Long orderId, Model model) {
		Long userId = getUserIdFromContext();
		model.addAttribute("userId", userId);
		
		Order order = orderService.getOrderById(orderId);
		model.addAttribute("order", order);
		
		addGraphismAttributes(alias, model);
		return "amap/front/user-profile/order-details";
	}
	
	@GetMapping("/admin/order-details/{orderId}")
	public String viewOrderDetailsForAdmin(@PathVariable("tenancyAlias") String alias, @PathVariable("orderId") Long orderId, Model model) {
		Long userId = getUserIdFromContext();
		model.addAttribute("userId", userId);
		
		Order order = orderService.getOrderById(orderId);
		model.addAttribute("order", order);
		
		addGraphismAttributes(alias, model);
		return "amap/back/orders/order-details";
	}
	
    
    @PostMapping("order/{userId}/createOrder")
    public String createOrder(
    		@PathVariable("userId") Long userId,  @PathVariable("tenancyAlias") String alias, 
            @ModelAttribute("action") String action) {
    	userId = getUserIdFromContext();
    	ShoppingCart cart = orderService.getCartByUserId(userId);
    	
    	if ("OrderWithPayment".equals(action)) {
    		orderService.createOrderFromCartWithOnlinePayment(cart.getShoppingCartId());
    	} else if ("OrderWithoutPayment".equals(action)) {
    		orderService.createOrderFromCartWithOnsitePayment(cart.getShoppingCartId());
    	}
    	return "redirect:/{tenancyAlias}/account/my-orders/" + userId;
    }
    
    @PostMapping("order/updateOrder")
    public String updateOrder(@PathVariable("tenancyAlias") String alias, @RequestParam("orderId") Long orderId, 
            @RequestParam("paymentType") String paymentType){
    	orderService.validatePayment(orderId, paymentType);
    	return "redirect:/amap/{tenancyAlias}/admin/order-details/" + orderId;
    }
    
	public void addGraphismAttributes(String alias, Model model) {
		// get map style and coordinates depending on tenancy
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
