package onlineShoppingSystem.invoiceService;

import onlineShoppingSystem.orderService.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private KafkaTemplate<String, Invoice> kafkaTemplate;

    private static final String TOPIC_NAME = "invoice_topic";

    public Invoice generateInvoice(Order order) {
        // Calculate amount based on order details
        BigDecimal amount = calculateAmount(order);

        // Create invoice
        Invoice invoice = new Invoice();
        invoice.setOrderId(order.getId());
        invoice.setAmount(amount);

        // Save invoice to repository
        invoice = invoiceRepository.save(invoice);

        // Publish invoice event to Kafka topic for asynchronous processing
        kafkaTemplate.send(TOPIC_NAME, invoice);

        return invoice;
    }

    private BigDecimal calculateAmount(Order order) {
        // Calculate amount based on order details
        return BigDecimal.TEN; // Placeholder
    }

    public Invoice getInvoiceById(Long id) {
        return null;
    }

    public void generateInvoiceAsync(Order order) {
    }
}
