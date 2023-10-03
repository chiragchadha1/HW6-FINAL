package model;

/**
 * Represents color filter operations utilizing that can be applied on images.
 * Utilizes a kernel of numbers represented in a matrix format to do so.
 */
public class Filter extends TransformationsImpl {

  static final double[][] sharpen = new double[][]{
          {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0},
          {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
          {-1.0 / 8.0, 1.0 / 4.0, 1.0, 1.0 / 4.0, -1.0 / 8.0},
          {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
          {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0}};

  static final double[][] blur = new double[][]{
          {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
          {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0},
          {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0}};

  /**
   * Constructor to get the fields of the image.
   *
   * @param image the image to transform.
   */
  public Filter(SingleImage image) {
    super(image);
  }

  /**
   * Constructor to get the fields of the image.
   *
   * @param srcImage  original image.
   * @param maskImage mask image.
   */
  public Filter(SingleImage srcImage, SingleImage maskImage) {
    super(srcImage, maskImage);
  }

  /**
   * Processes pixels with the given kernal.
   *
   * @return a new kernal RGB value.
   */
  private RGB filterPixels(int x, int y, double[][] kernal) {
    int red = 0;
    int green = 0;
    int blue = 0;
    int alpha = 0;
    for (int i = 0; i < kernal.length; i++) {
      for (int j = 0; j < kernal[0].length; j++) {
        int kernalX = x - kernal.length / 2 + i;
        int kernalY = y - kernal[0].length / 2 + j;
        if (kernalX >= 0 && kernalX < this.height && kernalY >= 0 && kernalY < this.width) {
          int r = this.image[kernalX][kernalY].getRed();
          int g = this.image[kernalX][kernalY].getGreen();
          int b = this.image[kernalX][kernalY].getBlue();
          int a = this.image[kernalX][kernalY].getAlpha();
          red += (int) Math.round((r * kernal[i][j]));
          green += (int) Math.round((g * kernal[i][j]));
          blue += (int) Math.round((b * kernal[i][j]));
          alpha += (int) Math.round((a * kernal[i][j]));
        }
      }
    }
    return new RGBImpl(clamp(red), clamp(green), clamp(blue), clamp(alpha));
  }

  /**
   * Applies the given kernal to the image.
   *
   * @param filter the kernal to apply.
   * @return a new image with the kernal applied.
   */
  private SingleImage constructFilter(double[][] filter) {
    RGB[][] newImage = new RGB[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int r = image[i][j].getRed();
        int g = image[i][j].getGreen();
        int b = image[i][j].getBlue();
        if (hasMask) {
          int maskRed = maskImage[i][j].getRed();
          int maskGreen = maskImage[i][j].getGreen();
          int maskBlue = maskImage[i][j].getBlue();
          // if mask color channels are non-zero, skip transformation.
          if (maskRed > 0 || maskGreen > 0 || maskBlue > 0) {
            newImage[i][j] = new RGBImpl(r, g, b, image[i][j].getAlpha());
            continue;
          }
        }
        newImage[i][j] = filterPixels(i, j, filter);
      }
    }
    return new SingleImageImpl(this.width, this.height, this.maxValue, newImage);
  }

  /**
   * Sharpens an image.
   *
   * @return a sharpened single image.
   */
  public SingleImage sharpen() {
    return constructFilter(sharpen);
  }

  /**
   * Blurs an image.
   *
   * @return a blurred single image.
   */
  public SingleImage blur() {
    return constructFilter(blur);
  }
}
