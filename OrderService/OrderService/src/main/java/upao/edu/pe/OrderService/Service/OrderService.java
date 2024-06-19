package upao.edu.pe.OrderService.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import upao.edu.pe.OrderService.Entity.Orders;
import upao.edu.pe.OrderService.Feign.InventoryClient;
import upao.edu.pe.OrderService.Repository.OrderRepository;
import upao.edu.pe.OrderService.Request.OrderRequest;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryClient inventoryClient;

    public Orders createOrder(OrderRequest orderRequest) {
        // Lógica para crear un nuevo pedido
        boolean isAvailable = inventoryClient.checkAvailability(orderRequest.getProductId(), orderRequest.getQuantity());
        if (isAvailable) {
            Orders orders = new Orders();
            orders.setProductId(orderRequest.getProductId());
            orders.setQuantity(orderRequest.getQuantity());
            orders.setStatus("CONFIRMED");
            inventoryClient.updateInventory(orderRequest.getProductId(), orderRequest.getQuantity());
            return orderRepository.save(orders);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Producto no disponible en el inventario.");
        }
    }

    public Orders getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado."));
    }
}
