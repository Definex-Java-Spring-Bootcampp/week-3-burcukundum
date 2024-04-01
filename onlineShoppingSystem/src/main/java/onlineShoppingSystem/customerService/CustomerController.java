package onlineShoppingSystem.customerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) throws CustomerNotFoundException {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found with id: " + id);
        }
        return ResponseEntity.ok(customer);
    }
}