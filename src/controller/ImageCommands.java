package controller;

import model.ImageGallery;

/**
 * Represents commands to be used by the controller.
 * Allows for unification between commands and the controller.
 */
public interface ImageCommands {

  /**
   * Performs the command on the given image.
   *
   * @param gallery the gallery that stores the images.
   */
  void runCommands(ImageGallery gallery);
}
