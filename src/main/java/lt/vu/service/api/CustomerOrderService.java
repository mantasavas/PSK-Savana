package lt.vu.service.api;

import lt.vu.model.CustomerOrder;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface CustomerOrderService {

    void addCustomerOrder(CustomerOrder customerOrder);

    List<CustomerOrder> getCustomerOrders(int customerId);

    BigDecimal getCustomerOrderGrandTotal(int cartId);

    List<CustomerOrder> getAllOrders();

    void setOrderStatus(int orderId, String status);

    void rateOrder(int orderId, int rating);

    void writeOrderFeedback(int orderId, String feedback);

    void processOrder(CustomerOrder order) throws IOException;

    CustomerOrder getOrderById(int orderId);

    CustomerOrder initOrder(int cartId) throws IOException;
}
