package lt.vu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = -5081150964282877541L;

    @Id
    @GeneratedValue
    @Getter @Setter
    private int productCategoryId;

    @Getter @Setter
    private String categoryName;
}
