package viewtest;

import view.ImageView;

/**
 * Mock class for ImageView.
 */
public class RenderMessageMock implements ImageView {

  /**
   * Constructor for the text view that takes in an Appendable.
   *
   * @param ap Represents an appendable object.
   * @throws IllegalArgumentException if the model is null.
   */
  public RenderMessageMock(Appendable ap) throws IllegalArgumentException {
    if (ap == null) {
      throw new IllegalArgumentException("Model or Appendable cannot be null.");
    }
  }


  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   */
  public void renderMessage(String message) {
    throw new IllegalArgumentException("invalid renderMessage");
  }
}