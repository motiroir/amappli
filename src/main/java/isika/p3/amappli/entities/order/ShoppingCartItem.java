package isika.p3.amappli.entities.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartItem {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
	private Long shoppingItemId;
	
//	@Getter @Setter
//	private ShoppingCart shoppingCart;
	
	@Getter @Setter
	@ManyToOne
	private Shoppable shoppable;
	
	@Getter @Setter
	private int quantity;

//	public double getTotalPrice() {
//		return shoppable.getPrice() * quantity;
//	}

}
