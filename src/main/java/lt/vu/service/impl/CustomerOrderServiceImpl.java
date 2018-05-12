package lt.vu.service.impl;

import lt.vu.dao.api.CustomerOrderDao;
import lt.vu.model.Cart;
import lt.vu.model.CartItem;
import lt.vu.model.CustomerOrder;
import lt.vu.service.CartService;
import lt.vu.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderDao customerOrderDao;

    @Autowired
    private CartService cartService;

    public void addCustomerOrder(CustomerOrder customerOrder){
        customerOrderDao.addCustomerOrder(customerOrder);
    }

    @Override
    public List<CustomerOrder> getCustomerOrders(int customerId) {
        return customerOrderDao.getCustomerOrders(customerId);
    }

    public double getCustomerOrderGrandTotal(int cartId) {
        double grandTotal = 0;
        Cart cart = cartService.getCartById(cartId);
        List<CartItem> cartItems = cart.getCartItems();

        for (CartItem item : cartItems){
            grandTotal += item.getTotalPrice();
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
}
