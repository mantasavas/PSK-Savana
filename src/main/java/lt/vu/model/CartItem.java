package lt.vu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class CartItem implements Serializable {

    private static final long serialVersionUID = 4993851003282190999L;

    @Id
    @GeneratedValue
    @Getter @Setter
    private int cartItemId;

    @ManyToOne
    @JoinColumn(name = "cartId")
    @JsonIgnore
    @Getter @Setter
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "productId")
    @Getter @Setter
    private Product product;

    @Getter @Setter
    private int quantity;

    @Getter @Setter
    private BigDecimal totalPrice;

}
