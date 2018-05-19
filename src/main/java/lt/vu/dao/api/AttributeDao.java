package lt.vu.dao.api;

import lt.vu.model.Attribute;

import java.util.List;

public interface AttributeDao {

    void addNewAttribute(Attribute attribute);

    List<Attribute> getAllAttributes();
}
