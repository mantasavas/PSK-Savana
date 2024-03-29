package lt.vu.dao.impl;

import lt.vu.dao.api.CartDao;
import lt.vu.model.Cart;
import lt.vu.model.Customer;
import lt.vu.service.api.CustomerOrderService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;

@Repository
@Transactional
public class CartDaoImpl implements CartDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CustomerOrderService customerOrderService;

    public Cart getCartById(int cartId){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Cart.class, cartId);
    }

    public void create(Cart cart) {
        Session session = sessionFactory.getCurrentSession();

        Customer customer = cart.getCustomer();
        customer.setCart(cart);

        customer.setPasswordRepeat(customer.getPassword());
        session.saveOrUpdate(customer);
        session.saveOrUpdate(cart);

        session.flush();
    }

    public void update(Cart cart){
        int cartId = cart.getCartId();
        BigDecimal grandTotal = customerOrderService.getCustomerOrderGrandTotal(cartId);
        cart.setGrandTotal(grandTotal);

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(cart);
    }

    public Cart validate(int cartId) throws IOException {
        Cart cart = getCartById(cartId);
        if(cart == null || cart.getCartItems().size() == 0){
            throw new IOException(cartId + "");
        }

        update(cart);
        return cart;
    }
}