package isika.p3.amappli.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;

import isika.p3.amappli.dto.ProductDTO;
import isika.p3.amappli.entities.contract.DeliveryDay;
import isika.p3.amappli.entities.contract.DeliveryRecurrence;
import isika.p3.amappli.entities.product.Product;
import isika.p3.amappli.repo.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ProductDTO productDTO) {
        Product product = new Product();

        product.setProductName(productDTO.getProductName());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductStock(productDTO.getProductStock());
        product.setFabricationDate(productDTO.getFabricationDate());
        product.setExpirationDate(productDTO.getExpirationDate());
        product.setDateCreation(productDTO.getDateCreation());
        product.setDateCreation(LocalDate.now());
        product.setProductDescription(productDTO.getProductDescription());
        product.setDeliveryRecurrence(DeliveryRecurrence.valueOf(productDTO.getDeliveryRecurrence()));
        product.setDeliveryDay(DeliveryDay.valueOf(productDTO.getDeliveryDay()));

        // Traitement de l'image
        if (!productDTO.getImage().isEmpty()) {
            if (productDTO.getImage().getSize() > 20971520) { // 20 MB max
                throw new IllegalArgumentException("La taille de l'image dépasse la limite de 20MB.");
            }
            try {
                product.setImageType(productDTO.getImage().getContentType());
                byte[] imageBytes = productDTO.getImage().getBytes();
                product.setImageData(Base64.getEncoder().encodeToString(imageBytes));
            } catch (IOException e) {
                throw new RuntimeException("Erreur lors du traitement de l'image", e);
            }
        }


        productRepository.save(product);
    }
}
