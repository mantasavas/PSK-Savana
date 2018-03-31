package lt.vu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
