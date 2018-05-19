package lt.vu.service.impl;

import lt.vu.dao.api.ImageDao;
import lt.vu.model.Image;
import lt.vu.service.api.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    public Image getImage(int id) {
        return imageDao.getImage(id);
    }

    public void addImage(Image image) {
        imageDao.addImage(image);
    }

    public void deleteImage(Image image) {
        imageDao.deleteImage(image);
    }
}
