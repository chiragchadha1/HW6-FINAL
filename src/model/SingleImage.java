package model;

/**
 * Represents a single image.
 * Contains getter methods for the image's height, width, and max value.
 */
public interface SingleImage {

  /**
   * Returns a copy of an image's pixels.
   *
   * @return a 2D RGB array of pixels.
   */
  RGB[][] getPixels();

  /**
   * Returns an image's pixel at a given width and height.
   *
   * @param row the row of the pixel
   * @param col the column of the pixel
   * @return a pixel.
   */
  String getPixel(int row, int col);

  /**
   * Returns the width of the image.
   *
   * @return the width of the image.
   */
  int getWidth();

  /**
   * Returns the height of the image.
   *
   * @return the height of the image.
   */
  int getHeight();

  /**
   * Returns the maximum value of a color in the image.
   *
   * @return the maximum value of a color in the image.
   */
  int getMaxValue();
}
