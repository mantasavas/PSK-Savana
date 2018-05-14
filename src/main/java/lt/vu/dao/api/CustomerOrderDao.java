package lt.vu.dao.api;

import lt.vu.model.CustomerOrder;

import java.util.List;

public interface CustomerOrderDao {

    void addCustomerOrder(CustomerOrder customerOrder);

    List<CustomerOrder> getCustomerOrders(int customerId);

    List<CustomerOrder> getAllOrders();

    void setOrderStatus(int orderId, String status);

    void rateOrder(int orderId, int rating);

    void writeOrderFeedback(int orderId, String feedback);

    CustomerOrder getOrderById(int orderId);
}
