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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();

        customer.getAddress().setCustomer(customer);

        session.saveOrUpdate(customer);
        session.saveOrUpdate(customer.getAddress());

        Users newUser = new Users();
        newUser.setUsername(customer.getUsername());
        newUser.setPassword(customer.getPassword());
        newUser.setEnabled(true);
        newUser.setCustomerId(customer.getCustomerId());

        Authorities newAuthorities = new Authorities();
        newAuthorities.setUsername(customer.getUsername());
        newAuthorities.setAuthority("ROLE_USER");

        session.saveOrUpdate(newUser);
        session.saveOrUpdate(newAuthorities);

        Cart newCart = new Cart();
        newCart.setCustomer(customer);
        customer.setCart(newCart);

        session.saveOrUpdate(customer);
        session.saveOrUpdate(newCart);

        session.flush();
    }

    public void updateCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();

        customer.getAddress().setCustomer(customer);

        session.saveOrUpdate(customer);
        session.saveOrUpdate(customer.getAddress());

        List<Users> users = getAllUsers();
        Users newUser = null;
        for (Users user: users) {
            if (user.getCustomerId() == customer.getCustomerId()) {
                user.setUsername(customer.getUsername());
                user.setPassword(customer.getPassword());
                newUser = user;
                break;
            }
        }

        Authorities newAuthorities = new Authorities();
        newAuthorities.setUsername(customer.getUsername());

        session.saveOrUpdate(newUser);
        session.saveOrUpdate(newAuthorities);

        List<Cart> carts = getAllCarts();
        Cart newCart = null;
        for (Cart cart: carts) {
            if (cart.getCustomer().getCustomerId() == customer.getCustomerId()) {
                cart.setCustomer(customer);
                newCart = cart;
                customer.setCart(newCart);
                break;
            }
        }

        session.saveOrUpdate(customer);
        session.saveOrUpdate(newCart);

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

    public Customer getCustomerByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Customer where username = ?");
        query.setString(0, username);

        return (Customer) query.uniqueResult();
    }
}