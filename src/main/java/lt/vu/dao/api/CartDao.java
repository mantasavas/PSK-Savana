package lt.vu.dao.api;

import lt.vu.model.Cart;

public interface CartDao {

    Cart create(Cart cart);

    Cart read(String cartId);

    void update(String cartId, Cart cart);

    void delete (String cartId);
}
