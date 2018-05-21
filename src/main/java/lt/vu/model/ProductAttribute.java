package lt.vu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductAttribute implements Serializable {

    private static final long serialVersionUID = 7061492474491963099L;

    @Id
    @ManyToOne
    @JoinColumn(name = "productId")
    @Getter @Setter
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "attributeId")
    @Getter @Setter
    private Attribute attribute;

    @Getter @Setter
    private String attributeValue;

    @Version
    @Getter @Setter
    private int version;
}
