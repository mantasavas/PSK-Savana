package lt.vu.dao.api;

import lt.vu.model.Log;
import lt.vu.model.Product;

import java.util.List;

public interface LogDao {

    List<Log> getLogs();

    Log getLogById(int id);

    void addLog(Log log);
}
