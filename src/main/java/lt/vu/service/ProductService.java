package lt.vu.service;

import lt.vu.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product getProductById (int id);

    void addProduct(Product product);

    void addProducts(List<Product> products);

    void editProduct(Product product);

    void deleteProduct(Product product);
}
