package lt.vu.dao.api;

import lt.vu.model.Customer;

import java.util.List;

public interface CustomerDao {

    void addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void setEnabledCustomer(Customer customer, boolean enabled);

    Customer getCustomerById(int customerId);

    List<Customer> getAllCustomers();

    Customer getCustomerByEmail(String email);
}
