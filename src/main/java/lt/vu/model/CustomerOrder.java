package lt.vu.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @Getter @Setter
    private String status;

    @Min(value = 0, message = "The order rating must not be less than zero!")
    @Max(value = 5, message = "The order rating must not be greater than five!")
    @Getter @Setter
    private int rating;

    @NotNull
    @Pattern(regexp = "^\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])\\s+\\d{2}:\\d{2}:\\d{2}$",
            message = "Datetime must match format: yyyy-mm-dd hh:mm:ss")
    @Getter @Setter
    private String orderDatetime;
}