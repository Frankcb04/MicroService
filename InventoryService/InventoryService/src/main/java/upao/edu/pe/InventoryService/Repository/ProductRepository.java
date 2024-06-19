package upao.edu.pe.InventoryService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upao.edu.pe.InventoryService.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

