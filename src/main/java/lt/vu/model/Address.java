package lt.vu.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@EqualsAndHashCode
@ToString
public class Address implements Serializable {

    private static final long serialVersionUID = 989191150380037359L;

    @Id
    @GeneratedValue
    @Getter @Setter
    private Integer addressId;

    @Getter @Setter
    private String streetName;

    @Getter @Setter
    private String apartmentNumber;

    @Getter @Setter
    private String city;

    @Getter @Setter
    private String state;

    @Getter @Setter
    private String country;

    @Getter @Setter
    private String zipCode;

    @ManyToOne
    @Getter @Setter
    private Customer customer;

    @Version
    @Getter @Setter
    private int version;

    public Address() {}

    public Address(Address orig) {
        addressId = null;
        streetName = orig.streetName;
        apartmentNumber = orig.apartmentNumber;
        city = orig.city;
        state = orig.state;
        country = orig.country;
        zipCode = orig.zipCode;
        customer = orig.customer;
    }
}