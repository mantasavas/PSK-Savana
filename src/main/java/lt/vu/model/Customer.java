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

    @NotEmpty(message = "The name must not be empty")
    @Getter @Setter
    private String customerName;

    @NotEmpty(message = "The email must not be empty")
    @Getter @Setter
    private String customerEmail;

    @Getter @Setter
    private String customerPhone;

    @NotEmpty(message = "The password must not be empty")
    @Getter @Setter
    private String password;

    @NotEmpty(message = "The password must not be empty")
    @Transient
    @Getter @Setter
    private String passwordRepeat;

    @Getter @Setter
    private boolean enabled;

    @OneToOne
    @JoinColumn(name = "addressId")
    @Getter @Setter
    private Address address;

    @OneToOne
    @JoinColumn(name = "cartId")
    @JsonIgnore
    @Getter @Setter
    private Cart cart;
}