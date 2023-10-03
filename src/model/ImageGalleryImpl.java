package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the {@link  ImageGallery} interface.
 * This class is used to store multiple images at one time represented as a HashMap.
 * Images are represented by their file name and corresponding {@link SingleImage} model.
 */
public class ImageGalleryImpl implements ImageGallery {
  private final Map<String, SingleImage> imageGallery;

  /**
   * Constructor to initialize the image gallery as a HashMap.
   */
  public ImageGalleryImpl() {
    imageGallery = new HashMap<String, SingleImage>();
  }

  /**
   * Puts the given image, represented by its file name, into the gallery represented as a HashMap.
   *
   * @param fileName represents the name of the image.
   * @param model    represents the model of the image.
   */
  @Override
  public void put(String fileName, SingleImage model) {
    if (model != null) {
      imageGallery.put(fileName, model);
    } else {
      throw new IllegalArgumentException("Image cannot be null.");
    }
  }

  /**
   * Returns the image model stored in the gallery.
   *
   * @param fileName represents the name of the image.
   * @return the image model stored.
   */
  @Override
  public SingleImage getImage(String fileName) {
    return imageGallery.get(fileName);
  }

  /**
   * Returns the gallery of images.
   *
   * @return the gallery of images.
   */
  @Override
  public Map<String, SingleImage> getGallery() {
    return imageGallery;
  }

}
