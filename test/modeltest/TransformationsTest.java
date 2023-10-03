package modeltest;

import org.junit.Before;
import org.junit.Test;

import model.Filter;
import model.RGB;
import model.RGBImpl;
import model.SingleImage;
import model.SingleImageImpl;
import model.Transformations;
import model.TransformationsImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for {@link Transformations}.
 */
public class TransformationsTest {

  private Transformations transform;

  private Filter filter;

  private SingleImage image;

  @Before
  public void init() {
    RGB[][] pix = new RGB[2][2];
    pix[0][0] = new RGBImpl(100, 30, 60);
    pix[0][1] = new RGBImpl(30, 60, 100);
    pix[1][0] = new RGBImpl(30, 100, 60);
    pix[1][1] = new RGBImpl(60, 30, 100);
    image = new SingleImageImpl(2, 2, 255, pix);
    transform = new TransformationsImpl(image);
    filter = new Filter(image);
  }

  @Test
  public void downscale() {
    SingleImage model = transform.downscale(1, 1);
    assertEquals("100 30 60 ", model.getPixel(0, 0));
  }


  @Test
  public void flipVertical() {
    SingleImage model = transform.flipVertical();
    assertEquals("30 100 60 ", model.getPixel(0, 0));
    assertEquals("60 30 100 ", model.getPixel(0, 1));
    assertEquals("100 30 60 ", model.getPixel(1, 0));
    assertEquals("30 60 100 ", model.getPixel(1, 1));
  }

  @Test
  public void flipHorizontal() {
    SingleImage model = transform.flipHorizontal();
    assertEquals("30 60 100 ", model.getPixel(0, 0));
    assertEquals("100 30 60 ", model.getPixel(0, 1));
    assertEquals("60 30 100 ", model.getPixel(1, 0));
    assertEquals("30 100 60 ", model.getPixel(1, 1));
  }

  @Test
  public void brightenPositive() {
    SingleImage model = transform.brighten(10);
    assertEquals("110 40 70 ", model.getPixel(0, 0));
    assertEquals("40 70 110 ", model.getPixel(0, 1));
    assertEquals("40 110 70 ", model.getPixel(1, 0));
    assertEquals("70 40 110 ", model.getPixel(1, 1));
  }

  @Test
  public void brightenNegative() {
    SingleImage model = transform.brighten(-10);
    assertEquals("90 20 50 ", model.getPixel(0, 0));
    assertEquals("20 50 90 ", model.getPixel(0, 1));
    assertEquals("20 90 50 ", model.getPixel(1, 0));
    assertEquals("50 20 90 ", model.getPixel(1, 1));
  }

  @Test
  public void brightenPostiveOverMaxValue() {
    SingleImage model = transform.brighten(300);
    assertEquals("255 255 255 ", model.getPixel(0, 0));
    assertEquals("255 255 255 ", model.getPixel(0, 1));
    assertEquals("255 255 255 ", model.getPixel(1, 0));
    assertEquals("255 255 255 ", model.getPixel(1, 1));
  }

  @Test
  public void brightenNegativeUnderMinimumValue() {
    SingleImage model = transform.brighten(-300);
    assertEquals("0 0 0 ", model.getPixel(0, 0));
    assertEquals("0 0 0 ", model.getPixel(0, 1));
    assertEquals("0 0 0 ", model.getPixel(1, 0));
    assertEquals("0 0 0 ", model.getPixel(1, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void brightenZero() {
    SingleImage model = transform.brighten(0);
  }

  @Test
  public void redScale() {
    SingleImage model = transform.colorScale("red");
    assertEquals("100 100 100 ", model.getPixel(0, 0));
    assertEquals("30 30 30 ", model.getPixel(0, 1));
    assertEquals("30 30 30 ", model.getPixel(1, 0));
    assertEquals("60 60 60 ", model.getPixel(1, 1));
  }

  @Test
  public void greenScale() {
    SingleImage model = transform.colorScale("green");
    assertEquals("30 30 30 ", model.getPixel(0, 0));
    assertEquals("60 60 60 ", model.getPixel(0, 1));
    assertEquals("100 100 100 ", model.getPixel(1, 0));
    assertEquals("30 30 30 ", model.getPixel(1, 1));
  }

  @Test
  public void blueScale() {
    SingleImage model = transform.colorScale("blue");
    assertEquals("60 60 60 ", model.getPixel(0, 0));
    assertEquals("100 100 100 ", model.getPixel(0, 1));
    assertEquals("60 60 60 ", model.getPixel(1, 0));
    assertEquals("100 100 100 ", model.getPixel(1, 1));
  }

  @Test
  public void valueScale() {
    SingleImage model = transform.valueScale();
    assertEquals("100 100 100 ", model.getPixel(0, 0));
    assertEquals("100 100 100 ", model.getPixel(0, 1));
    assertEquals("100 100 100 ", model.getPixel(1, 0));
    assertEquals("100 100 100 ", model.getPixel(1, 1));
  }

  @Test
  public void intensityScale() {
    SingleImage model = transform.intensityScale();
    assertEquals("63 63 63 ", model.getPixel(0, 0));
    assertEquals("63 63 63 ", model.getPixel(0, 1));
    assertEquals("63 63 63 ", model.getPixel(1, 0));
    assertEquals("63 63 63 ", model.getPixel(1, 1));
  }

  @Test
  public void lumaScale() {
    SingleImage model = transform.lumaScale();
    assertEquals("47 47 47 ", model.getPixel(0, 0));
    assertEquals("57 57 57 ", model.getPixel(0, 1));
    assertEquals("82 82 82 ", model.getPixel(1, 0));
    assertEquals("41 41 41 ", model.getPixel(1, 1));
  }

  @Test
  public void sepiaScale() {
    SingleImage model = transform.sepiaScale();
    assertEquals("74 66 51 ", model.getPixel(0, 0));
    assertEquals("77 68 53 ", model.getPixel(0, 1));
    assertEquals("100 89 69 ", model.getPixel(1, 0));
    assertEquals("66 58 45 ", model.getPixel(1, 1));
  }

  @Test
  public void greyScale() {
    SingleImage model = transform.greyScale();
    assertEquals("47 47 47 ", model.getPixel(0, 0));
    assertEquals("57 57 57 ", model.getPixel(0, 1));
    assertEquals("82 82 82 ", model.getPixel(1, 0));
    assertEquals("41 41 41 ", model.getPixel(1, 1));
  }

  @Test
  public void blur() {
    SingleImage model = filter.blur();
    assertEquals("37 31 42 ", model.getPixel(0, 0));
    assertEquals("31 29 50 ", model.getPixel(0, 1));
    assertEquals("31 37 42 ", model.getPixel(1, 0));
    assertEquals("29 31 50 ", model.getPixel(1, 1));
  }

  @Test
  public void sharpen() {
    SingleImage model = filter.sharpen();
    assertEquals("131 78 125 ", model.getPixel(0, 0));
    assertEquals("78 101 155 ", model.getPixel(0, 1));
    assertEquals("78 131 125 ", model.getPixel(1, 0));
    assertEquals("101 78 155 ", model.getPixel(1, 1));
  }
}
