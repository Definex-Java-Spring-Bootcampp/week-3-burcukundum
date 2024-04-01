package onlineShoppingSystem.customerService;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Custom methods if needed
}

