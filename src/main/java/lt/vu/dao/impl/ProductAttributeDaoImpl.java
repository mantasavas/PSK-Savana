package lt.vu.dao.impl;

import lt.vu.dao.api.ProductAttributeDao;
import lt.vu.model.ProductAttribute;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProductAttributeDaoImpl implements ProductAttributeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addOrUpdateProductAttribute(ProductAttribute productAttribute) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(productAttribute);
        session.flush();
    }
}
