package view;

import java.awt.image.BufferedImage;

/**
 * Represents an ImageViewGUI interface to display the GUI of the image processing app.
 * Handles all user interactions with the GUI and filtering/transformation of images.
 */
public interface ImageViewGUI {

  /**
   * Render a specific message using JOptionPane.
   *
   * @param message the message to be transmitted
   * @param title   the title of the message
   * @param type    the type of message (ex: information, error, etc.)
   */
  void renderMessage(String message, String title, int type);

  /**
   * Displays the GUI view to the user if true, otherwise hides the GUI view.
   *
   * @param b true if the GUI view should be displayed, false otherwise.
   */
  void displayGUI(boolean b);

  /**
   * Initializes a controller to allow the view to access actions from the controller.
   *
   * @param controller the controller to be initialized.
   */
  void setFeatures(ImageViewActions controller);

  /**
   * Creates a JMenuBar with all the necessary menu items (file, gallery, filter, transform, etc.)
   */
  void createMenu();

  /**
   * Loads an image from a specified file path.
   */
  void loadImage();

  /**
   * Saves the current image to a specified file path.
   */
  void saveImage();

  /**
   * Applies a given operation to an image. Utilizes the {@link ImageViewActions} to do so.
   *
   * @param operation the operation to be applied.
   */
  void processImage(String operation);

  /**
   * Updates the current image displayed in the GUI with the given image.
   */
  void updateImage(BufferedImage image);

  /**
   * Displays the loaded images in the {@link model.ImageGallery}.
   */
  void displayGallery();

  /**
   * Updates the name of the image displayed in the GUI.
   *
   * @param fileName the new name of the image.
   */
  void updateImageName(String fileName);
}
