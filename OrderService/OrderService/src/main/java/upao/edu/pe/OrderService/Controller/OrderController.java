package upao.edu.pe.OrderService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upao.edu.pe.OrderService.Entity.Orders;
import upao.edu.pe.OrderService.Request.OrderRequest;
import upao.edu.pe.OrderService.Service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<Orders> createOrder(@RequestBody OrderRequest orderRequest) {
        Orders orders = orderService.createOrder(orderRequest);
        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long orderId) {
        Orders orders = orderService.getOrderById(orderId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
