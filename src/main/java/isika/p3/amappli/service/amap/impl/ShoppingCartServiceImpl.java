package isika.p3.amappli.service.amap.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isika.p3.amappli.entities.contract.Contract;
import isika.p3.amappli.entities.order.ProductMock;
import isika.p3.amappli.entities.order.Shoppable;
import isika.p3.amappli.entities.order.ShoppingCart;
import isika.p3.amappli.entities.order.ShoppingCartItem;
import isika.p3.amappli.repo.amap.ContractRepository;
import isika.p3.amappli.repo.amap.ProductMockRepository;
import isika.p3.amappli.repo.amap.ProductRepository;
import isika.p3.amappli.repo.amap.ShoppingCartItemRepository;
import isika.p3.amappli.repo.amap.ShoppingCartRepository;
import isika.p3.amappli.repo.amap.WorkshopRepository;
import isika.p3.amappli.service.amap.ShoppingCartService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepo;
	@Autowired
	private ShoppingCartItemRepository itemsRepo;
	@Autowired
	private ContractRepository contractRepo;
	@Autowired
	private ProductMockRepository productMockRepo;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private WorkshopRepository workshopRepository;
	
	
	// get shopping cart by id or create new 
//    public ShoppingCart getOrCreateCart(Long cartId) {
//        return shoppingCartRepo.findById(cartId).orElse(new ShoppingCart());
//    }
	public ShoppingCart getOrCreateCart(Long cartId) {
	    return shoppingCartRepo.findById(cartId).orElseGet(() -> {
	        ShoppingCart newCart = new ShoppingCart();
	        shoppingCartRepo.save(newCart);
	        return newCart;
	    });
	}

    
    @Transactional
    public ShoppingCart getShoppingCartById(Long id) {
        ShoppingCart cart = shoppingCartRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Shopping Cart not found"));
        // force initialization of items
        cart.getShoppingCartItems().size();
        return cart;
    }
    
//        ProductMock product = productMockRepo.findById(shoppableId)
//                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + shoppableId));
//
//        // check if stock is enough
//        if (product.getStock() < quantity) {
//            throw new RuntimeException("Insufficient stock for product: " + product.getName());
//        }
    public ShoppingCart addItemToCart(Long cartId, Long shoppableId, String shoppableType, int quantity) {
        ShoppingCart cart = getOrCreateCart(cartId);

        Shoppable shoppable;
        if ("CONTRACT".equalsIgnoreCase(shoppableType)) {
            shoppable = contractRepo.findById(shoppableId)
                    .orElseThrow(() -> new RuntimeException("Contract not found with ID: " + shoppableId));
        } else if ("PRODUCT".equalsIgnoreCase(shoppableType)) {
            shoppable = productRepository.findById(shoppableId)
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + shoppableId));
        } else  if ("WORKSHOP".equalsIgnoreCase(shoppableType)) {
        	shoppable = workshopRepository.findById(shoppableId)
        			.orElseThrow(() -> new RuntimeException("Workshop not found with ID: " + shoppableId));
        } else {
        	throw new IllegalArgumentException("Invalid shoppable type: " + shoppableType);
        }
        // Vérifiez si le stock est suffisant
        if (shoppable.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock for " + shoppable.getInfo());
        }

        // Vérifiez si l'article est déjà dans le panier
        ShoppingCartItem existingItem = cart.getShoppingCartItems().stream()
                .filter(item -> item.getShoppable().getId().equals(shoppableId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            // Si l'article est déjà dans le panier, mettez à jour la quantité
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            // Sinon, ajoutez un nouvel article
            ShoppingCartItem newItem = ShoppingCartItem.builder()
                    .shoppingCart(cart)
                    .shoppable(shoppable)
                    .quantity(quantity)
                    .build();
            cart.getShoppingCartItems().add(newItem);
            itemsRepo.save(newItem);
        }

        return shoppingCartRepo.save(cart);
    }

    
    public void increaseItemQuantity(Long cartId, Long itemId) {
        ShoppingCart cart = getShoppingCartById(cartId);
        cart.getShoppingCartItems().stream()
            .filter(item -> item.getShoppingItemId().equals(itemId))
            .findFirst()
            .ifPresent(item -> item.setQuantity(item.getQuantity() + 1));
        shoppingCartRepo.save(cart);
    }

    public void decreaseItemQuantity(Long cartId, Long itemId) {
        ShoppingCart cart = getShoppingCartById(cartId);
        cart.getShoppingCartItems().stream()
            .filter(item -> item.getShoppingItemId().equals(itemId))
            .findFirst()
            .ifPresent(item -> {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                } else {
                    // delete item if quantity gets to 0
                    cart.getShoppingCartItems().remove(item);
                }
            });
        shoppingCartRepo.save(cart);
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
        shoppingCartRepo.save(cart);

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
        
    }
	
}
