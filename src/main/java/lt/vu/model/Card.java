package lt.vu.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@EqualsAndHashCode
@ToString
public class Card implements Serializable {
    @Getter @Setter
    @Id
    @GeneratedValue
    private Integer cardId;

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
    @ManyToOne
    @JoinColumn(name="customerId", nullable = false)
    private Customer customer;

    public Card() {}

    public Card(Card orig) {
        cardId = null;
        number = orig.number;
        name = orig.name;
        cvv = orig.cvv;
        expYear = orig.expYear;
        expMonth = orig.expMonth;
        customer = orig.customer;
    }
}
