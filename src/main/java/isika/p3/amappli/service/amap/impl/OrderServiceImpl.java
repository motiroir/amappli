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
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.repo.amap.OrderRepository;
import isika.p3.amappli.repo.amap.ShoppingCartRepository;
import isika.p3.amappli.repo.amap.UserRepository;
import isika.p3.amappli.repo.amappli.TenancyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl {

	@PersistenceContext
	private EntityManager entityManager;

	private final OrderRepository orderRepo;
	private final ShoppingCartRepository cartRepo;
	private final UserRepository userRepo;
	private final TenancyRepository tenancyRepo;

	public OrderServiceImpl(EntityManager entityManager, OrderRepository orderRepo, ShoppingCartRepository cartRepo,
			UserRepository userRepo, TenancyRepository tenancyRepo) {
		this.entityManager = entityManager;
		this.orderRepo = orderRepo;
		this.cartRepo = cartRepo;
		this.userRepo = userRepo;
		this.tenancyRepo = tenancyRepo;
	}

    public ShoppingCart getCartByUserId(Long userId) {
        ShoppingCart cart = cartRepo.findByUserId(userId);
        if (cart == null) {
        	ShoppingCart newCart =new ShoppingCart();
        	newCart.setUser(userRepo.findById(userId).orElse(null));
        	cartRepo.save(newCart);
        	return newCart;
        } return cart;
    }
    
    @Transactional
    public List<Order> getOrdersByTenancyAlias(String alias){
    	Tenancy tenancy = tenancyRepo.findByTenancyAlias(alias).orElse(null);
    	List<Order> orders = orderRepo.findOrdersByTenancyId(tenancy.getTenancyId());
    	for (Order order : orders) {
			orderRepo.findOrderWithPayments(order.getOrderId());
			orderRepo.findOrderWithItems(order.getOrderId());
		}
    	return orders;
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
	
	@Transactional
	public Order validatePayment(Long orderId, String paymentTypeString) {
		//transform to PaymentType enum
	    PaymentType paymentType;
	    switch (paymentTypeString) {
	        case "Carte bleue":
	            paymentType = PaymentType.card;
	            break;
	        case "Chèque":
	            paymentType = PaymentType.check;
	            break;
	        case "Espèces":
	            paymentType = PaymentType.cash;
	            break;
	        default:
	            throw new IllegalArgumentException("Type de paiement invalide : " + paymentTypeString);
	    }

	    Order order = orderRepo.findById(orderId)
	        .orElseThrow(() -> new EntityNotFoundException("Commande introuvable pour l'ID : " + orderId));

	    order.setOrderStatus(OrderStatus.DONE);
		Payment payment = Payment.builder().paymentType(paymentType).paymentDate(LocalDateTime.now())
				.paymentAmount(BigDecimal.valueOf(order.getTotalAmount())).build();

		order.setOrderPaid(true);
		order.getPayments().add(payment);
		payment.setOrder(order);
		orderRepo.save(order);
		return order;
	}

	public List<Order> getListOrdersByUser(Long userId) {
		User user = userRepo.findUserWithOrders(userId);
		for (Order order : user.getOrders()) {
			orderRepo.findOrderWithPayments(order.getOrderId());
			orderRepo.findOrderWithItems(order.getOrderId());
		}
		return user.getOrders();
	}
	
	@Transactional
	public Order getOrderById(Long orderId) {
		orderRepo.findOrderWithPayments(orderId);
		return orderRepo.findOrderWithItemsAndShoppable(orderId);
	}
	
}
