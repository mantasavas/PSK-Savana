package lt.vu.service.impl;

import lt.vu.dao.api.CustomerDao;
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
        customerDao.updateCustomer(customer);
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