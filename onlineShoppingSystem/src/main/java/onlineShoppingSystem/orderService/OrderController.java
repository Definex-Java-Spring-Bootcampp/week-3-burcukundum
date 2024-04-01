package onlineShoppingSystem.orderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) throws OrderNotFoundException {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            throw new OrderNotFoundException("Order not found with id: " + id);
        }
        return ResponseEntity.ok(order);
    }
}