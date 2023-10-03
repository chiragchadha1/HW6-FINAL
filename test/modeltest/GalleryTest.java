package modeltest;

import org.junit.Before;
import org.junit.Test;

import model.ImageGallery;
import model.ImageGalleryImpl;
import model.RGB;
import model.RGBImpl;
import model.SingleImage;
import model.SingleImageImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for {@link ImageGallery}.
 */
public class GalleryTest {

  private SingleImage image;
  private RGB[][] pix;
  private ImageGallery gallery;

  @Before
  public void init() {
    pix = new RGB[2][2];
    pix[0][0] = new RGBImpl(100, 30, 60);
    pix[0][1] = new RGBImpl(30, 60, 100);
    pix[1][0] = new RGBImpl(30, 100, 60);
    pix[1][1] = new RGBImpl(60, 30, 100);
    image = new SingleImageImpl(2, 2, 255, pix);
    gallery = new ImageGalleryImpl();
  }

  @Test
  public void testPutAndGet() {
    gallery.put("test", image);
    assertEquals(gallery.getImage("test").getHeight(), 2);
    assertEquals(gallery.getImage("test").getWidth(), 2);
    assertEquals(gallery.getImage("test").getPixels()[0][0], pix[0][0]);
    assertEquals(gallery.getImage("test").getPixels()[0][1], pix[0][1]);
    assertEquals(gallery.getImage("test").getPixels()[1][0], pix[1][0]);
    assertEquals(gallery.getImage("test").getPixels()[1][1], pix[1][1]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullGallery() {
    ImageGallery nullGallery = new ImageGalleryImpl();
    nullGallery.put("test", null);
  }

}
