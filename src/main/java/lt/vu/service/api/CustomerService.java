package lt.vu.service.api;

import lt.vu.model.Address;
import lt.vu.model.Card;
import lt.vu.model.Customer;

import java.util.List;

public interface CustomerService {

    void addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void setEnabledCustomer(Customer customer, boolean enabled);

    Customer getCustomerById(int customerId);

    List<Customer> getAllCustomers();

    Customer getCustomerByEmail(String email);

    void replaceCart(Customer customer);

    boolean isAddressInfoSame(Address addr1, Address addr2);

    boolean isCardInfoSame(Card card1, Card card2);
}
