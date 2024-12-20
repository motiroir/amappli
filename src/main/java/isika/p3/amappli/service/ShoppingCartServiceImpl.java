package isika.p3.amappli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isika.p3.amappli.entities.order.ProductMock;
import isika.p3.amappli.entities.order.ShoppingCart;
import isika.p3.amappli.entities.order.ShoppingCartItem;
import isika.p3.amappli.repo.ProductMockRepository;
import isika.p3.amappli.repo.ShoppingCartItemRepository;
import isika.p3.amappli.repo.ShoppingCartRepository;

@Service
public class ShoppingCartServiceImpl {

	@Autowired
	private ShoppingCartRepository shoppingCartRepo;
	@Autowired
	private ShoppingCartItemRepository itemsRepo;
	@Autowired
	private ProductMockRepository productMockRepo;
	
	
	// get shopping cart by id or create new 
    public ShoppingCart getOrCreateCart(Long cartId) {
        return shoppingCartRepo.findById(cartId).orElse(new ShoppingCart());
    }
    
    public ShoppingCart addItemToCart(Long cartId, ShoppingCartItem item) {
        ShoppingCart cart = getOrCreateCart(cartId);
        item.setShoppingCart(cart);
        cart.getShoppingCartItems().add(item);
        itemsRepo.save(item); 
        return shoppingCartRepo.save(cart); 
    }    
    
    public ShoppingCart removeItemFromCart(Long cartId, Long itemId) {
        ShoppingCart cart = getOrCreateCart(cartId);
        cart.getShoppingCartItems().removeIf(item -> item.getShoppingItemId().equals(itemId));
        itemsRepo.deleteById(itemId); 
        return shoppingCartRepo.save(cart); 
    }   
    
    public double calculateTotal(Long cartId) {
        ShoppingCart cart = getOrCreateCart(cartId);
        return cart.getShoppingCartItems().stream()
            .mapToDouble(ShoppingCartItem::getTotalPrice)
            .sum();
    }    
    
    public void initShoppingCart() {
    	ProductMock product1 = ProductMock.builder()
    			.name("Carottes Bio")
    			.price(1.5)
    			.stock(40)
    			.image("img1")
    			.build();
        ProductMock product2 = ProductMock.builder()
        		.name("Tomates anciennes")
        		.price(3.0)
        		.stock(20)
    			.image("img2")
        		.build();
        ProductMock product3 = ProductMock.builder()
        		.name("Courgettes")
        		.price(2.0)
        		.stock(60)
    			.image("img3")
        		.build();
        productMockRepo.save(product1);
        productMockRepo.save(product2);
        productMockRepo.save(product3);

        ShoppingCart cart = ShoppingCart.builder().build();

        ShoppingCartItem item1 = ShoppingCartItem.builder()
                .shoppingCart(cart)
                .shoppable(product1)
                .quantity(3)
                .build();
        ShoppingCartItem item2 = ShoppingCartItem.builder()
                .shoppingCart(cart)
                .shoppable(product2)
                .quantity(2)
                .build();
        ShoppingCartItem item3 = ShoppingCartItem.builder()
                .shoppingCart(cart)
                .shoppable(product3)
                .quantity(1)
                .build();
        itemsRepo.save(item1);
        itemsRepo.save(item2);
        itemsRepo.save(item3);
        
        cart.getShoppingCartItems().add(item2);
        cart.getShoppingCartItems().add(item3);
        shoppingCartRepo.save(cart);
    }
	
}
