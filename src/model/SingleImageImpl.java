package model;

/**
 * Implementation of the {@link SingleImage} interface.
 * An image is consisted of an {@link RGB} pixels, width, height, and max value.
 */
public class SingleImageImpl implements SingleImage {
  private final RGB[][] pixel;
  private final int width;
  private final int height;
  private final int maxValue;

  /**
   * Constructs a new Single Image with the given height, width, and max value.
   *
   * @param width    the width of the image.
   * @param height   the height of the image.
   * @param maxValue the max value of the image.
   * @param pixel    the pixels of the image.
   * @throws IllegalArgumentException if the width or height is negative or if the pixel is null.
   */
  public SingleImageImpl(int width, int height, int maxValue, RGB[][] pixel)
          throws IllegalArgumentException {
    if (width < 0 || height < 0 || maxValue < 0 || pixel == null || pixel.length > height
            || pixel.length < height || pixel[0].length > width || pixel[0].length < width
            || maxValue > 255) {
      throw new IllegalArgumentException("Invalid image dimensions");
    }
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
    this.pixel = pixel;
  }

  /**
   * Returns a copy of an image's pixels.
   *
   * @return a 2D RGB array of pixels.
   */
  @Override
  public RGB[][] getPixels() {
    return this.pixel;
  }

  /**
   * Returns an image's pixel at a given width and height.
   *
   * @param row the row of the pixel
   * @param col the column of the pixel
   * @return a pixel.
   */
  @Override
  public String getPixel(int row, int col) {
    int red = pixel[row][col].getRed();
    int blue = pixel[row][col].getBlue();
    int green = pixel[row][col].getGreen();
    return red + " " + green + " " + blue
            + " ";
  }

  /**
   * Returns the width of the image.
   *
   * @return the width of the image.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Returns the height of the image.
   *
   * @return the height of the image.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Returns the maximum value of a color in the image.
   *
   * @return the maximum value of a color in the image.
   */
  @Override
  public int getMaxValue() {
    return this.maxValue;
  }


}
