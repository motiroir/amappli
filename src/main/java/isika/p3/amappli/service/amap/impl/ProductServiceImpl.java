package isika.p3.amappli.service.amap.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import isika.p3.amappli.dto.amap.ProductDTO;
import isika.p3.amappli.entities.contract.DeliveryDay;
import isika.p3.amappli.entities.contract.DeliveryRecurrence;
import isika.p3.amappli.entities.product.Product;
import isika.p3.amappli.repo.amap.ProductRepository;
import isika.p3.amappli.service.amap.ProductService;

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

	@Override
	public void updateProduct(ProductDTO updatedProductDTO, MultipartFile image) {

		Product existingProduct = productRepository.findById(updatedProductDTO.getId()).orElse(null);
	
		if (existingProduct == null) {
	        throw new IllegalArgumentException("Le produit avec l'ID " + updatedProductDTO.getId() + " n'existe pas.");
	    }

	    // Mise à jour des champs non liés à l'image
	    existingProduct.setProductName(updatedProductDTO.getProductName() != null ? updatedProductDTO.getProductName() : existingProduct.getProductName());
	    existingProduct.setProductDescription(updatedProductDTO.getProductDescription() != null ? updatedProductDTO.getProductDescription() : existingProduct.getProductDescription());
	    existingProduct.setProductPrice(updatedProductDTO.getProductPrice() != null ? updatedProductDTO.getProductPrice() : existingProduct.getProductPrice());
	    existingProduct.setProductStock(updatedProductDTO.getProductStock() != null ? updatedProductDTO.getProductStock() : existingProduct.getProductStock());
	    existingProduct.setFabricationDate(updatedProductDTO.getFabricationDate() != null ? updatedProductDTO.getFabricationDate() : existingProduct.getFabricationDate());
	    existingProduct.setExpirationDate(updatedProductDTO.getExpirationDate() != null ? updatedProductDTO.getExpirationDate() : existingProduct.getExpirationDate());
	    existingProduct.setDeliveryDay(updatedProductDTO.getDeliveryDay() != null ? DeliveryDay.valueOf(updatedProductDTO.getDeliveryDay()) : existingProduct.getDeliveryDay());
	    existingProduct.setDeliveryRecurrence(updatedProductDTO.getDeliveryRecurrence() != null ? DeliveryRecurrence.valueOf(updatedProductDTO.getDeliveryRecurrence()) : existingProduct.getDeliveryRecurrence());

	    // Gestion de l'image
	    if (image != null && !image.isEmpty()) {
	        try {
	            existingProduct.setImageType(image.getContentType());
	            byte[] imageBytes = image.getBytes();
	            existingProduct.setImageData(Base64.getEncoder().encodeToString(imageBytes));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    // Sauvegarde du produit mis à jour
	    productRepository.save(existingProduct);
	
	}
}
