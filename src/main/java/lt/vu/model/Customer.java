package lt.vu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = -3416421020837687546L;

    @Id
    @GeneratedValue
    @Getter @Setter
    private int customerId;

    @NotEmpty(message = "The customer name must not be empty")
    @Getter @Setter
    private String customerName;

    @NotEmpty(message = "The customer email must not be empty")
    @Getter @Setter
    private String customerEmail;

    @Getter @Setter
    private String customerPhone;

    @NotEmpty(message = "The customer username must not be empty")
    @Getter @Setter
    private String username;

    @NotEmpty(message = "The customer password must not be empty")
    @Getter @Setter
    private String password;

    @Getter @Setter
    private boolean enabled;

    @OneToOne
    @JoinColumn(name = "billingAddressId")
    @Getter @Setter
    private BillingAddress billingAddress;

    @OneToOne
    @JoinColumn(name = "shippingAddressId")
    @Getter @Setter
    private ShippingAddress shippingAddress;

    @OneToOne
    @JoinColumn(name = "cartId")
    @JsonIgnore
    @Getter @Setter
    private Cart cart;
}