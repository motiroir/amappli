package isika.p3.amappli.repo.amap;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import isika.p3.amappli.entities.order.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{

	@Query("SELECT o FROM Order o JOIN FETCH o.payments WHERE o.orderId = :orderId")
	Order findOrderWithPayments(@Param("orderId") Long orderId);
	
	@Query("SELECT o FROM Order o JOIN FETCH o.orderItems JOIN FETCH o.payments WHERE o.orderId = :orderId")
	Order findOrderWithItemsAndPayments(@Param("orderId") Long orderId);
	
	@Query("SELECT o FROM Order o JOIN FETCH o.orderItems WHERE o.orderId = :orderId")
	Order findOrderWithItems(@Param("orderId") Long orderId);
}
