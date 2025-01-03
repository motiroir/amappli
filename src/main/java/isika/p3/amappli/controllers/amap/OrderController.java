package isika.p3.amappli.controllers.amap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import isika.p3.amappli.entities.order.Order;
import isika.p3.amappli.service.amap.GraphismService;
import isika.p3.amappli.service.amap.impl.OrderServiceImpl;

@Controller
@RequestMapping("/{tenancyAlias}/order")
public class OrderController {
	
	@Autowired
	private OrderServiceImpl orderService;
	@Autowired
	private GraphismService graphismService;
	
	@GetMapping("/{userId}")
	public String viewOrderByUser(@PathVariable("userId") Long userId, @PathVariable("tenancyAlias") String alias, Model model) {
		List<Order> orderByUser = orderService.getListOrdersByUser(userId);
		model.addAttribute("orders", orderByUser);
		
		 //get map style depending on tenancy
        model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(alias));
        model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(alias));
        //get tenancy info for header footer
        model.addAttribute("tenancy", graphismService.getTenancyByAlias(alias));
        //get color palette
        model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(alias));
        //get font choice
        model.addAttribute("font", graphismService.getFontByTenancyAlias(alias));
        return "amap/front/user-profile/my-orders";
	}
	
    
    @PostMapping("/{cartId}/createOrder")
    public String createOrder(
    		@PathVariable("cartId") Long cartId,  @PathVariable("tenancyAlias") String alias, 
            @ModelAttribute("action") String action) {
    	if ("OrderWithPayment".equals(action)) {
    		orderService.createOrderFromCartWithOnlinePayment(cartId);
    	} else if ("OrderWithoutPayment".equals(action)) {
    		orderService.createOrderFromCartWithOnsitePayment(cartId);
    	}
    	return "redirect:/{tenancyAlias}/order/" + 6;
    }

}
