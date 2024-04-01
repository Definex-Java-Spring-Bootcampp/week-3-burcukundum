package onlineShoppingSystem.invoiceService;

import onlineShoppingSystem.orderService.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class InvoiceKafkaConsumer {

    @KafkaListener(topics = "invoice-topic", groupId = "invoice-group")
    public void consumeMessage(Order order) {
        // Generate invoice for the received order asynchronously
        // You can call the invoice generation logic here
    }
}