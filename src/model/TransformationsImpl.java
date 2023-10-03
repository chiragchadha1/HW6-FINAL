package model;

/**
 * Implements {@link Transformations} interface to handle the transformation operations.
 * Performs transformations on a {@link SingleImage} and returns an updated {@link SingleImage}.
 */
public class TransformationsImpl implements Transformations {
  protected final RGB[][] image;
  protected final int width;
  protected final int height;
  protected final int maxValue;
  protected boolean hasMask = false;
  protected RGB[][] maskImage = null;

  /**
   * Constructor to get the fields of the image.
   *
   * @param image the image to transform.
   */
  public TransformationsImpl(SingleImage image) {
    this.image = image.getPixels();
    this.width = image.getWidth();
    this.height = image.getHeight();
    this.maxValue = image.getMaxValue();
  }

  /**
   * Constructor to get the fields of the image and maskImage.
   *
   * @param srcImage  the image to transform.
   * @param maskImage the mask image to use as a mask.
   */
  public TransformationsImpl(SingleImage srcImage, SingleImage maskImage)
          throws IllegalArgumentException {
    this(srcImage);
    hasMask = true;
    this.maskImage = maskImage.getPixels();
    if (this.width != maskImage.getWidth() || this.height != maskImage.getHeight()) {
      throw new IllegalArgumentException("Mask image width or height does not match source image.");
    }
  }

  protected int clamp(int value) {
    return Math.max(0, Math.min(255, value));
  }

