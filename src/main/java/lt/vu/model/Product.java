package lt.vu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private String productDescription;

    @Min(value = 0, message = "The product price must not be less than zero!")
    @Getter @Setter
    private BigDecimal productPrice;

    @Getter @Setter
    private String productStatus;

    @Getter @Setter
    private String productManufacturer;

    @Min(value = 0, message = "The discount percentage must not be less than 0!")
    @Max(value = 100, message = "The discount percentage must not be greater than 100!")
    @Getter @Setter
    private Integer productDiscountPercentage;

    //also matches empty string (because this field is not required)
    @Pattern(regexp = "^|((\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1])[' '](0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))$",
            message = "Datetime must be valid and match format: yyyy-mm-dd hh:mm:ss")
    @Getter @Setter
    private String productDiscountExpirationDatetime;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    @Getter @Setter
    private List<CartItem> cartItemList;

    // For exporting products, administrator must have ability to pick product from the list
    @Transient
    @Getter @Setter
    private Boolean picked;

    @Getter @Setter
    private String productCategory;

    @OneToMany(mappedBy = "product")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Getter @Setter
    private List<ProductAttribute> productAttributes = new ArrayList<>();

    @Transient
    @Getter @Setter
    private List<MultipartFile> files;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //add this to avoid error: cannot simultaneously fetch multiple bags
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    @Getter @Setter
    private List<Image> productImages;

    @Getter @Setter
    private Integer featuredImage;

    /*public BigDecimal getProductPrice() {
        return this.productPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
    }*/

    public BigDecimal getActualPrice() {
        BigDecimal discount = new BigDecimal(0);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date discountExpirationDate = null;

        try {
            discountExpirationDate = df.parse(productDiscountExpirationDatetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (discountExpirationDate != null && discountExpirationDate.after(new Date())) {
            BigDecimal productDiscountPercentageBigDecimal = new BigDecimal(productDiscountPercentage);
            BigDecimal oneHundredBigDecimal = new BigDecimal(100);
            discount = productPrice.multiply(productDiscountPercentageBigDecimal)
                    .divide(oneHundredBigDecimal, 2, BigDecimal.ROUND_HALF_UP);
        }

        return productPrice.subtract(discount).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
