package lt.vu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;

@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = -489286844229855914L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private int productId;

    @NotEmpty(message = "The product name must not be empty!")
    @Getter @Setter
    private String productName;

    @Getter @Setter
    private String productCategory;

    @Getter @Setter
    private String productDescription;

    @Min(value = 0, message = "The product price must not be less than zero!")
    @Getter @Setter
    private double productPrice;

    @Getter @Setter
    private String productCondition;

    @Getter @Setter
    private String productStatus;

    @Getter @Setter
    private String productManufacturer;

    @Transient
    @Getter @Setter
    private MultipartFile productImage;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    @Getter @Setter
    private List<CartItem> cartItemList;
}
