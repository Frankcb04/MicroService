package upao.edu.pe.InventoryService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upao.edu.pe.InventoryService.Entity.Product;
import upao.edu.pe.InventoryService.Service.InventoryService;

import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarProducto(@RequestBody Map<String, Object> payload) {
        String name = (String) payload.get("name");
        Integer quantity = (Integer) payload.get("quantity");
        Product productoAgregado = inventoryService.createProduct(name, quantity);
        return ResponseEntity.ok().body(productoAgregado);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = inventoryService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/{productId}/availability")
    public ResponseEntity<Boolean> checkAvailability(@PathVariable Long productId, @RequestParam Integer quantity) {
        boolean isAvailable = inventoryService.checkAvailability(productId, quantity);
        return new ResponseEntity<>(isAvailable, HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateInventory(@PathVariable Long productId, @RequestParam Integer quantity) {
        inventoryService.updateInventory(productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
