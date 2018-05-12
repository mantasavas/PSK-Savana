package lt.vu.service;

import lt.vu.model.CustomerOrder;

import java.util.List;

public interface CustomerOrderService {

    void addCustomerOrder(CustomerOrder customerOrder);

    List<CustomerOrder> getCustomerOrders(int customerId);

    double getCustomerOrderGrandTotal(int cartId);

    List<CustomerOrder> getAllOrders();
}
