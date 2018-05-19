package lt.vu.service.api;

import lt.vu.model.Image;

public interface ImageService {
    Image getImage(int id);

    void addImage(Image image);

    void deleteImage(Image image);
}
