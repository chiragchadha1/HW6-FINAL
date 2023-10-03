package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.ImageGallery;
import model.RGB;
import model.RGBImpl;
import model.SingleImage;
import model.SingleImageImpl;
import view.ImageView;

/**
 * Utility class to load and save images of any type using ImageIO.
 */
public class ImageUtilsIO implements ImageUtils {
  private final ImageView view;

  /**
   * Constructor for the ImageUtilsImpl class.
   *
   * @param view the view that displays the images, used displaying error messages.
   * @throws IllegalArgumentException if the argument is null.
   */
  public ImageUtilsIO(ImageView view) throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("Argument cannot be null");
    }
    this.view = view;
  }

  /**
   * Loads a given image from a file name through the ImageGallery.
   *
   * @param fileName the name of the file to read from.
   * @return the image that was read from the file.
   */
  public SingleImage loadImage(String fileName) {
    BufferedImage img = null;

    try {
      img = ImageIO.read(new FileInputStream(fileName));
    } catch (IOException e) {
      this.view.renderMessage(e.getMessage() + System.lineSeparator());
    }

    int width = img.getWidth();
    int height = img.getHeight();

    RGB[][] image = new RGB[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int rgb = img.getRGB(j, i);
        int r = rgb >> 16 & 0xFF;
        int g = rgb >> 8 & 0xFF;
        int b = rgb & 0xFF;
        int a = rgb >> 24 & 0xFF;
        image[i][j] = new RGBImpl(r, g, b, a);
      }
    }

    return new SingleImageImpl(width, height, 255, image);
  }

  /**
   * Writes a given image to a file name through the ImageGallery.
   *
   * @param fileName   the name of the file to write to.
   * @param fileOutput the output to write to the image.
   * @param gallery    the gallery that stores the images.
   */
  public void saveImage(String fileName, String fileOutput, ImageGallery gallery) {
    SingleImage model = gallery.getImage(fileName);

    try {
      if (fileOutput.toLowerCase().contains("png")) {
        BufferedImage img = new BufferedImage(model.getWidth(), model.getHeight(),
                BufferedImage.TYPE_4BYTE_ABGR);
        File outputFile = new File(fileOutput);
        setRGB(img, model);
        ImageIO.write(img, "png", outputFile);
      } else {
        BufferedImage img = new BufferedImage(model.getWidth(), model.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        File outputFile = new File(fileOutput);
        setRGB(img, model);
        ImageIO.write(img, fileOutput.substring(
                fileOutput.lastIndexOf(".") + 1), outputFile);
      }
    } catch (Exception e) {
      this.view.renderMessage(fileOutput + " does not exist" + System.lineSeparator());
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  // Sets a 2D array of RGB values to a BufferedImage.
  protected static void setRGB(BufferedImage img, SingleImage model) {
    RGB[][] pixels = model.getPixels();

    int width = img.getWidth();
    int height = img.getHeight();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = pixels[i][j].getRed();
        int g = pixels[i][j].getGreen();
        int b = pixels[i][j].getBlue();
        int a = pixels[i][j].getAlpha();
        Color color = new Color(r, g, b, a);
        img.setRGB(j, i, color.getRGB());
      }
    }
  }
}