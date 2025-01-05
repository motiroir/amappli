package isika.p3.amappli.service.amap.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.type.descriptor.java.LocalDateTimeJavaType;
import org.springframework.stereotype.Service;

import isika.p3.amappli.entities.order.Order;
import isika.p3.amappli.entities.order.OrderItem;
import isika.p3.amappli.entities.order.OrderStatus;
import isika.p3.amappli.entities.order.Shoppable;
import isika.p3.amappli.entities.order.ShoppingCart;
import isika.p3.amappli.entities.order.ShoppingCartItem;
import isika.p3.amappli.entities.payment.Payment;
import isika.p3.amappli.entities.payment.PaymentType;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.repo.amap.OrderRepository;
import isika.p3.amappli.repo.amap.ShoppingCartRepository;
import isika.p3.amappli.repo.amap.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl {

	@PersistenceContext
	private EntityManager entityManager;

	private final OrderRepository orderRepo;
	private final ShoppingCartRepository cartRepo;
	private final UserRepository userRepo;

	public OrderServiceImpl(EntityManager entityManager, OrderRepository orderRepo, ShoppingCartRepository cartRepo,
			UserRepository userRepo) {
		this.entityManager = entityManager;
		this.orderRepo = orderRepo;
		this.cartRepo = cartRepo;
		this.userRepo = userRepo;
	}

	public List<OrderItem> copyCartItemsListToOrderItemsList(List<ShoppingCartItem> cartItems) {
		return cartItems.stream().map(cartItem -> {
			// deals with issue on copying the shoppable attached to cartItem to get to
			// attach to the orderItem
			Shoppable attachedShoppable = entityManager.merge(cartItem.getShoppable());
			// builds all orderItems form cartItems
			return OrderItem.builder().quantity(cartItem.getQuantity()).unitPrice(attachedShoppable.getPrice())
					.total(cartItem.getQuantity() * attachedShoppable.getPrice()).shoppable(attachedShoppable).build();
		}).collect(Collectors.toList());
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
		List<OrderItem> orderItems = copyCartItemsListToOrderItemsList(cart.getShoppingCartItems());

		Double totalAmountFromItems = orderItems.stream().mapToDouble(OrderItem::getTotal).sum();
		// create order
		Order order = Order.builder().orderItems(new ArrayList<OrderItem>()).user(cart.getUser())
				.totalAmount(totalAmountFromItems).installmentCount(1).installmentAmount(totalAmountFromItems / 1)
				.orderDate(LocalDate.now()).orderStatus(OrderStatus.PENDING).build();

		// attache order to items and vice-versa
		for (OrderItem item : orderItems) {
			item.setOrder(order);
			order.getOrderItems().add(item);
		}

		// empty shoppingCart and save
		cart.getShoppingCartItems().clear();
		cartRepo.save(cart);
		return order;
	}

	@Transactional
	public Order createOrderFromCartWithOnsitePayment(Long cartId) {
		Order order = createOrderFromCart(cartId);
		// save order and order items by cascade
		orderRepo.save(order);

		return order;
	}

	@Transactional
	public Order createOrderFromCartWithOnlinePayment(Long cartId) {
		Order order = createOrderFromCart(cartId);
		Payment payment = Payment.builder().paymentType(PaymentType.card).paymentDate(LocalDateTime.now())
				.paymentAmount(BigDecimal.valueOf(order.getTotalAmount())).build();

		order.setOrderPaid(true);
		order.getPayments().add(payment);
		payment.setOrder(order);
		// save order and order items and payment by cascade
		orderRepo.save(order);
		return order;
	}

	public List<Order> getListOrdersByUser(Long userId) {
		User user = userRepo.findUserWithOrders(userId);
		for (Order order : user.getOrders()) {
			orderRepo.findOrderWithItems(order.getOrderId());
			orderRepo.findOrderWithPayments(order.getOrderId());
		}
		return user.getOrders();
	}
	
	public Order getOrderById(Long orderId) {
		return orderRepo.findOrderWithItemsAndShoppable(orderId);
	}

}
