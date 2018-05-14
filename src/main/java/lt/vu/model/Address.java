package lt.vu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = 989191150380037359L;

    @Id
    @GeneratedValue
    @Getter @Setter
    private int addressId;

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

    @OneToOne
    @Getter @Setter
    private Customer customer;

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}