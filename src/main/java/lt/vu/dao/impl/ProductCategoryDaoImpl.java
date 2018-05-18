package lt.vu.dao.impl;

import lt.vu.dao.api.ProductCategoryDao;
import lt.vu.model.ProductCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductCategoryDaoImpl implements ProductCategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addProductCategory(ProductCategory productCategory) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(productCategory);
        session.flush();
    }

    @Override
    public List<ProductCategory> getAllCategories() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ProductCategory");
        List<ProductCategory> categoryList = query.list();

        return categoryList;
    }
}
