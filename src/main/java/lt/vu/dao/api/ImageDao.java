package lt.vu.dao.api;

import lt.vu.model.Image;

public interface ImageDao {

    Image getImage(int id);

    void addImage(Image image);

    void deleteImage(Image image);
}
