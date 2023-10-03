package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.HistogramModel;
import model.ImageGallery;
import model.SingleImage;

/**
 * View implementation of {@link ImageViewGUI}.
 * Provides the ability to display the GUI of the image processing app.
 * Handles all user interactions with the GUI and filtering/transformation of images.
 */
public class ImageViewGUIImpl extends JFrame implements ImageViewGUI {
  private final ImageGallery gallery;
  private ImageViewActions controller;
  private String fileName;

  private final JFrame frame;
  private final JPanel mainPanel;
  private final JLabel instructions;

  private final JPanel imagePanel;
  private final JLabel imageLabel;
  private final JLabel imageName;
  private final JScrollPane imageScrollPane;

  private final JLabel histogramText;
  private JLabel histogramLabel;
  private final JScrollPane histogramScrollPanel;


  /**
   * Constructor for the GUI view.
   *
   * @param gallery Represents a gallery of images
   * @throws IllegalArgumentException if the model is null.
   */
  public ImageViewGUIImpl(ImageGallery gallery) throws IllegalArgumentException {
    super();
    if (gallery == null) {
      throw new IllegalArgumentException("Gallery cannot be null.");
    }
    this.gallery = gallery;

    this.imageLabel = new JLabel();
    imagePanel = new JPanel();
    histogramLabel = new JLabel();
    histogramText = new JLabel();

    histogramScrollPanel = new JScrollPane();

    mainPanel = new JPanel();
    instructions = new JLabel();
    imageScrollPane = new JScrollPane();
    frame = new JFrame();
    imageName = new JLabel();

    createMainPanel();
    createImagePanel();
    createFrame();
    createMenu();
  }

  // Creates the main panel (left side) of the GUI.
  private void createMainPanel() {
    instructions.setText("<html> <h2> Instructions </h2>"
            + "1. Load an image using the \"File > Load\" menu item."
            + "<br> 2. Select an operation to perform on the image using the \"Filter\" "
            + "or \"Transform\" menu items."
            + "<br> 3. Click the \"File > Save\" menu item to save the image."
            + "<br> 4. To view all images loaded double click on the file name in the"
            + " \"File > Image Gallery\" menu item."
            + "</html>");

    histogramScrollPanel.setMaximumSize(new Dimension(300, 225));
    histogramScrollPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

    histogramText.setText("<html> <br> <br> <h2> Histogram </h2> </html>");

    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.add(instructions);
    mainPanel.add(imageName);
    mainPanel.add(histogramText);
    mainPanel.add(histogramScrollPanel);
  }

  // Creates the image panel (left side) of the GUI.
  private void createImagePanel() {
    imageScrollPane.setPreferredSize(new Dimension(500, 500));
    imageScrollPane.setViewportView(imageLabel);
    imagePanel.add(imageScrollPane);
  }

