package controller;

import java.awt.image.BufferedImage;

import controller.commands.BlueScale;
import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.Downscale;
import controller.commands.FlipHorizontal;
import controller.commands.FlipVertical;
import controller.commands.GreenScale;
import controller.commands.GreyScale;
import controller.commands.IntensityScale;
import controller.commands.Load;
import controller.commands.LumaScale;
import controller.commands.RedScale;
import controller.commands.Save;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.ValueScale;
import model.ImageGallery;
import model.SingleImage;
import view.ImageView;
import view.ImageViewActions;
import view.ImageViewGUI;
import view.ImageViewImpl;

/**
 * Represents a GUI implementation of an ImageController.
 * Implements the {@link ImageViewActions} which acts as the mediator between the
 * view and the controller.
 * Performs commands on the image and calls on the model {@link ImageGallery} to do so.
 */
public class ImageControllerGUI implements ImageViewActions {
  private final ImageGallery gallery;
  private final ImageViewGUI view;
  private final ImageView viewAction;

  /**
   * Constructor for the ImageControllerGUI class.
   *
   * @param gallery the gallery that stores the images.
   * @param view    the GUI view.
   * @throws IllegalArgumentException if the arguments are null.
   */
  public ImageControllerGUI(ImageGallery gallery, ImageViewGUI view) {
    if (gallery == null || view == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.gallery = gallery;
    this.view = view;
    this.viewAction = new ImageViewImpl(gallery);
    this.view.setFeatures(this);
  }


  /**
   * Starts the program by giving control to the controller.
   *
   * @throws IllegalStateException if the controller is unable to perform a command.
   */
  @Override
  public void runProgram() throws IllegalStateException {
    this.view.displayGUI(true);
  }

  /**
   * Loads a given image from a file name through the ImageGallery.
   * Update the GUI to display accordingly.
   *
   * @param fileInput the file to read from.
   * @param fileName  the given name of the file.
   */
  @Override
  public void loadAction(String fileInput, String fileName) {
    Load l = new Load(fileInput, fileName, viewAction);
    l.runCommands(gallery);
    updateViewImage(fileName);
  }

  /**
   * Saves a given image to a file name through the ImageGallery.
   * Update the GUI to display accordingly.
   *
   * @param fileOutput the file to read from.
   * @param fileName   the given name of the file.
   */
  @Override
  public void saveAction(String fileOutput, String fileName) {
    Save s = new Save(fileOutput, fileName, viewAction);
    s.runCommands(gallery);
    updateViewImage(fileName);
  }


  /**
   * Brightens an image by a given factor. If the factor is positive, the image is brightened.
   * If the factor is negative, the image is darkened. Updates the view accordingly.
   *
   * @param constant the factor by which the image is brightened or darkened.
   * @param fileName the name of the image to be updated.
   * @param fileNew  the name of the new image.
   */
  @Override
  public void brightenAction(int constant, String fileName, String fileNew) {
    ImageCommands b = new Brighten(constant, fileName, fileNew, viewAction);
    b.runCommands(gallery);
    updateViewImage(fileNew);
  }

  @Override
  public void downscaleAction(int width, int height, String fileName, String fileNew) {
    ImageCommands b = new Downscale(width, height, fileName, fileNew, viewAction);
    b.runCommands(gallery);
    updateViewImage(fileNew);
  }

  /**
   * Applies the greyscale filter on an image using the luma matrix. Updates the view accordingly.
   *
   * @param fileName the name of the image to be updated.
   * @param fileNew  the name of the new image.
   */
  @Override
  public void greyScaleAction(String fileName, String fileNew) {
    ImageCommands g = new GreyScale(fileName, fileNew, viewAction);
    g.runCommands(gallery);
    updateViewImage(fileNew);
  }

  /**
   * Applies the sepia filter on an image using the sepia matrix. Updates the view accordingly.
   *
   * @param fileName the name of the image to be updated.
   * @param fileNew  the name of the new image.
   */
  @Override
  public void sepiaAction(String fileName, String fileNew) {
    ImageCommands s = new Sepia(fileName, fileNew, viewAction);
    s.runCommands(gallery);
    updateViewImage(fileNew);
  }

  /**
   * Applies the sharpening filter on an image. Updates the view accordingly.
   *
   * @param fileName the name of the image to be updated.
   * @param fileNew  the name of the new image.
   */
  @Override
  public void sharpenAction(String fileName, String fileNew) {
    ImageCommands s = new Sharpen(fileName, fileNew, viewAction);
    s.runCommands(gallery);
    updateViewImage(fileNew);
  }

  /**
   * Applies the blur filter on an image. Updates the view accordingly.
   *
   * @param fileName the name of the image to be updated.
   * @param fileNew  the name of the new image.
   */
  @Override
  public void blurAction(String fileName, String fileNew) {
    ImageCommands b = new Blur(fileName, fileNew, viewAction);
    b.runCommands(gallery);
    updateViewImage(fileNew);
  }

  /**
   * Applies the horizontal flip transformation on an image. Updates the view accordingly.
   *
   * @param fileName the name of the image to be updated.
   * @param fileNew  the name of the new image.
   */
  @Override
  public void horizontalFlipAction(String fileName, String fileNew) {
    ImageCommands f = new FlipHorizontal(fileName, fileNew, viewAction);
    f.runCommands(gallery);
    updateViewImage(fileNew);
  }

  /**
   * Applies the vertical flip transformation on an image. Updates the view accordingly.
   *
   * @param fileName the name of the image to be updated.
   * @param fileNew  the name of the new image.
   */
  @Override
  public void verticalFlipAction(String fileName, String fileNew) {
    ImageCommands v = new FlipVertical(fileName, fileNew, viewAction);
    v.runCommands(gallery);
    updateViewImage(fileNew);
  }

  /**
   * Applies the horizontal flip transformation on an image. Updates the view accordingly.
   *
   * @param fileName the name of the image to be updated.
   * @param fileNew  the name of the new image.
   * @param type     the type of the greyscale component to be applied.
   *                 Valid types are "red-component", "green-component", "blue-component",
   *                 "value-component", "luma-component", and "intensity-component".
   */
  @Override
  public void componentAction(String fileName, String fileNew, String type) {
    if (type.equalsIgnoreCase("red-component")) {
      new RedScale(fileName, fileNew, viewAction);
    }
    if (type.equalsIgnoreCase("green-component")) {
      new GreenScale(fileName, fileNew, viewAction);
    }
    if (type.equalsIgnoreCase("blue-component")) {
      new BlueScale(fileName, fileNew, viewAction);
    }
    if (type.equalsIgnoreCase("value-component")) {
      new ValueScale(fileName, fileNew, viewAction);
    }
    if (type.equalsIgnoreCase("luma-component")) {
      new LumaScale(fileName, fileNew, viewAction);
    }
    if (type.equalsIgnoreCase("intensity-component")) {
      new IntensityScale(fileName, fileNew, viewAction);
    }
    updateViewImage(fileNew);
  }

  /**
   * Updates the display image in the GUI based on the given file name.
   *
   * @param fileName the name of the image to be updated.
   */
  public void updateViewImage(String fileName) {
    this.view.updateImageName(fileName);
    this.view.updateImage(convertToBufferedImage(fileName));
  }

  /**
   * Converts a SingleImage to a BufferedImage given a file name.
   */
  protected BufferedImage convertToBufferedImage(String fileName) {
    SingleImage model = gallery.getImage(fileName);
    if (fileName.toLowerCase().contains("png")) {
      BufferedImage img = new BufferedImage(model.getWidth(), model.getHeight(),
              BufferedImage.TYPE_4BYTE_ABGR);
      ImageUtilsIO.setRGB(img, model);
      return img;
    } else {
      BufferedImage img = new BufferedImage(model.getWidth(), model.getHeight(),
              BufferedImage.TYPE_INT_RGB);
      ImageUtilsIO.setRGB(img, model);
      return img;
    }
  }
}
