package isika.p3.amappli.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isika.p3.amappli.entities.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
