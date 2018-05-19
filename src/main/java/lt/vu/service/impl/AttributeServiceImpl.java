package lt.vu.service.impl;

import lt.vu.dao.api.AttributeDao;
import lt.vu.model.Attribute;
import lt.vu.service.api.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeDao attributeDao;

    @Override
    public void addNewAttribute(Attribute attribute) {
        attributeDao.addNewAttribute(attribute);
    }

    @Override
    public List<Attribute> getAllAttributes() {
        return attributeDao.getAllAttributes();
    }
}
