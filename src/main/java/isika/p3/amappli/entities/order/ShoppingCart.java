package isika.p3.amappli.entities.order;

import java.util.List;
import java.util.ArrayList;

import isika.p3.amappli.entities.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
public class ShoppingCart {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
	private Long shoppingCartId;
	
//	@Getter @Setter
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "userId")
//	private User user;
	
	@Getter @Setter
	@Builder.Default
	//@OneToMany(targetEntity = ShoppingCartItem.class, mappedBy="shoppingCart", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OneToMany(mappedBy = "shoppingCart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>();
	
}
