package model;

/**
 * Represents an RGB color as red, blue, and green.
 */
public interface RGB {
  /**
   * Returns the red value in RGB.
   *
   * @return the red value of a pixel.
   */
  int getRed();

  /**
   * Returns the blue value in RGB.
   *
   * @return the blue value of a pixel.
   */
  int getBlue();

  /**
   * Returns the green value in RGB.
   *
   * @return the green value of a pixel.
   */
  int getGreen();

  /**
   * Returns the alpha value in RGB.
   *
   * @return the alpha value of a pixel.
   */
  int getAlpha();
}
