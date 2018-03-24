package lt.vu.dao.api;

import lt.vu.model.Product;

import java.util.List;

public interface ProductDao {

    void addProduct(Product product);

    Product getProductById(String id);

    List<Product> getProducts();

    void deleteProduct(String id);
}
