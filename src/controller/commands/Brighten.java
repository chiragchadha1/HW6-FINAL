package controller.commands;

import controller.ImageCommands;
import model.ImageGallery;
import model.SingleImage;
import model.TransformationsImpl;
import view.ImageView;

/**
 * Brightens an image by a given factor. If the factor is positive, the image is brightened.
 * If the factor is negative, the image is darkened.
 */
public class Brighten implements ImageCommands {
  protected final String fileName;
  protected final String fileNew;
  protected String fileMask = "";
  protected final int constant;
  protected final ImageView view;

  /**
   * Constructor to assign the file name, new file name, and view to render messages.
   *
   * @param fileName the file to read from.
   * @param fileNew  the given name of the file.
   * @throws IllegalArgumentException if the file name or new file name is null.
   */
  public Brighten(int constant, String fileName, String fileNew, ImageView view)
          throws IllegalArgumentException {
    if ((fileName == null) || (fileNew == null) || (view == null)) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.constant = constant;
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
  public Brighten(int constant, String fileName, String fileMask, String fileNew, ImageView view)
          throws IllegalArgumentException {
    this(constant, fileName, fileNew, view);
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
    TransformationsImpl transform;
    if (fileMask.length() > 0) {
      transform = new TransformationsImpl(gallery.getImage(this.fileName),
              gallery.getImage(this.fileMask));
    } else {
      transform = new TransformationsImpl(gallery.getImage(this.fileName));
    }
    SingleImage newImage;
    newImage = transform.brighten(constant);
    gallery.put(this.fileNew, newImage);
    if (constant > 0) {
      this.view.renderMessage("Successfully brightened " + this.fileName + " by " + this.constant
              + " and saved as " + this.fileNew + System.lineSeparator());
    } else {
      this.view.renderMessage(("Successfully darkened " + this.fileName + " by " + this.constant
              + " and saved as " + this.fileNew + System.lineSeparator()));
    }
  }
}
