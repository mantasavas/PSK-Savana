package lt.vu.dao.api;

import lt.vu.model.Product;

import java.util.List;

public interface ProductDao {

    void addProduct(Product product);

    void editProduct(Product product);

    Product getProductById(Integer id);

    List<Product> getProducts();

    void deleteProduct(Integer id);
}
