package controller.commands;

import controller.ImageCommands;
import model.ImageGallery;
import model.SingleImage;
import model.TransformationsImpl;
import view.ImageView;

/**
 * Flips an image vertically.
 */
public class FlipVertical implements ImageCommands {
  protected final String fileName;
  protected final String fileNew;
  protected final ImageView view;

  /**
   * Constructor to assign the file name, new file name, and view to render messages.
   *
   * @param fileName the file to read from.
   * @param fileNew  the given name of the file.
   * @param view     the view to display the image (used for rendering messages).
   * @throws IllegalArgumentException if the file name or new file name is null.
   */
  public FlipVertical(String fileName, String fileNew, ImageView view)
          throws IllegalArgumentException {
    if ((fileName == null) || (fileNew == null) || (view == null)) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.fileName = fileName;
    this.fileNew = fileNew;
    this.view = view;
  }

  /**
   * Performs the command on the given image.
   *
   * @param gallery the gallery that stores the images.
   */
  @Override
  public void runCommands(ImageGallery gallery) {
    TransformationsImpl transform = new TransformationsImpl(gallery.getImage(this.fileName));
    SingleImage newImage = transform.flipVertical();
    gallery.put(this.fileNew, newImage);
    this.view.renderMessage("Successfully flipped " + this.fileName
            + " vertically and saved as " + this.fileNew + System.lineSeparator());

  }

}
