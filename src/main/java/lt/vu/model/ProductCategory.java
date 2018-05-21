package lt.vu.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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
    private String productCategoryName;

    @Transient
    @Getter @Setter
    private MultipartFile productCategoryImage;


    @Version
    @Getter @Setter
    private int version;
}
