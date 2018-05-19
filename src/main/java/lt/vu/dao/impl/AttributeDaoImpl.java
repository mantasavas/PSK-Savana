package lt.vu.dao.impl;

import lt.vu.dao.api.AttributeDao;
import lt.vu.model.Attribute;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AttributeDaoImpl implements AttributeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addNewAttribute(Attribute attribute) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(attribute);
        session.flush();
    }

    @Override
    public List<Attribute> getAllAttributes() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Attribute");
        List<Attribute> attributeList = query.list();

        return attributeList;
    }
}
