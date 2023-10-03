package model;

/**
 * Implements the {@link RGB} interface.
 * Represents individual pixels as red, green, or blue.
 * Used in representing the model as a 2D array of pixels.
 */
public class RGBImpl implements RGB {
  private final int red;
  private final int green;
  private final int blue;
  private final int alpha;

  /**
   * Constructs a new RGB color.
   *
   * @param red   the red value of the pixel.
   * @param green the green value of the pixel.
   * @param blue  the blue value of the pixel.
   * @throws IllegalArgumentException if the red, green, or blue values are not between 0 and 255.
   */
  public RGBImpl(int red, int green, int blue, int alpha) throws IllegalArgumentException {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255
            || alpha < 0 || alpha > 255) {
      throw new IllegalArgumentException("Invalid RGB values!");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
  }

  public RGBImpl(int red, int green, int blue) {
    this(red, green, blue, 0);
  }

  /**
   * Returns the red value in RGB.
   *
   * @return the red value of a pixel.
   */
  @Override
  public int getRed() {
    return this.red;
  }

  /**
   * Returns the blue value in RGB.
   *
   * @return the blue value of a pixel.
   */
  @Override
  public int getBlue() {
    return this.blue;
  }

  /**
   * Returns the green value in RGB.
   *
   * @return the green value of a pixel.
   */
  @Override
  public int getGreen() {
    return this.green;
  }

  /**
   * Returns the alpha value in RGB.
   *
   * @return the alpha value of a pixel.
   */
  @Override
  public int getAlpha() {
    return this.alpha;
  }
}
