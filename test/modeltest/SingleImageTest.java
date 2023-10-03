package modeltest;

import org.junit.Before;
import org.junit.Test;

import model.RGB;
import model.RGBImpl;
import model.SingleImage;
import model.SingleImageImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for {@link SingleImage}.
 */

public class SingleImageTest {

  private SingleImage image;
  private RGB[][] pix;

  @Before
  public void init() {
    pix = new RGB[2][2];
    pix[0][0] = new RGBImpl(100, 30, 60);
    pix[0][1] = new RGBImpl(30, 60, 100);
    pix[1][0] = new RGBImpl(30, 100, 60);
    pix[1][1] = new RGBImpl(60, 30, 100);
    image = new SingleImageImpl(2, 2, 255, pix);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidPixel() {
    RGB[][] pix = new RGB[2][2];
    pix[0][0] = new RGBImpl(100, 30, 60);
    pix[0][1] = new RGBImpl(30, 60, 300);
    pix[1][0] = new RGBImpl(30, 100, 60);
    pix[1][1] = new RGBImpl(60, 30, 100);
    image = new SingleImageImpl(2, 2, 255, pix);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDimensionsWidth() {
    RGB[][] pix = new RGB[2][3];
    pix[0][0] = new RGBImpl(100, 30, 60);
    pix[0][1] = new RGBImpl(30, 60, 100);
    pix[1][0] = new RGBImpl(30, 100, 60);
    pix[1][1] = new RGBImpl(60, 30, 100);
    image = new SingleImageImpl(2, 2, 255, pix);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDimensionsHeight() {
    RGB[][] pix = new RGB[3][2];
    pix[0][0] = new RGBImpl(100, 30, 60);
    pix[0][1] = new RGBImpl(30, 60, 100);
    pix[1][0] = new RGBImpl(30, 100, 60);
    pix[1][1] = new RGBImpl(60, 30, 100);
    image = new SingleImageImpl(2, 2, 255, pix);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidth() {
    image = new SingleImageImpl(-2, 2, 255, pix);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeight() {
    image = new SingleImageImpl(2, -2, 255, pix);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidthHeight() {
    image = new SingleImageImpl(-2, -2, 255, pix);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeMaxValue() {
    image = new SingleImageImpl(2, 2, -1, pix);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMaxWidth() {
    image = new SingleImageImpl(256, 2, 255, pix);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMaxHeight() {
    image = new SingleImageImpl(2, 256, 255, pix);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMaxWidthHeight() {
    image = new SingleImageImpl(256, 256, 255, pix);
  }

  @Test
  public void getWidth() {
    assertEquals(2, image.getWidth());
  }

  @Test
  public void getHeight() {
    assertEquals(2, image.getHeight());
  }

  @Test
  public void getMaxValue() {
    assertEquals(255, image.getMaxValue());
  }

  @Test
  public void getPixels() {
    RGB[][] pix = new RGB[2][2];
    pix[0][0] = new RGBImpl(100, 30, 60);
    pix[0][1] = new RGBImpl(30, 60, 100);
    pix[1][0] = new RGBImpl(30, 100, 60);
    pix[1][1] = new RGBImpl(60, 30, 100);
    assertEquals(100, image.getPixels()[0][0].getRed());
    assertEquals(30, image.getPixels()[0][0].getGreen());
    assertEquals(60, image.getPixels()[0][0].getBlue());
  }

  @Test
  public void getPixel() {
    assertEquals("100 30 60 ", image.getPixel(0, 0));
    assertEquals("30 60 100 ", image.getPixel(0, 1));
    assertEquals("30 100 60 ", image.getPixel(1, 0));
  }
}
