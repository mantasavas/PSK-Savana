package lt.vu.service.impl;

import lombok.extern.slf4j.Slf4j;
import lt.vu.dao.api.CartDao;
import lt.vu.dao.api.CustomerOrderDao;
import lt.vu.model.*;
import lt.vu.service.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CartItemService itemService;

    @Autowired
    private CustomerOrderDao customerOrderDao;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartDao cartDao;

    public void addCustomerOrder(CustomerOrder customerOrder){
        customerOrderDao.addCustomerOrder(customerOrder);
    }

    @Override
    public List<CustomerOrder> getCustomerOrders(int customerId) {
        return customerOrderDao.getCustomerOrders(customerId);
    }

    public BigDecimal getCustomerOrderGrandTotal(int cartId) {
        BigDecimal grandTotal = new BigDecimal(0);
        Cart cart = cartService.getCartById(cartId);
        List<CartItem> cartItems = cart.getCartItems();

        for (CartItem item : cartItems){
            grandTotal = grandTotal.add(item.getTotalPrice());
        }

        return grandTotal;
    }

    @Override
    public List<CustomerOrder> getAllOrders() {
        return customerOrderDao.getAllOrders();
    }

    @Override
    public void setOrderStatus(int orderId, String status) {
        customerOrderDao.setOrderStatus(orderId, status);
    }

    @Override
    public void rateOrder(int orderId, int rating) {
        customerOrderDao.rateOrder(orderId, rating);
    }

    @Override
    public void writeOrderFeedback(int orderId, String feedback) {
        customerOrderDao.writeOrderFeedback(orderId, feedback);
    }

    @Override
    public CustomerOrder getOrderById(int orderId) {
        return customerOrderDao.getOrderById(orderId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processOrder(CustomerOrder order) throws IOException {
        acceptOrder(order);

        customerService.replaceCart(order.getCustomer());

        customerOrderDao.addCustomerOrder(order);

        // Payment has to be the last step in transaction, because we can't reverse it
        paymentService.pay(order);
    }

    public CustomerOrder initOrder(int cartId) throws IOException {
       CustomerOrder order = new CustomerOrder();
       Cart cart = cartDao.validate(cartId);
       Customer customer = cart.getCustomer();

       order.setCart(cart);
       order.setCustomer(customer);
       order.setAddress(new Address(customer.getAddress()));
       order.setCard(new Card(customer.getCard()));

       return order;
    }

    private void acceptOrder(CustomerOrder order) {
        order.setStatus("Accepted");
        order.setRating(0);
        order.setOrderDatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        Customer customer = order.getCustomer();

        if (customerService.isAddressInfoSame(customer.getAddress(), order.getAddress())) {
            log.debug("Using customer's current address");
            order.setAddress(customer.getAddress());
        }

        if (customerService.isCardInfoSame(customer.getCard(), order.getCard())) {
            log.debug("Using customer's current card");
            order.setCard(customer.getCard());
        }
    }
}
