package controller;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import model.ImageGallery;
import model.RGB;
import model.RGBImpl;
import model.SingleImage;
import model.SingleImageImpl;
import view.ImageView;

/**
 * Utility class to load and save PPM images.
 */
public class ImageUtilsPPM implements ImageUtils {
  private final ImageView view;

  /**
   * Constructor for the ImageUtilsImpl class.
   *
   * @param view the view that displays the images, used displaying error messages.
   * @throws IllegalArgumentException if the argument is null.
   */
  public ImageUtilsPPM(ImageView view) throws IllegalArgumentException {
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
    Scanner sc = null;

    try {
      sc = new Scanner(new FileInputStream(fileName));
    } catch (FileNotFoundException e) {
      this.view.renderMessage("File " + fileName + " not found" + System.lineSeparator());
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      this.view.renderMessage("Invalid PPM file: plain RAW file should begin with P3"
              + System.lineSeparator());
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    RGB[][] image = new RGB[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        image[i][j] = new RGBImpl(r, g, b);
      }
    }

    return new SingleImageImpl(width, height, maxValue, image);
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
      BufferedWriter writer =
              new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOutput)));
      writer.write("P3" + System.lineSeparator() + (model.getWidth() + " " + model.getHeight()
              + System.lineSeparator()) + model.getMaxValue() + System.lineSeparator());

      for (int i = 0; i < model.getHeight(); i++) {
        for (int j = 0; j < model.getWidth(); j++) {
          writer.write(model.getPixel(i, j) + System.lineSeparator());
        }
      }
      writer.flush();
      writer.close();
    } catch (Exception e) {
      this.view.renderMessage(fileOutput + " does not exist" + System.lineSeparator());
      throw new IllegalArgumentException(e.getMessage());
    }
  }
}
