package lt.vu.dao.api;

import lt.vu.model.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {

    void addProductCategory(ProductCategory productCategory);

    List<ProductCategory> getAllCategories();
}
