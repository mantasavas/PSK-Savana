package lt.vu.dao.impl;

import lt.vu.dao.api.CartItemDao;
import lt.vu.dao.api.CustomerOrderDao;
import lt.vu.model.Cart;
import lt.vu.model.Customer;
import lt.vu.model.CustomerOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class CustomerOrderDaoImpl implements CustomerOrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CartItemDao cartItemDao;

    public void addCustomerOrder(CustomerOrder customerOrder) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(customerOrder);

        session.flush();
    }

    public List<CustomerOrder> getCustomerOrders(int customerId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CustomerOrder where customerId = :id");
        query.setParameter("id", customerId);

        List<CustomerOrder> orderList = query.list();

        return orderList;
    }

    public List<CustomerOrder> getAllOrders() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CustomerOrder");
        List<CustomerOrder> orderList = query.list();

        return orderList;
    }

    public void setOrderStatus(int orderId, String status) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("update CustomerOrder set status = :newStatus where customerOrderId = :id");
        query.setParameter("newStatus", status);
        query.setParameter("id", orderId);

        query.executeUpdate();
    }

    public void rateOrder(int orderId, int rating) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("update CustomerOrder set rating = :customerRating where customerOrderId = :id");
        query.setParameter("customerRating", rating);
        query.setParameter("id", orderId);

        query.executeUpdate();
    }

    public void writeOrderFeedback(int orderId, String feedback) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("update CustomerOrder set feedback = :customerFeedback where customerOrderId = :id");
        query.setParameter("customerFeedback", feedback);
        query.setParameter("id", orderId);

        query.executeUpdate();
    }

    public CustomerOrder getOrderById(int orderId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(CustomerOrder.class, orderId);
    }

    public List<CustomerOrder> getOrdersByAddressId(int addressId) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from CustomerOrder where addressId = :addrId");
        query.setParameter("addrId", addressId);

        return query.list();
    }

    public List<CustomerOrder> getOrdersByCardId(int cardId) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from CustomerOrder where cardId = :cardId");
        query.setParameter("cardId", cardId);

        return query.list();

    }
}