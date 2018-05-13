package lt.vu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Min(value = 0, message = "The discount percentage must not be less than 0!")
    @Max(value = 100, message = "The discount percentage must not be greater than 100!")
    @Getter @Setter
    private int productDiscountPercentage;

    @NotNull
    @Pattern(regexp = "^\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])\\s+\\d{2}:\\d{2}:\\d{2}$",
            message = "Datetime must match format: yyyy-mm-dd hh:mm:ss")
    @Getter @Setter
    private String productDiscountExpirationDatetime;

    @Transient
    @Getter @Setter
    private MultipartFile productImage;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    @Getter @Setter
    private List<CartItem> cartItemList;

    public double getActualPrice() {
        double discount = 0;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date discountExpirationDate = null;

        try {
            discountExpirationDate = df.parse(productDiscountExpirationDatetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (discountExpirationDate != null && discountExpirationDate.after(new Date())) {
            discount = productPrice * productDiscountPercentage / 100;
        }

        return productPrice - discount;
    }
}
