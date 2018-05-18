package lt.vu.service.impl;

import lt.vu.dao.api.CartDao;
import lt.vu.model.Cart;
import lt.vu.model.Customer;
import lt.vu.service.api.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    public Cart getCartById(int cartId){
        return cartDao.getCartById(cartId);
    }

    public void update(Cart cart){
        cartDao.update(cart);
    }

    public Cart createNew(Customer customer) {
        Cart cart = new Cart();
        cart.setCustomer(customer);
        customer.setCart(cart);
        cartDao.create(cart);
        return cart;
    }
}
