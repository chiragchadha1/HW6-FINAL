package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Arrays;
import java.util.IntSummaryStatistics;

import javax.swing.JLabel;

/** Represents a histogram of the image using red, green, blue, and intensity values. */
public class HistogramModel extends JLabel {

  private SingleImage model;
  private int[][] histogram;
  private IntSummaryStatistics redStat;
  private IntSummaryStatistics greenStat;
  private IntSummaryStatistics blueStat;
  private IntSummaryStatistics intensityStat;
  private static double redScaled;
  private static double greenScaled;
  private static double blueScaled;
  private static double intensityScaled;

  /** Constructs a histogram model using a given SingleImage.
   * @param model The model of the image.
   */
  public HistogramModel(SingleImage model) throws IllegalArgumentException {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    histogram = new int[4][256];
    getColorArray(model);

    redStat = Arrays.stream(histogram[0]).summaryStatistics();
    greenStat = Arrays.stream(histogram[1]).summaryStatistics();
    blueStat = Arrays.stream(histogram[2]).summaryStatistics();
    intensityStat = Arrays.stream(histogram[3]).summaryStatistics();
  }

  /**
   * Gets the color array of the given image.
   * @param model the model of the image.
   */
  public void getColorArray(SingleImage model) {
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getWidth(); j++) {
        int blue = model.getPixels()[i][j].getBlue();
        int green = model.getPixels()[i][j].getGreen();
        int red = model.getPixels()[i][j].getRed();
        int intensity = (red + blue + green) / 3;
        histogram[0][red]++;
        histogram[1][green]++;
        histogram[2][blue]++;
        histogram[3][intensity]++;
      }
    }
  }

  /**
   * This method is used to paint the Histogram.
   *
   * @param g The Graphics object.
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2D = (Graphics2D) g.create();

    redScaled = (model.getHeight() * 0.2) / redStat.getMax();
    greenScaled = (model.getHeight() * 0.2) / greenStat.getMax();
    blueScaled = (model.getHeight() * 0.2) / blueStat.getMax();
    intensityScaled = (model.getHeight() * 0.2) / intensityStat.getMax();

    AffineTransform orig = g2D.getTransform();

    g2D.translate(0, this.getPreferredSize().getHeight());
    g2D.scale(1, -1);

    for (int i = 0; i < histogram[0].length - 1; i++) {
      g2D.setColor(Color.RED);
      g2D.drawLine(i, (int) (redScaled * histogram[0][i]), i + 1, (int) (redScaled
              * histogram[0][i + 1]));

      g2D.setColor(Color.GREEN);
      g2D.drawLine(i, (int) (greenScaled * histogram[0][i]), i + 1, (int) (greenScaled
              * histogram[0][i + 1]));

      g2D.setColor(Color.BLUE);
      g2D.drawLine(i, (int) (blueScaled * histogram[0][i]), i + 1, (int) (blueScaled
              * histogram[0][i + 1]));

      g2D.setColor(Color.BLACK);
      g2D.drawLine(i, (int) (intensityScaled * histogram[0][i]), i + 1, (int) (intensityScaled
              * histogram[0][i + 1]));
    }


    g2D.setTransform(orig);
    g2D.dispose();
  }
}