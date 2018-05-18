package lt.vu.service.impl;

import lt.vu.dao.api.ProductCategoryDao;
import lt.vu.model.ProductCategory;
import lt.vu.service.api.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public void addProductCategory(ProductCategory productCategory) {
        productCategoryDao.addProductCategory(productCategory);
    }

    @Override
    public List<ProductCategory> getAllCategories() {
        return productCategoryDao.getAllCategories();
    }
}
