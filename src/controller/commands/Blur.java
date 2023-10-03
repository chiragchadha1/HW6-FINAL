package controller.commands;

import controller.ImageCommands;
import model.Filter;
import model.ImageGallery;
import model.SingleImage;
import view.ImageView;

/**
 * Creates a blurred image.
 */
public class Blur implements ImageCommands {
  protected final String fileName;
  protected final String fileNew;
  protected String fileMask = "";
  protected final ImageView view;

  /**
   * Constructor to assign the file name, new file name, and view to render messages.
   *
   * @param fileName the file to read from.
   * @param fileNew  the given name of the file.
   * @param view     the view to display the image (used for rendering messages).
   * @throws IllegalArgumentException if the file name or new file name is null.
   */
  public Blur(String fileName, String fileNew, ImageView view)
          throws IllegalArgumentException {
    if ((fileName == null) || (fileNew == null) || (view == null)) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.fileName = fileName;
    this.fileNew = fileNew;
    this.view = view;
  }

  /**
   * Constructor to assign the file name, mask name, new file name, and view to render messages.
   *
   * @param fileName the file to read from.
   * @param fileMask the mask file.
   * @param fileNew  the given name of the file.
   * @param view     the view to display the image (used for rendering messages).
   * @throws IllegalArgumentException if the file name or new file name is null.
   */
  public Blur(String fileName, String fileMask, String fileNew, ImageView view)
          throws IllegalArgumentException {
    this(fileName, fileNew, view);
    if (fileMask == null) {
      throw new IllegalArgumentException("fileMask cannot be null");
    }
    this.fileMask = fileMask;
  }

  /**
   * Performs the command on the given image.
   *
   * @param gallery the gallery that stores the images.
   */
  @Override
  public void runCommands(ImageGallery gallery) {
    Filter transform;
    if (fileMask.length() > 0) {
      transform = new Filter(gallery.getImage(this.fileName),
              gallery.getImage(this.fileMask));
    } else {
      transform = new Filter(gallery.getImage(this.fileName));
    }
    SingleImage newImage = transform.blur();
    gallery.put(this.fileNew, newImage);
    this.view.renderMessage("Successfully blurred " + this.fileName
            + " and saved as " + this.fileNew + System.lineSeparator());
  }
}
