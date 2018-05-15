package lt.vu.dao.impl;

import lt.vu.dao.api.LogDao;
import lt.vu.model.Log;
import lt.vu.model.Product;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class LogDaoImpl implements LogDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Log> getLogs() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Logs");
        List<Log> logList = query.list();
        session.flush();

        return logList;
    }

    @Override
    public Log getLogById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Log log = session.get(Log.class, id);
        session.flush();

        return log;
    }

    @Override
    public void addLog(Log log) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(log);
        session.flush();
    }
}
