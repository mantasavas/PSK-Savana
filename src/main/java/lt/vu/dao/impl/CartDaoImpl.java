package lt.vu.dao.impl;

import lt.vu.dao.api.CartDao;
import lt.vu.model.Cart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CartDaoImpl implements CartDao {

    private Map<String, Cart> listOfCarts;

    public CartDaoImpl() {
        listOfCarts = new HashMap<>();
    }

    @Override
    public Cart create(Cart cart) {
        if (listOfCarts.keySet().contains(cart.getId())) {
            throw new IllegalArgumentException("Cannot create a cart. A cart with the given ID already exists!");
        }

        listOfCarts.put(cart.getId(), cart);
        return cart;
    }

    @Override
    public Cart read(String cartId) {
        return listOfCarts.get(cartId);
    }

    @Override
    public void update(String cartId, Cart cart) {
        if (!listOfCarts.keySet().contains(cartId)) {
            throw new IllegalArgumentException("Cannot update cart. A cart with the given ID doesn't exist!");
        }

        listOfCarts.put(cartId, cart);
    }

    @Override
    public void delete(String cartId) {
        if (!listOfCarts.keySet().contains(cartId)) {
            throw new IllegalArgumentException("Cannot delete cart. A cart with the given ID doesn't exist!");
        }

        listOfCarts.remove(cartId);
    }
}
