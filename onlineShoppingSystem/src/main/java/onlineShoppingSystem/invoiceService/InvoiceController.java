package onlineShoppingSystem.invoiceService;

import onlineShoppingSystem.orderService.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/invoices")
    public Invoice generateInvoice(@RequestBody Order order) {
        return invoiceService.generateInvoice(order);
    }

    @GetMapping("/invoices/{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable Long id) throws InvoiceNotFoundException {
        Invoice invoice = invoiceService.getInvoiceById(id);
        if (invoice == null) {
            throw new InvoiceNotFoundException("Invoice not found with id: " + id);
        }
        return ResponseEntity.ok(invoice);
    }

}