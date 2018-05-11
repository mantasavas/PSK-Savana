package lt.vu.dao.api;

import lt.vu.model.CustomerOrder;

import java.util.List;

public interface CustomerOrderDao {

    void addCustomerOrder(CustomerOrder customerOrder);

    List<CustomerOrder> getCustomerOrders(int customerId);
}
