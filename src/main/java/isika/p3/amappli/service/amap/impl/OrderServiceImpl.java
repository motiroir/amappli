package isika.p3.amappli.service.amap.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import isika.p3.amappli.entities.order.Order;
import isika.p3.amappli.entities.order.OrderItem;
import isika.p3.amappli.entities.order.ShoppingCart;
import isika.p3.amappli.repo.amap.OrderItemRepository;
import isika.p3.amappli.repo.amap.OrderRepository;
import isika.p3.amappli.repo.amap.ShoppingCartRepository;
import isika.p3.amappli.service.amap.ShoppingCartService;

@Service
public class OrderServiceImpl {

	private final ShoppingCartService cartService;
	private final OrderRepository orderRepo;
	private final OrderItemRepository orderItemRepo;
	private final ShoppingCartRepository cartRepo;

	public OrderServiceImpl(ShoppingCartService cartService, OrderRepository orderRepo,
			OrderItemRepository orderItemRepo, ShoppingCartRepository cartRepo) {
		super();
		this.cartService = cartService;
		this.orderRepo = orderRepo;
		this.orderItemRepo = orderItemRepo;
		this.cartRepo = cartRepo;
	}

	public Order createOrderFromCart(Long cartId) {
		// get cart by id, to change with user id
		ShoppingCart cart = cartRepo.findById(cartId)
				.orElseThrow(() -> new RuntimeException("Le panier est introuvable. "));
		if (cart == null || cart.getShoppingCartItems().isEmpty()) {
			throw new RuntimeException("Le panier est vide ou introuvable.");
		}

		// change cartItems into orderItems
		List<OrderItem> orderItems = cart.getShoppingCartItems().stream()
			    .map(cartItem -> {
			        if (cartItem.getShoppable() == null || cartItem.getShoppable().getId() == null) {
			            throw new RuntimeException("Le produit associé est null ou non persisté !");
			        }
			        return OrderItem.builder()
			            .quantity(cartItem.getQuantity())
			            .unitPrice(cartItem.getShoppable().getPrice())
			            .total(cartItem.getQuantity() * cartItem.getShoppable().getPrice())
			            .shoppable(cartItem.getShoppable())
			            .build();
			    })
				.collect(Collectors.toList());

		Double totalAmountFromItems = orderItems.stream().mapToDouble(OrderItem::getTotal).sum();
		// create and save order
		Order order = Order.builder()
				.orderItems(new ArrayList<OrderItem>())
				.user(cart.getUser())
				.totalAmount(totalAmountFromItems)
				.installmentCount(1)
				.installmentAmount(totalAmountFromItems)
				.build();

		for (OrderItem item : orderItems) {
	        item.setOrder(order);
	        order.getOrderItems().add(item);
	    }

		orderRepo.save(order);
		
		// empty shoppingCart
		cart.getShoppingCartItems().clear();
		cartRepo.save(cart);

		return order;
	}

}
