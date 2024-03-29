package lt.vu.service.impl;

import lt.vu.dao.api.ProductDao;
import lt.vu.model.Product;
import lt.vu.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public Product getProductById(int productId){
        return productDao.getProductById(productId);
    }

    @Override
    public List<Product> getProducstById(String[] productsID) {
        List<Product> findedProducts = new ArrayList<>();
        for (String productID : productsID){
            findedProducts.add(productDao.getProductById(Integer.parseInt(productID)));
        }
        return findedProducts;
    }

    public List<Product> getProducts(){
        return productDao.getProducts();
    }

    public void addProduct(Product product){
        productDao.addProduct(product);
    }

    public void addProducts(List<Product> products){
        for(Product product : products) {
            productDao.addProduct(product);
        }
    }

    public void editProduct(Product product){
        productDao.editProduct(product);
    }

    public void deleteProduct(Product product){
        productDao.deleteProduct(product);
    }
}
