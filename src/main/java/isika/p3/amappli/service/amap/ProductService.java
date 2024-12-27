package isika.p3.amappli.service.amap;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import isika.p3.amappli.dto.amap.ProductDTO;
import isika.p3.amappli.entities.product.Product;

public interface ProductService {

    void save(ProductDTO productDTO);

    List<Product> findAll();

    void deleteById(Long id);

    Product findById(Long id);
    
    void updateProduct(ProductDTO updatedProductDTO, MultipartFile image);
}
