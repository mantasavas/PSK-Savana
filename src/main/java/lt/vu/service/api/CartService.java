package lt.vu.service.api;

import lt.vu.model.Cart;

public interface CartService {

    Cart getCartById(int cartId);

    void update(Cart cart);
}
