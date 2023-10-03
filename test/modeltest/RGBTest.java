package modeltest;

import org.junit.Before;
import org.junit.Test;

import model.RGB;
import model.RGBImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for {@link RGB}.
 */
public class RGBTest {

  private RGB color;
  private RGB min;
  private RGB max;

  @Before
  public void init() {
    color = new RGBImpl(110, 200, 44);
    min = new RGBImpl(0, 0, 0);
    max = new RGBImpl(255, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNegativeRed() {
    new RGBImpl(-1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMaxRed() {
    new RGBImpl(256, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNegativeGreen() {
    new RGBImpl(0, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMaxGreen() {
    new RGBImpl(0, 256, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNegativeBlue() {
    new RGBImpl(0, 0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMaxBlue() {
    new RGBImpl(0, 0, 256);
  }

  @Test
  public void getRed() {
    assertEquals(110, color.getRed());
    assertEquals(0, min.getRed());
    assertEquals(255, max.getRed());
  }

  @Test
  public void getGreen() {
    assertEquals(200, color.getGreen());
    assertEquals(0, min.getGreen());
    assertEquals(255, max.getGreen());
  }

  @Test
  public void getBlue() {
    assertEquals(44, color.getBlue());
    assertEquals(0, min.getBlue());
    assertEquals(255, max.getBlue());
  }
}
