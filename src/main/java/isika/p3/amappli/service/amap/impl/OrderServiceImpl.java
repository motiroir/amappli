package isika.p3.amappli.service.amap.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import isika.p3.amappli.entities.order.Order;
import isika.p3.amappli.entities.order.OrderItem;
import isika.p3.amappli.entities.order.Shoppable;
import isika.p3.amappli.entities.order.ShoppingCart;
import isika.p3.amappli.repo.amap.OrderRepository;
import isika.p3.amappli.repo.amap.ShoppingCartRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl {

	@PersistenceContext
	private EntityManager entityManager;
	
	private final OrderRepository orderRepo;
	private final ShoppingCartRepository cartRepo;

	public OrderServiceImpl(EntityManager entityManager, OrderRepository orderRepo, ShoppingCartRepository cartRepo) {
		this.entityManager = entityManager;
		this.orderRepo = orderRepo;
		this.cartRepo = cartRepo;
	}

	@Transactional
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
			        // deals with issue on copying the shoppable attached to cartItem to get to attach to the orderItem
			        Shoppable attachedShoppable = entityManager.merge(cartItem.getShoppable());
			        // builds all orderItems form cartItems
			        return OrderItem.builder()
			            .quantity(cartItem.getQuantity())
			            .unitPrice(attachedShoppable.getPrice())
			            .total(cartItem.getQuantity() * attachedShoppable.getPrice())
			            .shoppable(attachedShoppable)
			            .build();
			    })
				.collect(Collectors.toList());

		
		Double totalAmountFromItems = orderItems.stream().mapToDouble(OrderItem::getTotal).sum();
		// create order
		Order order = Order.builder()
				.orderItems(new ArrayList<OrderItem>())
				.user(cart.getUser())
				.totalAmount(totalAmountFromItems)
				.installmentCount(1)
				.installmentAmount(totalAmountFromItems/1)
				.build();

		// attache order to items and vice-versa
		for (OrderItem item : orderItems) {
	        item.setOrder(order);
	        order.getOrderItems().add(item);
	    }

		// save order and order items by cascade
		orderRepo.save(order);
		
		// empty shoppingCart and save
		cart.getShoppingCartItems().clear();
		cartRepo.save(cart);

		return order;
	}

}
