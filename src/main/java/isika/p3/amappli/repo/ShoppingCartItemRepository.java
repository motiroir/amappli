package isika.p3.amappli.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import isika.p3.amappli.entities.order.ShoppingCartItem;

@Repository
public interface ShoppingCartItemRepository extends CrudRepository<ShoppingCartItem, Long>{

}
