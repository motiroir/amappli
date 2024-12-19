//package isika.p3.amappli.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import isika.p3.amappli.entities.order.ShoppingCart;
//import isika.p3.amappli.repo.ShoppingCartItemRepository;
//import isika.p3.amappli.repo.ShoppingCartRepository;
//
//@Service
//public class ShoppingCartServiceImpl {
//
//	@Autowired
//	private ShoppingCartRepository shoppingCartRepo;
//	@Autowired
//	private ShoppingCartItemRepository itemsRepo;
//	
//	public ShoppingCart save(ShoppingCart shoppingCart) {
//		return shoppingCartRepo.save(shoppingCart);
//	}
//	
//	public ShoppingCart getShoppingCartById(Long id) {
//		return shoppingCartRepo.findById(id).orElse(null);
//	}
//	
//	public void addItemsToShoppingCart() {
//	}
//	
//	public void removeItemsFromShoppingCart() {
//	}
//	
//}
