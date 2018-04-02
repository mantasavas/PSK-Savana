package lt.vu.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String category;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private double price;

    @Getter @Setter
    private String condition;

    @Getter @Setter
    private String status;

    @Getter @Setter
    private int unitsInStock;

    @Getter @Setter
    private String manufacturer;

    @Transient
    @Getter @Setter
    private MultipartFile image;
}
