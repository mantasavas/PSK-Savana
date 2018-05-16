package lt.vu.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Card implements Serializable {
    @Getter @Setter
    @Id
    @GeneratedValue
    private int cardId;

    @Getter @Setter
    //@Column(nullable=false)
    private String number;

    @Getter @Setter
    //@Column(nullable = false)
    private String name;

    @Getter @Setter
    //@Column(length = 3)
    private String cvv;

    @Getter @Setter
    private int expYear;

    @Getter @Setter
    private int expMonth;

    @Getter @Setter
    @OneToOne
    @JoinColumn(name="customerId", nullable = false)
    private Customer customer;

}
