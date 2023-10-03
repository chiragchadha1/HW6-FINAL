package viewtest;

import java.util.Map;

import model.ImageGallery;
import model.SingleImage;

/**
 * Mock implementation of {@link ImageGallery}.
 */
public class GalleryImplMock implements ImageGallery {
  private final Appendable output;

  /**
   * Constructor for SingleImageImplMock.
   *
   * @param output takes in an output to append the controller outputs too.
   */
  public GalleryImplMock(Appendable output) {
    this.output = output;
  }

  /**
   * Puts the given image, represented by its file name, into the gallery represented as a HashMap.
   *
   * @param fileName represents the name of the image.
   * @param model    represents the model of the image.
   */
  @Override
  public void put(String fileName, SingleImage model) {
    output.toString();
  }

  /**
   * Returns the image model stored in the gallery.
   *
   * @param fileName represents the name of the image.
   * @return the image model stored.
   */
  @Override
  public SingleImage getImage(String fileName) {
    return null;
  }

  /**
   * Returns the gallery of images.
   *
   * @return the gallery of images.
   */
  @Override
  public Map<String, SingleImage> getGallery() {
    return null;
  }
}
