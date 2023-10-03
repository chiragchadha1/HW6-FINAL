package controller.commands;

import controller.ImageCommands;
import model.ImageGallery;
import model.SingleImage;
import model.TransformationsImpl;
import view.ImageView;

/**
 * Downscales an image to a specified width and height.
 */
public class Downscale implements ImageCommands {
  protected final String fileName;
  protected final String fileNew;
  protected final int width;
  protected final int height;
  protected final ImageView view;

  /**
   * Constructor to assign the file name, new file name, and view to render messages.
   *
   * @param fileName the file to read from.
   * @param fileNew  the given name of the file.
   * @throws IllegalArgumentException if the file name or new file name is null.
   */
  public Downscale(int width, int height, String fileName, String fileNew, ImageView view)
          throws IllegalArgumentException {
    if ((fileName == null) || (fileNew == null) || (view == null)) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.width = width;
    this.height = height;
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
    SingleImage newImage;
    newImage = transform.downscale(width, height);
    gallery.put(this.fileNew, newImage);
    this.view.renderMessage("Successfully downscaled " + this.fileName + " by " + width + ", "
            + height + " and saved as " + this.fileNew + System.lineSeparator());
  }
}
