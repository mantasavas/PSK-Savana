package lt.vu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Image implements Serializable {

    private static final long serialVersionUID = 4677164461199296516L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private int imageId;

    @ManyToOne
    @JoinColumn(name = "productId")
    @Getter @Setter
    private Product product;
}
