package lt.vu.service.impl;

import lt.vu.dao.api.ProductAttributeDao;
import lt.vu.model.ProductAttribute;
import lt.vu.service.api.ProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductAttributeServiceImpl implements ProductAttributeService {

    @Autowired
    private ProductAttributeDao productAttributeDao;

    @Override
    public void addOrUpdateProductAttribute(ProductAttribute productAttribute) {
        productAttributeDao.addOrUpdateProductAttribute(productAttribute);
    }

    @Override
    public void removeProductAttribute(ProductAttribute productAttribute) {
        productAttributeDao.removeProductAttribute(productAttribute);
    }
}
