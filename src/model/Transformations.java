package model;

/**
 * Represents transformation operations used by the controller in the runProgram method.
 * The transformation operations are performed on given {@link SingleImage} and
 * the image is updated with a new {@link SingleImage} that is returned.
 */
public interface Transformations {

  /**
   * Creates a greyscale image using the luma matrix.
   *
   * @return a greyscale image.
   */
  SingleImage greyScale();

  /**
   * Creates a sepia toned image using the sepia matrix.
   *
   * @return a sepia toned image.
   */
  SingleImage sepiaScale();

  /**
   * Creates a greyscale image with a color-component of the image.
   *
   * @return a greyscale color-component image.
   */
  SingleImage colorScale(String s) throws IllegalArgumentException;

  /**
   * Creates a greyscale image with a value-component of the image.
   *
   * @return a greyscale value-component image.
   */
  SingleImage valueScale();

  /**
   * Creates a greyscale image with an intensity-component of the image.
   *
   * @return a greyscale intensity-component image.
   */
  SingleImage intensityScale();

  /**
   * Creates a greyscale image with a luma-component of the image.
   *
   * @return a greyscale luma-component image.
   */
  SingleImage lumaScale();

  /**
   * Flips an image horizontally.
   *
   * @return a horizontally flipped image.
   */
  SingleImage flipHorizontal();

  /**
   * Flips an image vertically.
   *
   * @return a vertically flipped image.
   */
  SingleImage flipVertical();

  /**
   * Brightens an image by a given factor. If the factor is positive, the image is brightened.
   * If the factor is negative, the image is darkened.
   *
   * @param constant the factor by which the image is brightened or darkened.
   * @return a brightened or darkened image.
   * @throws IllegalArgumentException if the constant is 0.
   */
  SingleImage brighten(int constant) throws IllegalArgumentException;

  /**
   * Downscales an image to a specified width and height.
   *
   * @param newWidth  the width of the downscaled image.
   * @param newHeight the height of the downscaled image.
   */
  SingleImage downscale(int newWidth, int newHeight);
}
