package model;

import java.util.Map;

/**
 * Represents a gallery of images to store multiple images at one time.
 */
public interface ImageGallery {

  /**
   * Puts the given image, represented by its file name, into the gallery represented as a HashMap.
   *
   * @param fileName represents the name of the image.
   * @param model    represents the model of the image.
   */
  void put(String fileName, SingleImage model);

  /**
   * Returns the image model stored in the gallery.
   *
   * @param fileName represents the name of the image.
   * @return the image model stored.
   */
  SingleImage getImage(String fileName);


  /**
   * Returns the gallery of images.
   *
   * @return the gallery of images.
   */
  Map<String, SingleImage> getGallery();

}
