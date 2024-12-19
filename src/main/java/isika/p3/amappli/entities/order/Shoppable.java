package isika.p3.amappli.entities.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public abstract class Shoppable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
	
	public abstract int getStock();
	public abstract double getPrice();
	public abstract String getInfo();
	public abstract String getImage();
	
}
