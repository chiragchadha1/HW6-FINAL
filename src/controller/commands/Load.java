package controller.commands;

import controller.ImageCommands;
import controller.ImageUtils;
import controller.ImageUtilsIO;
import controller.ImageUtilsPPM;
import model.ImageGallery;
import model.SingleImage;
import view.ImageView;

/**
 * Loads an image from a specified file path.
 */
public class Load implements ImageCommands {
  protected final String fileInput;
  protected final String fileName;
  protected final ImageView view;

  /**
   * Constructor to assign the file input, given file name, and view to render messages.
   *
   * @param fileInput the file to read from.
   * @param fileName  the given name of the file.
   * @param view      the view to display the image (used for rendering messages).
   * @throws IllegalArgumentException if the file input or new file name is null.
   */
  public Load(String fileInput, String fileName, ImageView view) throws IllegalArgumentException {
    if ((fileInput == null) || (fileName == null) || (view == null)) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.fileInput = fileInput;
    this.fileName = fileName;
    this.view = view;
  }

  /**
   * Performs the command on the given image.
   *
   * @param gallery the gallery that stores the images.
   */
  @Override
  public void runCommands(ImageGallery gallery) {
    ImageUtils loader;
    // Change from previous assignment: check file extension to determine which loader to use
    if (fileInput.toLowerCase().contains("ppm")) {
      loader = new ImageUtilsPPM(view);
    } else {
      loader = new ImageUtilsIO(view);
    }
    SingleImage image = loader.loadImage(fileInput);
    gallery.put(this.fileName, image);
    this.view.renderMessage("Successfully loaded " + this.fileName + " from " + this.fileInput
            + System.lineSeparator());
  }
}



