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
public class Save implements ImageCommands {
  protected final String fileOutput;
  protected final String fileName;
  protected final ImageView view;

  /**
   * Constructor to assign the file output, given file name, and view to render messages.
   *
   * @param fileOutput the file to read from.
   * @param fileName   the given name of the file.
   */
  public Save(String fileOutput, String fileName, ImageView view) throws IllegalArgumentException {
    if ((fileName == null) || (fileOutput == null) || (view == null)) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.fileOutput = fileOutput;
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
    ImageUtils save;
    // Change from previous assignment: check file extension to determine which saver to use
    if (fileOutput.toLowerCase().contains("ppm")) {
      save = new ImageUtilsPPM(view);
    } else {
      save = new ImageUtilsIO(view);
    }
    save.saveImage(fileName, fileOutput, gallery);
    SingleImage newImage = gallery.getImage(this.fileName);
    gallery.put(this.fileOutput, newImage);
    this.view.renderMessage("Successfully saved " + this.fileName + " to " + this.fileOutput
            + System.lineSeparator());
  }
}



