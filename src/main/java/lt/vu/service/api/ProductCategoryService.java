package lt.vu.service.api;

import lt.vu.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    void addProductCategory(ProductCategory productCategory);

    List<ProductCategory> getAllCategories();
}
