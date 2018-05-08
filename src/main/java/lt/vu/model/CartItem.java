package lt.vu.model;

import lombok.Getter;
import lombok.Setter;

public class CartItem {

    @Getter @Setter
    private Product product;

    @Getter @Setter
    private int quantity;

    @Getter @Setter
    private double totalPrice;

    public CartItem() {
    }

    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.totalPrice = product.getPrice();
    }
}
