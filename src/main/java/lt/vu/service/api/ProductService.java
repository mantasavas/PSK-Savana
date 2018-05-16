package lt.vu.service.api;

import lt.vu.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product getProductById (int id);

    List<Product> getProducstById(String[] productsID);

    void addProduct(Product product);

    void addProducts(List<Product> products);

    void editProduct(Product product);

    void deleteProduct(Product product);
}
