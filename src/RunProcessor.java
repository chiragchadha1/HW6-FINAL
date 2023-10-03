import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.ImageController;
import controller.ImageControllerGUI;
import controller.ImageControllerImpl;
import model.ImageGallery;
import model.ImageGalleryImpl;
import view.ImageView;
import view.ImageViewGUI;
import view.ImageViewGUIImpl;
import view.ImageViewImpl;

/**
 * Runs the program. Used as a test.
 */
public class RunProcessor {

  /**
   * The main class to run the image processing program.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    ImageGallery gallery = new ImageGalleryImpl();
    ImageView view = new ImageViewImpl(gallery);

    if (args.length == 0) {
      ImageViewGUI gui = new ImageViewGUIImpl(gallery);
      ImageController controller = new ImageControllerGUI(gallery, gui);
      controller.runProgram();

    } else {
      switch (args[0]) {
        case "-text":
          Readable in = new InputStreamReader(System.in);
          ImageController controllerText = new ImageControllerImpl(gallery, view, in);
          controllerText.runProgram();
          break;
        case "-file":
        case "-f":
          BufferedReader br = null;
          try {
            br = new BufferedReader(new FileReader(args[1]));
          } catch (IOException e) {
            System.out.println(e.getMessage());
          }
          ImageController controllerFile = new ImageControllerImpl(gallery, view, br);
          controllerFile.runProgram();
          break;
        default:
          System.out.println("Invalid input. Please try again.");
          break;
      }
    }
  }
}
