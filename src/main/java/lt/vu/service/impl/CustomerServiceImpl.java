package lt.vu.service.impl;

import lt.vu.dao.api.CustomerDao;
import lt.vu.model.Address;
import lt.vu.model.Card;
import lt.vu.model.Customer;
import lt.vu.service.api.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public void addCustomer(Customer customer){
        customerDao.addCustomer(customer);
    }

    public void updateCustomer(Customer customer) {
        Customer oldCustomer = customerDao.getCustomerById(customer.getCustomerId());
        Address oldAddr = oldCustomer.getAddress();
        Card oldCard = oldCustomer.getCard();
        Address newAddr = customer.getAddress();
        Card newCard = customer.getCard();

        System.out.println("Old addr: " + oldAddr.toString());
        System.out.println("New addr: " + oldAddr.toString());
        System.out.println("Old card: " + oldCard.toString());
        System.out.println("New card: " + newCard.toString());

        if (!oldAddr.equals(newAddr))
            oldCustomer.setAddress(new Address(newAddr));
        if (!oldCard.equals(newCard)) {
            oldCustomer.setCard(new Card(newCard));
        }

        oldCustomer.setPasswordRepeat(customer.getPasswordRepeat());
        oldCustomer.setPassword(customer.getPassword());
        oldCustomer.setCustomerEmail(customer.getCustomerEmail());
        oldCustomer.setCustomerName(customer.getCustomerName());
        oldCustomer.setCustomerPhone(customer.getCustomerPhone());

        customerDao.updateCustomer(customer);
    }

    public boolean equal(Address addr1, Address addr2) {
        //TODO:
    }

    public void setEnabledCustomer(Customer customer, boolean enabled) {
        customerDao.setEnabledCustomer(customer, enabled);
    }

    public Customer getCustomerById(int customerId){
        return customerDao.getCustomerById(customerId);
    }

    public List<Customer> getAllCustomers(){
        return customerDao.getAllCustomers();
    }

    public Customer getCustomerByEmail (String email){
        return customerDao.getCustomerByEmail(email);
    }

    public void replaceCart(Customer customer) {
        // New cart will be created when needed
        customerDao.setCart(customer, null);
    }
}