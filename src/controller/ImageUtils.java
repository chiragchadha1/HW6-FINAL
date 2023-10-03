package controller;

import model.ImageGallery;
import model.SingleImage;

/**
 * Utility interface for performing read and write operations on images.
 */
public interface ImageUtils {

  /**
   * Loads a given image from a file name through the ImageGallery.
   *
   * @param fileName the name of the file to read from.
   * @return the image that was read from the file.
   */
  SingleImage loadImage(String fileName);

  /**
   * Writes a given image to a file name through the ImageGallery.
   *
   * @param fileName   the name of the file to write to.
   * @param fileOutput the output to write to the image.
   * @param gallery    the gallery that stores the images.
   */
  void saveImage(String fileName, String fileOutput, ImageGallery gallery);
}
