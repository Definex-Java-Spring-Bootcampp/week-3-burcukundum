package onlineShoppingSystem.orderService;

import onlineShoppingSystem.invoiceService.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InvoiceService invoiceService;

    public Order createOrder(Order order) {

        order.setStatus(OrderStatus.PENDING);

        return orderRepository.save(order);
    }

    public void placeOrder(Order order) {

        invoiceService.generateInvoiceAsync(order);
    }

    public Order getOrderById(Long id) {
        return null;
    }
}


