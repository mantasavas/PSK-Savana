package lt.vu.service.api;

import lt.vu.model.Attribute;

import java.util.List;

public interface AttributeService {

    void addNewAttribute(Attribute attribute);

    List<Attribute> getAllAttributes();
}
