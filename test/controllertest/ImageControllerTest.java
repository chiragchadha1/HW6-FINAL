package controllertest;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageGallery;
import model.ImageGalleryImpl;
import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test class for {@link ImageController}.
 */
public class ImageControllerTest {

  @Test(expected = IllegalArgumentException.class)
  public void testModelControllerNull() {
    Readable in = new StringReader("q");
    Appendable out = new StringBuilder();

    ImageGallery model = new ImageGalleryImpl();
    ImageView view = new ImageViewImpl(model, out);
    ImageController controller = new ImageControllerImpl(null, view, in);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testViewControllerNull() {
    Readable in = new StringReader("q");
    Appendable out = new StringBuilder();

    ImageGallery model = new ImageGalleryImpl();
    ImageView view = new ImageViewImpl(model, out);
    ImageController controller = new ImageControllerImpl(model, null, in);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInputNull() {
    Readable in = new StringReader("q");
    Appendable out = new StringBuilder();

    ImageGallery model = new ImageGalleryImpl();
    ImageView view = new ImageViewImpl(model, out);
    ImageController controller = new ImageControllerImpl(model, view, null);
  }

  @Test
  public void testLoadImage() {
    Readable in = new StringReader("load img/Koala.ppm koala");
    Appendable out = new StringBuilder();

    ImageGallery model = new ImageGalleryImpl();
    ImageView view = new ImageViewImpl(model, out);
    ImageController controller = new ImageControllerImpl(model, view, in);
    controller.runProgram();
    assertEquals(out.toString(), "Successfully loaded koala from img/Koala.ppm"
            + System.lineSeparator());
    assertNotNull(model.getImage("koala"));
  }

  @Test
  public void testExportImage() {
    Readable in = new StringReader("load img/Koala.ppm koala" + System.lineSeparator()
            + "save img/koala-test.ppm koala");
    Appendable out = new StringBuilder();

    ImageGallery model = new ImageGalleryImpl();
    ImageView view = new ImageViewImpl(model, out);
    ImageController controller = new ImageControllerImpl(model, view, in);
    controller.runProgram();

    File testFile = new File("img/koala-test.ppm");
    assertTrue(testFile.exists());
    assertEquals(out.toString(), "Successfully loaded koala from img/Koala.ppm"
            + System.lineSeparator() + "Successfully saved koala to img/koala-test.ppm"
            + System.lineSeparator());
  }

  @Test
  public void testInvalidLoad() {
    Readable in = new StringReader("load img/Koalaaa.ppm koala");
    Appendable out = new StringBuilder();

    ImageGallery model = new ImageGalleryImpl();
    ImageView view = new ImageViewImpl(model, out);
    ImageController controller = new ImageControllerImpl(model, view, in);
    controller.runProgram();
    assertEquals(out.toString(), "File img/Koalaaa.ppm not found"
            + System.lineSeparator() + "Invalid command. Please try again."
            + System.lineSeparator());
    assertEquals(model.getImage("koala"), model.getImage("koala-test.ppm"));
  }

  @Test
  public void testInvalidSave() {
    Readable in = new StringReader("load img/Koala.ppm koala" + System.lineSeparator()
            + "save img/new/koala-error.ppm koala");
    Appendable out = new StringBuilder();

    ImageGallery model = new ImageGalleryImpl();
    ImageView view = new ImageViewImpl(model, out);
    ImageController controller = new ImageControllerImpl(model, view, in);
    controller.runProgram();
    assertEquals(out.toString(), "Successfully loaded koala from img/Koala.ppm"
            + System.lineSeparator() + "img/new/koala-error.ppm does not exist"
            + System.lineSeparator() + "img/new/koala-error.ppm (No such file or directory)"
            + System.lineSeparator() + "Invalid command. Please try again."
            + System.lineSeparator());
  }


  @Test
  public void testValidAndInvalidCommandSequence() {
    Readable in = new StringReader("load img/transparent.png k" + System.lineSeparator()
            + "vertical-flip k kv" + System.lineSeparator() + "brighten 55 kv kb"
            + System.lineSeparator() + "intensity-component kbbb ki" + System.lineSeparator()
            + "blur kb kl" + System.lineSeparator() + "sepia kl ks" + System.lineSeparator()
            + "save img/transparent-test.png ks" + System.lineSeparator());
    Appendable out = new StringBuilder();

    ImageGallery model = new ImageGalleryImpl();
    ImageView view = new ImageViewImpl(model, out);
    ImageController controller = new ImageControllerImpl(model, view, in);
    controller.runProgram();
    assertEquals(out.toString(), "Successfully loaded k from img/transparent.png"
            + System.lineSeparator() + "Successfully flipped k vertically and saved as kv"
            + System.lineSeparator() + "Successfully brightened kv by 55 and saved as kb"
            + System.lineSeparator() + "Invalid command. Please try again."
            + System.lineSeparator() + "Successfully blurred kb and saved as kl"
            + System.lineSeparator() + "Successfully sepia toned kl and saved as ks"
            + System.lineSeparator() + "Successfully saved ks to img/transparent-test.png"
            + System.lineSeparator());
  }

  @Test
  public void testMultiExportImage() {
    Readable in = new StringReader("load img/Koala.ppm koala" + System.lineSeparator()
            + "save img/koala-test.ppm koala" + System.lineSeparator()
            + "save img/koala-test.jpg koala" + System.lineSeparator()
            + "save img/koala-test.png koala" + System.lineSeparator()
            + "save img/koala-test.bmp koala");
    Appendable out = new StringBuilder();

    ImageGallery model = new ImageGalleryImpl();
    ImageView view = new ImageViewImpl(model, out);
    ImageController controller = new ImageControllerImpl(model, view, in);
    controller.runProgram();

    File testFile = new File("img/koala-test.ppm");
    assertTrue(testFile.exists());
    assertEquals(out.toString(), "Successfully loaded koala from img/Koala.ppm"
            + System.lineSeparator() + "Successfully saved koala to img/koala-test.ppm"
            + System.lineSeparator() + "Successfully saved koala to img/koala-test.jpg"
            + System.lineSeparator() + "Successfully saved koala to img/koala-test.png"
            + System.lineSeparator() + "Successfully saved koala to img/koala-test.bmp"
            + System.lineSeparator());
  }

  @Test
  public void testLoadScript() throws IOException {
    Readable in = new StringReader(Files.readString(Paths.get("res/script.txt")));
    Appendable out = new StringBuilder();

    ImageGallery model = new ImageGalleryImpl();
    ImageView view = new ImageViewImpl(model, out);
    ImageController controller = new ImageControllerImpl(model, view, in);
    controller.runProgram();
    assertEquals(out.toString(), "Successfully loaded v from img"
            + "/vadim-sadovsk.jpg" + System.lineSeparator()
            + "Successfully loaded p from img/personal.ppm" + System.lineSeparator()
            + "Successfully loaded t from img/transparent.png" + System.lineSeparator()
            + "Successfully loaded m from img/monique-sherar.bmp" + System.lineSeparator()
            + "Successfully sharpened v and saved as vs" + System.lineSeparator()
            + "Successfully changed vs to blue-component "
            + "grayscale and saved as vb" + System.lineSeparator()
            + "Successfully flipped vb horizontally and saved as vh" + System.lineSeparator()
            + "Successfully grayscaled p and saved as pg" + System.lineSeparator()
            + "Successfully brightened pg by 50 and saved as pb" + System.lineSeparator()
            + "Successfully sepia toned t and saved as ts" + System.lineSeparator()
            + "Successfully blurred ts and saved as tb" + System.lineSeparator()
            + "Successfully flipped tb vertically and saved as tv" + System.lineSeparator()
            + "Successfully darkened m by -50 and saved as mb" + System.lineSeparator()
            + "Successfully sepia toned mb and saved as ms" + System.lineSeparator()
            + "Successfully saved vh to img/results/vadim-final.ppm" + System.lineSeparator()
            + "Successfully saved pb to img/results/personal-final.jpg" + System.lineSeparator()
            + "Successfully saved tv to img/results/transparent-final.png" + System.lineSeparator()
            + "Successfully saved ms to img/results/monique-final.ppm" + System.lineSeparator());
  }
}
