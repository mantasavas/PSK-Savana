package lt.vu.service.api;

import lt.vu.model.Cart;
import lt.vu.model.Customer;

public interface CartService {

    Cart getCartById(int cartId);

    void update(Cart cart);

    Cart createNew(Customer customer);
}
