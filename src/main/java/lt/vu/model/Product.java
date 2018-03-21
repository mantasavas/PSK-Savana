package lt.vu.model;

import lombok.Getter;
import lombok.Setter;

public class Product {

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
