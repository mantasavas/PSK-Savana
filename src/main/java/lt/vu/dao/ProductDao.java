package lt.vu.dao;

import lt.vu.model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private List<Product> products;

    public List<Product> getProducts() {
        Product product1 = new Product();
        product1.setId("1");
        product1.setName("Keyboard");
        product1.setCategory("PC");
        product1.setDescription("Very good keyboard");
        product1.setPrice(50);
        product1.setCondition("New");
        product1.setStatus("Active");
        product1.setUnitsInStock(3);
        product1.setManufacturer("Razer");

        Product product2 = new Product();
        product2.setId("2");
        product2.setName("Mouse");
        product2.setCategory("PC");
        product2.setDescription("Very good mouse");
        product2.setPrice(20);
        product2.setCondition("Used");
        product2.setStatus("Active");
        product2.setUnitsInStock(1);
        product2.setManufacturer("Razer");

        products = new ArrayList<Product>();
        products.add(product1);
        products.add(product2);

        return products;
    }

    public Product getProductById(String id) throws IOException {
        for (Product product : getProducts()) {
            if (product.getId().equals(id)) {
                return product;
            }
        }

        throw new IOException("Cannot find product!");
    }
}
