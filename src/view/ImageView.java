package view;

/**
 * View for the {@link model.ImageGallery}.
 * Provides the ability to render messages to an appendable.
 */
public interface ImageView {

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   */
  void renderMessage(String message);
}
