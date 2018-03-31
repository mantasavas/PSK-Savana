package lt.vu.dao.impl;

import lt.vu.dao.api.ProductDao;
import lt.vu.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
        session.flush();
    }

    public Product getProductById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);
        session.flush();

        return product;
    }

    public List<Product> getProducts() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Product");

        List<Product> products = query.list();
        session.flush();

        return products;
    }

    public void deleteProduct(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getProductById(id));
        session.flush();
    }
}
