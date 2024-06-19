package upao.edu.pe.OrderService.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "InventoryService", url = "${InventoryService.url}")
public interface InventoryClient {

    @GetMapping("/inventory/{productId}/availability")
    boolean checkAvailability(@PathVariable("productId") Long productId, @RequestParam Integer quantity);

    @PutMapping("/inventory/{productId}")
    void updateInventory(@PathVariable("productId") Long productId, @RequestParam("quantity") Integer quantity);
}
