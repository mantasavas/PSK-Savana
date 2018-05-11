package lt.vu.dao.api;

import lt.vu.model.Customer;

import java.util.List;

public interface CustomerDao {

    void addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    Customer getCustomerById(int customerId);

    List<Customer> getAllCustomers();

    Customer getCustomerByUsername(String username);
}
