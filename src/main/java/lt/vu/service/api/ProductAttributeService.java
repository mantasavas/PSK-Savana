package lt.vu.service.api;

import lt.vu.model.ProductAttribute;

public interface ProductAttributeService {

    void addOrUpdateProductAttribute(ProductAttribute productAttribute);

    void removeProductAttribute(ProductAttribute productAttribute);
}
