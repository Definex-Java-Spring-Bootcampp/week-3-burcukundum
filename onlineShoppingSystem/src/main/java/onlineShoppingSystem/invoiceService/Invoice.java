package onlineShoppingSystem.invoiceService;

import onlineShoppingSystem.orderService.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Invoice {

    static Set<Order> orderInvoiceList = new HashSet<>();
    private BigDecimal amount;
    private LocalDateTime invoiceDate;

    private Integer orderId;

    public Invoice() {

    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Invoice(Order order, LocalDateTime invoiceDate) {

        Try:
            this.amount =order.getTotalAmount();
            this.invoiceDate = invoiceDate;
            orderInvoiceList.add(order);
        Exception:
            System.out.println("models.Invoice has been created for this order");
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @Override
    public String toString() {
        return "models.Invoice{" +
                "amount=" + amount +
                ", invoiceDate=" + invoiceDate +
                '}';
    }

    public static List<Order> getHighValueInvoices(BigDecimal thresholdAmount) {
        return orderInvoiceList.stream()
                .filter(order -> order.getTotalAmount().compareTo(thresholdAmount) > 0)
                .collect(Collectors.toList());
    }

}
