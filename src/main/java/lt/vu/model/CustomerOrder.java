package lt.vu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CustomerOrder implements Serializable {

    private static final long serialVersionUID = 2868509521946166206L;

    @Id
    @GeneratedValue
    @Getter @Setter
    private int customerOrderId;

    @OneToOne
    @JoinColumn(name = "cartId")
    @Getter @Setter
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "customerId")
    @Getter @Setter
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "billingAddressId")
    @Getter @Setter
    private BillingAddress billingAddress;

    @OneToOne
    @JoinColumn(name = "shippingAddressId")
    @Getter @Setter
    private ShippingAddress shippingAddress;


}