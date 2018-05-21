package lt.vu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Attribute {

    @Id
    @GeneratedValue
    @Getter @Setter
    private Integer attributeId;

    @Getter @Setter
    private String attributeKey;

    @OneToMany(mappedBy = "attribute")
    @Getter @Setter
    private List<ProductAttribute> productAttributes = new ArrayList<>();

    @Version
    @Getter @Setter
    private int version;
}
