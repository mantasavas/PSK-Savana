package lt.vu.dao.impl;

import lt.vu.dao.api.CustomerDao;
import lt.vu.model.Authorities;
import lt.vu.model.Cart;
import lt.vu.model.Customer;
import lt.vu.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(customer);

        customer.getAddress().setCustomer(customer);

        Users newUser = new Users();
        newUser.setUsername(customer.getCustomerEmail());
        newUser.setPassword(customer.getPassword());
        newUser.setEnabled(true);
        newUser.setCustomerId(customer.getCustomerId());

        Authorities newAuthorities = new Authorities();
        newAuthorities.setUsername(customer.getCustomerEmail());
        newAuthorities.setAuthority("ROLE_USER");

        customer.getCard().setCustomer(customer);

        session.saveOrUpdate(customer.getAddress());
        session.saveOrUpdate(newUser);
        session.saveOrUpdate(newAuthorities);
        session.saveOrUpdate(customer.getCard());
        session.flush();
    }

    public void updateCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();

        Customer oldCustomer = session.get(Customer.class, customer.getCustomerId());
        customer.setCart(oldCustomer.getCart());
        session.detach(oldCustomer);
        session.detach(oldCustomer.getAddress());
        session.detach(oldCustomer.getCard());

        customer.getAddress().setCustomer(customer);
        customer.getCard().setCustomer(customer);

        session.saveOrUpdate(customer);
        session.saveOrUpdate(customer.getAddress());
        session.saveOrUpdate(customer.getCard());

        Users editedUser = getUserByCustomerId(customer.getCustomerId());
        Authorities editedAuthorities = getAuthoritiesByUsername(editedUser.getUsername());

        editedUser.setUsername(customer.getCustomerEmail());
        editedUser.setPassword(customer.getPassword());
        editedAuthorities.setUsername(customer.getCustomerEmail());

        session.saveOrUpdate(editedUser);
        session.saveOrUpdate(editedAuthorities);

        session.flush();
    }

    public void setCart(Customer customer, Cart cart) {
        Session session = sessionFactory.getCurrentSession();

        customer.setCart(cart);
        customer.setPasswordRepeat(customer.getPassword());

        session.saveOrUpdate(customer);

        session.flush();
    }

    @Transactional
    public void setEnabledCustomer(Customer customer, boolean enabled) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("update Customer set enabled = :enable where customerId = :id");
        query.setParameter("enable", enabled);
        query.setParameter("id", customer.getCustomerId());

        Query query2 = session.createQuery("update Users set enabled = :enable where customerId = :id");
        query2.setParameter("enable", enabled);
        query2.setParameter("id", customer.getCustomerId());

        query.executeUpdate();
        query2.executeUpdate();
    }

    private List<Users> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Users");
        List<Users> usersList = query.list();

        return usersList;
    }

    private Users getUserByCustomerId(int customerId) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Users where customerId = :custId");
        query.setParameter("custId", customerId);

        return (Users) query.uniqueResult();
    }

    private List<Cart> getCartsByCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Cart where customerId = :custId");
        query.setParameter("custId", customer.getCustomerId());

        return query.list();
    }


    private List<Cart> getAllCarts() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Cart");
        List<Cart> cartList = query.list();

        return cartList;
    }

    public Customer getCustomerById(int customerId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Customer.class, customerId);
    }

    public List<Customer> getAllCustomers() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Customer");
        List<Customer> customerList = query.list();

        return customerList;
    }

    public Customer getCustomerByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Customer where lower(customerEmail) = lower(:email)");
        query.setParameter("email", email);

        return (Customer) query.uniqueResult();
    }

    private Authorities getAuthoritiesByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Authorities where username = :username");
        query.setParameter("username", username);

        return (Authorities) query.uniqueResult();
    }
}