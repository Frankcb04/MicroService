package upao.edu.pe.InventoryService.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upao.edu.pe.InventoryService.Entity.Product;
import upao.edu.pe.InventoryService.Repository.ProductRepository;

@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product createProduct(String name, Integer quantity) {
        Product product = new Product();
        product.setName(name);
        product.setQuantity(quantity);
        return productRepository.save(product);
    }

    public boolean checkAvailability(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Producto no encontrado."));
        return product.getQuantity() >= quantity;
    }

    public void updateInventory(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Producto no encontrado."));
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado."));
    }
}

