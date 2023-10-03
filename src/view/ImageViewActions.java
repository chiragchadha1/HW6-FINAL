package view;

import java.io.IOException;

import controller.ImageController;

/**
 * Represents an ImageViewActions interface to handle user input from the GUI and
 * update the model and gallery accordingly.
 */
public interface ImageViewActions extends ImageController {

  /**
   * Loads a given image from a file name through the ImageGallery.
   * Update the GUI to display accordingly.
   *
   * @param fileInput the file to read from.
   * @param fileName  the given name of the file.
   */
  void loadAction(String fileInput, String fileName) throws IOException;

  /**
   * Saves a given image to a file name through the ImageGallery.
   * Update the GUI to display accordingly.
   *
   * @param fileOutput the file to read from.
   * @param fileName   the given name of the file.
   */
  void saveAction(String fileOutput, String fileName);

  /**
   * Brightens an image by a given factor. If the factor is positive, the image is brightened.
   * If the factor is negative, the image is darkened. Updates the view accordingly.
   *
   * @param constant the factor by which the image is brightened or darkened.
   * @param fileName the name of the image to be updated.
   * @param fileNew  the name of the new image.
   */
  void brightenAction(int constant, String fileName, String fileNew);

  /**
   * Downscales an image by a given width and height.
   *
   * @param width    new width of image
   * @param height   new height of image
   * @param fileName the name of the image to be updated.
   * @param fileNew  the name of the new image.
   */
  void downscaleAction(int width, int height, String fileName, String fileNew);

  /**
   * Applies the greyscale filter on an image using the luma matrix. Updates the view accordingly.
   *
   * @param fileName the name of the image to be updated.
   * @param fileNew  the name of the new image.
   */
  void greyScaleAction(String fileName, String fileNew);

  /**
   * Applies the sepia filter on an image using the sepia matrix. Updates the view accordingly.
   *
   * @param fileName the name of the image to be updated.
   * @param fileNew  the name of the new image.
   */
  void sepiaAction(String fileName, String fileNew);

  /**
   * Applies the sharpening filter on an image. Updates the view accordingly.
   *
   * @param fileName the name of the image to be updated.
   * @param fileNew  the name of the new image.
   */
  void sharpenAction(String fileName, String fileNew);

  /**
   * Applies the blur filter on an image. Updates the view accordingly.
   *
   * @param fileName the name of the image to be updated.
   * @param fileNew  the name of the new image.
   */
  void blurAction(String fileName, String fileNew);

  /**
   * Applies the horizontal flip transformation on an image. Updates the view accordingly.
   *
   * @param fileName the name of the image to be updated.
   * @param fileNew  the name of the new image.
   */
  void horizontalFlipAction(String fileName, String fileNew);

  /**
   * Applies the vertical flip transformation on an image. Updates the view accordingly.
   *
   * @param fileName the name of the image to be updated.
   * @param fileNew  the name of the new image.
   */
  void verticalFlipAction(String fileName, String fileNew);

  /**
   * Applies the horizontal flip transformation on an image. Updates the view accordingly.
   *
   * @param fileName the name of the image to be updated.
   * @param fileNew  the name of the new image.
   * @param type     the type of the greyscale component to be applied.
   *                 Valid types are "red-component", "green-component", "blue-component",
   *                 "value-component", "luma-component", and "intensity-component".
   */
  void componentAction(String fileName, String fileNew, String type);

  /**
   * Updates the display image in the GUI based on the given file name.
   *
   * @param fileName the name of the image to be updated.
   */
  void updateViewImage(String fileName);

}