  /**
   * Transforms pixels with a given color matrix.
   *
   * @param color matrix to transform pixels with.
   * @return a new image with the transformed pixels.
   * @throws IllegalArgumentException if the given matrix is not size 9
   */
  private SingleImage colorTransformation(double[] color) throws IllegalArgumentException {
    if (color.length != 9) {
      throw new IllegalArgumentException("Color transformation matrix must be of size 9");
    }
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
        int red = (int) Math.round((r * color[0]) + (g * color[1]) + (b * color[2]));
        int green = (int) Math.round((r * color[3]) + (g * color[4]) + (b * color[5]));
        int blue = (int) Math.round((r * color[6]) + (g * color[7]) + (b * color[8]));
        newImage[i][j] = new RGBImpl(clamp(red), clamp(green), clamp(blue), image[i][j].getAlpha());
      }
    }
    return new SingleImageImpl(this.width, this.height, this.maxValue, newImage);
  }

  /**
   * Creates a greyscale image using the luma matrix.
   *
   * @return a greyscale image.
   */
  @Override
  public SingleImage greyScale() {
    double[] greyMatrix = new double[]{0.2126, 0.7152, 0.0722, 0.2126, 0.7152, 0.0722, 0.2126,
                                       0.7152, 0.0722};
    return colorTransformation(greyMatrix);
  }


  /**
   * Creates a sepia toned image using the sepia matrix.
   *
   * @return a sepia toned image.
   */
  @Override
  public SingleImage sepiaScale() {
    double[] sepiaMatrix = new double[]{0.393, 0.769, 0.189, 0.349, 0.686, 0.168, 0.272, 0.534,
                                        0.131};
    return colorTransformation(sepiaMatrix);
  }


  /**
   * Creates a greyscale image with a color-component of the image.
   *
   * @return a greyscale color-component image.
   */
  public SingleImage colorScale(String s) throws IllegalArgumentException {
    RGB[][] newImage = new RGB[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int red = image[i][j].getRed();
        int blue = image[i][j].getBlue();
        int green = image[i][j].getGreen();
        if (hasMask) {
          int maskRed = maskImage[i][j].getRed();
          int maskGreen = maskImage[i][j].getGreen();
          int maskBlue = maskImage[i][j].getBlue();
          // if mask color channels are non-zero, skip transformation.
          if (maskRed > 0 || maskGreen > 0 || maskBlue > 0) {
            newImage[i][j] = new RGBImpl(red, green, blue, image[i][j].getAlpha());
            continue;
          }
        }
        switch (s) {
          case "red":
            newImage[i][j] = new RGBImpl(red, red, red, image[i][j].getAlpha());
            break;
          case "blue":
            newImage[i][j] = new RGBImpl(blue, blue, blue, image[i][j].getAlpha());
            break;
          case "green":
            newImage[i][j] = new RGBImpl(green, green, green, image[i][j].getAlpha());
            break;
          default:
            throw new IllegalArgumentException("Invalid color-component.");
        }
      }
    }
    return new SingleImageImpl(this.width, this.height, this.maxValue, newImage);
  }

  /**
   * Creates a greyscale image with a value-component of the image.
   *
   * @return a greyscale value-component image.
   */
  public SingleImage valueScale() {
    RGB[][] newImage = new RGB[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int red = image[i][j].getRed();
        int blue = image[i][j].getBlue();
        int green = image[i][j].getGreen();
        if (hasMask) {
          int maskRed = maskImage[i][j].getRed();
          int maskGreen = maskImage[i][j].getGreen();
          int maskBlue = maskImage[i][j].getBlue();
          // if mask color channels are non-zero, skip transformation.
          if (maskRed > 0 || maskGreen > 0 || maskBlue > 0) {
            newImage[i][j] = new RGBImpl(red, green, blue, image[i][j].getAlpha());
            continue;
          }
        }
        int max = Math.max(red,
                (Math.max(green, blue)));
        newImage[i][j] = new RGBImpl(max, max, max, image[i][j].getAlpha());
      }
    }
    return new SingleImageImpl(this.width, this.height, this.maxValue, newImage);
  }

  /**
   * Creates a greyscale image with an intensity-component of the image.
   *
   * @return a greyscale intensity-component image.
   */
  public SingleImage intensityScale() {
    RGB[][] newImage = new RGB[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int red = image[i][j].getRed();
        int blue = image[i][j].getBlue();
        int green = image[i][j].getGreen();
        if (hasMask) {
          int maskRed = maskImage[i][j].getRed();
          int maskGreen = maskImage[i][j].getGreen();
          int maskBlue = maskImage[i][j].getBlue();
          // if mask color channels are non-zero, skip transformation.
          if (maskRed > 0 || maskGreen > 0 || maskBlue > 0) {
            newImage[i][j] = new RGBImpl(red, green, blue, image[i][j].getAlpha());
            continue;
          }
        }
        int average = (red + green + blue) / 3;
        newImage[i][j] = new RGBImpl(average, average, average, image[i][j].getAlpha());
      }
    }
    return new SingleImageImpl(this.width, this.height, this.maxValue, newImage);
  }

  /**
   * Creates a greyscale image with a luma-component of the image.
   *
   * @return a greyscale luma-component image.
   */
  public SingleImage lumaScale() {
    RGB[][] newImage = new RGB[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int red = image[i][j].getRed();
        int blue = image[i][j].getBlue();
        int green = image[i][j].getGreen();
        if (hasMask) {
          int maskRed = maskImage[i][j].getRed();
          int maskGreen = maskImage[i][j].getGreen();
          int maskBlue = maskImage[i][j].getBlue();
          // if mask color channels are non-zero, skip transformation.
          if (maskRed > 0 || maskGreen > 0 || maskBlue > 0) {
            newImage[i][j] = new RGBImpl(red, green, blue, image[i][j].getAlpha());
            continue;
          }
        }
        int luma = (int) Math.round((0.2126 * red)
                + (0.7152 * green)
                + (0.0722 * blue));
        newImage[i][j] = new RGBImpl(clamp(luma), clamp(luma), clamp(luma), image[i][j].getAlpha());
      }
    }
    return new SingleImageImpl(this.width, this.height, this.maxValue, newImage);
  }

  /**
   * Flips an image horizontally.
   *
   * @return a horizontally flipped image.
   */
  public SingleImage flipHorizontal() {
    RGB[][] newImage = new RGB[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int red = image[i][width - j - 1].getRed();
        int green = image[i][width - j - 1].getGreen();
        int blue = image[i][width - j - 1].getBlue();
        newImage[i][j] = new RGBImpl(red, green, blue, image[i][j].getAlpha());
      }
    }
    return new SingleImageImpl(this.width, this.height, this.maxValue, newImage);
  }

  /**
   * Flips an image vertically.
   *
   * @return a vertically flipped image.
   */
  public SingleImage flipVertical() {
    RGB[][] newImage = new RGB[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int red = image[height - i - 1][j].getRed();
        int green = image[height - i - 1][j].getGreen();
        int blue = image[height - i - 1][j].getBlue();
        newImage[i][j] = new RGBImpl(red, green, blue, image[i][j].getAlpha());
      }
    }
    return new SingleImageImpl(this.width, this.height, this.maxValue, newImage);
  }

  /**
   * Brightens an image by a given factor. If the factor is positive, the image is brightened.
   * If the factor is negative, the image is darkened.
   *
   * @param constant the factor by which the image is brightened or darkened.
   * @return a brightened or darkened image.
   * @throws IllegalArgumentException if the constant is 0.
   */
  public SingleImage brighten(int constant) throws IllegalArgumentException {
    RGB[][] newImage = new RGB[this.height][this.width];
    if (constant == 0) {
      throw new IllegalArgumentException("Brighten command requires a non-zero constant.");
    }
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int red = image[i][j].getRed();
        int blue = image[i][j].getBlue();
        int green = image[i][j].getGreen();
        if (hasMask) {
          int maskRed = maskImage[i][j].getRed();
          int maskGreen = maskImage[i][j].getGreen();
          int maskBlue = maskImage[i][j].getBlue();
          // if mask color channels are non-zero, skip transformation.
          if (maskRed > 0 || maskGreen > 0 || maskBlue > 0) {
            newImage[i][j] = new RGBImpl(red, green, blue, image[i][j].getAlpha());
            continue;
          }
        }
        newImage[i][j] = new RGBImpl(clamp(red + constant), clamp(green + constant),
                clamp(blue + constant), image[i][j].getAlpha());
      }
    }
    return new SingleImageImpl(this.width, this.height, this.maxValue, newImage);
  }

  /**
   * Downscales an image to a specified width and height.
   *
   * @param newWidth  the width of the downscaled image.
   * @param newHeight the height of the downscaled image.
   */
  @Override
  public SingleImage downscale(int newWidth, int newHeight) throws IllegalArgumentException {
    RGB[][] newImage = new RGB[newHeight][newWidth];
    float xRatio = (1.0f * this.width) / newWidth;
    float yRatio = (1.0f * this.height) / newHeight;

    if ((xRatio < 1.0f) || (yRatio < 1.0f)) {
      throw new IllegalArgumentException("provided width or height is greater than original width"
              + "or height");
    }

    for (int i = 0; i < newHeight; i++) {
      for (int j = 0; j < newWidth; j++) {
        int x = Math.round(j * xRatio);
        int y = Math.round(i * yRatio);
        int red = image[y][x].getRed();
        int green = image[y][x].getGreen();
        int blue = image[y][x].getBlue();
        int alpha = image[y][x].getAlpha();
        newImage[i][j] = new RGBImpl(clamp(red), clamp(green), clamp(blue), clamp(alpha));
      }
    }
    return new SingleImageImpl(newWidth, newHeight, this.maxValue, newImage);
  }
}
