package view;

import java.io.IOException;

import model.ImageGallery;

/**
 * View implementation of {@link ImageView}.
 * Provides the ability to render messages to an appendable.
 */
public class ImageViewImpl implements ImageView {
  private final Appendable ap;

  /**
   * Constructor for the text view that takes in an Appendable.
   *
   * @param gallery Represents a gallery of images
   * @param ap      Represents an appendable object.
   * @throws IllegalArgumentException if the model or appendable is null.
   */
  public ImageViewImpl(ImageGallery gallery, Appendable ap) throws IllegalArgumentException {
    if (gallery == null || ap == null) {
      throw new IllegalArgumentException("Model or Appendable cannot be null.");
    }
    this.ap = ap;
  }

  /**
   * Constructor for the text view.
   *
   * @param gallery Represents a gallery of images
   * @throws IllegalArgumentException if the model or appendable is null.
   */
  public ImageViewImpl(ImageGallery gallery) throws IllegalArgumentException {
    this(gallery, System.out);
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderMessage(String message) {
    try {
      this.ap.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("Error rendering message.");
    }
  }
}
