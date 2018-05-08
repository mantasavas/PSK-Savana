package lt.vu.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    @Getter @Setter
    private String id;

    @Getter @Setter
    private Map<Integer, CartItem> cartItems;

    @Getter @Setter
    private double grandTotal;

    private Cart(){
        cartItems = new HashMap<>();
        grandTotal = 0;
    }

    public Cart(String id) {
        this();
        this.id = id;
    }

    public void addCartItem(CartItem item) {
        Integer productId = item.getProduct().getId();

        if (cartItems.containsKey(productId)) {
            CartItem existingCarItem = cartItems.get(productId);
            existingCarItem.setQuantity(existingCarItem.getQuantity() + item.getQuantity());
            cartItems.put(productId, existingCarItem);
        } else {
            cartItems.put(productId, item);
        }

        updateGrandTotal();
    }

    public void removeCartItem(CartItem item) {
        Integer productId = item.getProduct().getId();
        cartItems.remove(productId);
        updateGrandTotal();
    }

    public void updateGrandTotal() {
        grandTotal = 0;
        for (CartItem item : cartItems.values()) {
            grandTotal += item.getTotalPrice();
        }
    }
}
