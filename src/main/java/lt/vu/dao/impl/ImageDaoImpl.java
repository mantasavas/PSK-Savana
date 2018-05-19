package lt.vu.dao.impl;

import lt.vu.dao.api.ImageDao;
import lt.vu.model.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ImageDaoImpl implements ImageDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Image getImage (int id){
        Session session = sessionFactory.getCurrentSession();
        Image image = session.get(Image.class, id);
        session.flush();

        return image;
    }

    public void addImage (Image image){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(image);
        session.flush();
    }

    public void deleteImage (Image image){
        Session session = sessionFactory.getCurrentSession();
        session.delete(image);
        session.flush();
    }
}
