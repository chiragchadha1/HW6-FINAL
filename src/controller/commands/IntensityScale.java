package controller.commands;

import controller.ImageCommands;
import model.ImageGallery;
import model.SingleImage;
import model.TransformationsImpl;
import view.ImageView;

/**
 * Creates a greyscale image with an intensity-component of the image.
 */
public class IntensityScale implements ImageCommands {
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
  public IntensityScale(String fileName, String fileNew, ImageView view)
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
  public IntensityScale(String fileName, String fileMask, String fileNew, ImageView view)
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
    TransformationsImpl transform;
    if (fileMask.length() > 0) {
      transform = new TransformationsImpl(gallery.getImage(this.fileName),
              gallery.getImage(this.fileMask));
    } else {
      transform = new TransformationsImpl(gallery.getImage(this.fileName));
    }
    SingleImage newImage = transform.intensityScale();
    gallery.put(this.fileNew, newImage);
    this.view.renderMessage("Successfully changed " + this.fileName
            + " to value-component grayscale and saved as " + this.fileNew
            + System.lineSeparator());
  }

}
