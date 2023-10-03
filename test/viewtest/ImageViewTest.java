package viewtest;

import org.junit.Test;

import java.io.StringReader;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageGallery;
import view.ImageView;

import static org.junit.Assert.assertEquals;

/**
 * Test class for image view makes sure it throws an error for render message.
 */
public class ImageViewTest {

  @Test(expected = IllegalStateException.class)
  public void testRenderMessageIOException() {
    try {
      Readable input = new StringReader("load res/personal.ppm p");
      StringBuilder output = new StringBuilder();

      ImageGallery gallery = new GalleryImplMock(output);
      ImageView view = new RenderMessageMock(output);
      ImageController controller = new ImageControllerImpl(gallery, view, input);
      controller.runProgram();
    } catch (IllegalStateException e) {
      assertEquals("invalid renderMessage", e.getMessage());
      throw e;
    }
  }

}