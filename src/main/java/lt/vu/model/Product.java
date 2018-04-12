package lt.vu.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @NotEmpty(message = "The product name must not be empty!")
    @Getter @Setter
    private String name;

    @Getter @Setter
    private String category;

    @Getter @Setter
    private String description;

    @Min(value = 0, message = "The product price must not be less than zero!")
    @Getter @Setter
    private double price;

    @Getter @Setter
    private String condition;

    @Getter @Setter
    private String status;

    @Getter @Setter
    private String manufacturer;

    @Transient
    @Getter @Setter
    private MultipartFile image;
}