  // Creates the frame of the GUI.
  private void createFrame() {
    frame.setLayout(new BorderLayout());
    frame.add(imagePanel, BorderLayout.WEST);
    frame.add(mainPanel, BorderLayout.EAST);
    frame.setTitle("Image Processor");
    frame.setPreferredSize(new Dimension(1100, 600));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Render a specific message using JOptionPane.
   *
   * @param message the message to be transmitted
   * @param title   the title of the message
   * @param type    the type of message (ex: information, error, etc.)
   */
  @Override
  public void renderMessage(String message, String title, int type) {
    JOptionPane.showMessageDialog(this, message, title, type);
  }

  /**
   * Displays the GUI view to the user if true, otherwise hides the GUI view.
   *
   * @param b true if the GUI view should be displayed, false otherwise.
   */
  @Override
  public void displayGUI(boolean b) {
    this.frame.setVisible(b);
    this.frame.pack();
  }

  /**
   * Initializes a controller to allow the view to access actions from the controller.
   *
   * @param controller the controller to be initialized.
   */
  @Override
  public void setFeatures(ImageViewActions controller) {
    this.controller = controller;
  }

  /**
   * Creates a JMenuBar with all the necessary menu items (file, gallery, filter, transform, etc.)
   */
  @Override
  public void createMenu() {
    JMenuBar menu = new JMenuBar();

    JMenu file = new JMenu("File");
    JMenu filter = new JMenu("Filter");
    JMenu component = new JMenu("Grey Scale Using Component");
    JMenu transform = new JMenu("Transform");


    JMenuItem load = new JMenuItem("Load");
    load.addActionListener(e -> loadImage());
    JMenuItem save = new JMenuItem("Save");
    save.addActionListener(e -> saveImage());
    JMenuItem gallery = new JMenuItem("Image Gallery");
    gallery.addActionListener(e -> displayGallery());
    file.add(load);
    file.add(save);
    file.add(gallery);

    JMenuItem brighten = new JMenuItem("Brighten");
    brighten.addActionListener(e -> processImage("brighten"));
    JMenuItem greyScale = new JMenuItem("Grey Scale");
    greyScale.addActionListener(e -> processImage("greyscale"));
    JMenuItem sepia = new JMenuItem("Sepia");
    sepia.addActionListener(e -> processImage("sepia"));
    JMenuItem sharpen = new JMenuItem("Sharpen");
    sharpen.addActionListener(e -> processImage("sharpen"));
    JMenuItem blur = new JMenuItem("Blur");
    blur.addActionListener(e -> processImage("blur"));

    filter.add(brighten);
    filter.add(greyScale);
    filter.add(sepia);
    filter.add(sharpen);
    filter.add(blur);

    JMenuItem horizontalFlip = new JMenuItem("Horizontal Flip");
    horizontalFlip.addActionListener(e -> processImage("horizontalFlip"));
    JMenuItem verticalFlip = new JMenuItem("Vertical Flip");
    verticalFlip.addActionListener(e -> processImage("verticalFlip"));
    JMenuItem downscale = new JMenuItem("Downscale");
    downscale.addActionListener(e -> processImage("downscale"));

    transform.add(horizontalFlip);
    transform.add(verticalFlip);
    transform.add(downscale);

    JMenuItem redComponent = new JMenuItem("Red Component");
    redComponent.addActionListener(e -> processImage("red-component"));
    JMenuItem greenComponent = new JMenuItem("Green Component");
    greenComponent.addActionListener(e -> processImage("green-component"));
    JMenuItem blueComponent = new JMenuItem("Blue Component");
    blueComponent.addActionListener(e -> processImage("blue-component"));
    JMenuItem valueComponent = new JMenuItem("Value Component");
    valueComponent.addActionListener(e -> processImage("value-component"));
    JMenuItem lumaComponent = new JMenuItem("Luma Component");
    lumaComponent.addActionListener(e -> processImage("luma-component"));
    JMenuItem intensityComponent = new JMenuItem("Intensity Component");
    intensityComponent.addActionListener(e -> processImage("intensity-component"));

    component.add(redComponent);
    component.add(greenComponent);
    component.add(blueComponent);
    component.add(valueComponent);
    component.add(lumaComponent);
    component.add(intensityComponent);
    filter.add(component);

    menu.add(file);
    menu.add(filter);
    menu.add(transform);

    this.frame.setJMenuBar(menu);

  }

  /**
   * Loads an image from a specified file path.
   */
  @Override
  public void loadImage() {
    JFileChooser load = new JFileChooser();
    load.setFileFilter(new FileNameExtensionFilter("Accepted File Types",
            "ppm", "jpg", "jpeg", "png", "bmp"));

    try {
      if (load.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        fileName = JOptionPane.showInputDialog(this,
                "Enter a name for the image:", "Image Name",
                JOptionPane.QUESTION_MESSAGE);
        if (fileName == null || fileName.isEmpty()) {
          renderMessage("Image name cannot be empty.", "Error",
                  JOptionPane.ERROR_MESSAGE);
        } else {

          File image = load.getSelectedFile();
          controller.loadAction(image.getAbsolutePath(), fileName);
          renderMessage("Image loaded successfully.", "Success",
                  JOptionPane.INFORMATION_MESSAGE);
        }
      }
    } catch (Exception e) {
      renderMessage("Error loading image." + e.getMessage(), "Error",
              JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Saves the current image to a specified file path.
   */
  @Override
  public void saveImage() {
    if (fileName == null) {
      renderMessage("No image has been loaded.", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
      JFileChooser save = new JFileChooser();
      save.setFileFilter(new FileNameExtensionFilter("Accepted File Types",
              "ppm", "jpg", "jpeg", "png", "bmp"));
      try {
        if (save.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
          File image = save.getSelectedFile();
          controller.saveAction(image.getAbsolutePath(), fileName);
          renderMessage("Image saved successfully.", "Success",
                  JOptionPane.INFORMATION_MESSAGE);
        }
      } catch (Exception e) {
        renderMessage("Error saving image." + e.getMessage(), "Error",
                JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  /**
   * Applies a given operation to an image. Utilizes the {@link ImageViewActions} to do so.
   *
   * @param operation the operation to be applied.
   */
  @Override
  public void processImage(String operation) {
    String fileNew = JOptionPane.showInputDialog(this,
            "Enter a name for the image:", "Image Name",
            JOptionPane.QUESTION_MESSAGE);
    try {
      switch (operation) {
        case "brighten":
          String constant = JOptionPane.showInputDialog(this,
                  "Enter a constant:", "Constant",
                  JOptionPane.QUESTION_MESSAGE);
          controller.brightenAction(Integer.parseInt(constant), fileName, fileNew);
          fileName = fileNew;
          break;
        case "downscale":
          String width = JOptionPane.showInputDialog(this,
                  "Enter a width:", "Width",
                  JOptionPane.QUESTION_MESSAGE);
          String height = JOptionPane.showInputDialog(this,
                  "Enter a height:", "Height",
                  JOptionPane.QUESTION_MESSAGE);
          controller.downscaleAction(Integer.parseInt(width), Integer.parseInt(height),
                  fileName, fileNew);
          fileName = fileNew;
          break;
        case "greyscale":
          controller.greyScaleAction(fileName, fileNew);
          fileName = fileNew;
          break;
        case "sepia":
          controller.sepiaAction(fileName, fileNew);
          fileName = fileNew;
          break;
        case "sharpen":
          controller.sharpenAction(fileName, fileNew);
          fileName = fileNew;
          break;
        case "blur":
          controller.blurAction(fileName, fileNew);
          fileName = fileNew;
          break;
        case "horizontalFlip":
          controller.horizontalFlipAction(fileName, fileNew);
          fileName = fileNew;
          break;
        case "verticalFlip":
          controller.verticalFlipAction(fileName, fileNew);
          fileName = fileNew;
          break;
        case "red-component":
          controller.componentAction("red-component", fileName, fileNew);
          fileName = fileNew;
          break;
        case "green-component":
          controller.componentAction("green-component", fileName, fileNew);
          fileName = fileNew;
          break;
        case "blue-component":
          controller.componentAction("blue-component", fileName, fileNew);
          fileName = fileNew;
          break;
        case "value-component":
          controller.componentAction("value-component", fileName, fileNew);
          fileName = fileNew;
          break;
        case "luma-component":
          controller.componentAction("luma-component", fileName, fileNew);
          fileName = fileNew;
          break;
        case "intensity-component":
          controller.componentAction("intensity-component", fileName, fileNew);
          fileName = fileNew;
          break;
        default:
          break;
      }
    } catch (IllegalArgumentException e) {
      renderMessage("Error processing image.", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Updates the name of the image displayed in the GUI.
   *
   * @param name the new name of the image.
   */
  @Override
  public void updateImageName(String name) {
    this.fileName = name;
  }

  /**
   * Updates the current image displayed in the GUI with the given image.
   */
  @Override
  public void updateImage(BufferedImage image) {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    imageLabel.setIcon(new ImageIcon(image));
    imageName.setText("<html> <br> <br> <h2>Current file:</h2>" + fileName + "</html>");
    imageName.setHorizontalAlignment(JLabel.LEFT);

    SingleImage imageHistogram = gallery.getImage(fileName);
    histogramLabel = new HistogramModel(imageHistogram);
    histogramLabel.setPreferredSize(new Dimension(250, 250));
    histogramScrollPanel.setViewportView(histogramLabel);
  }

  /**
   * Displays the loaded images in the {@link model.ImageGallery}.
   */
  @Override
  public void displayGallery() {
    JFrame galleryFrame = new JFrame("Image Gallery");
    galleryFrame.setSize(200, 250);
    galleryFrame.setLocationRelativeTo(null);
    galleryFrame.setVisible(true);

    JList galleryList = new JList(gallery.getGallery().keySet().toArray());
    galleryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    galleryList.setLayoutOrientation(JList.VERTICAL);

    JButton loadButton = new JButton("Load");
    galleryList.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
          controller.updateViewImage(galleryList.getSelectedValue().toString());
        }
      }
    });
    galleryList.add(loadButton);

    galleryFrame.add(galleryList);
  }


}