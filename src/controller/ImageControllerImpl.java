package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

import controller.commands.BlueScale;
import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.Downscale;
import controller.commands.FlipHorizontal;
import controller.commands.FlipVertical;
import controller.commands.GreenScale;
import controller.commands.GreyScale;
import controller.commands.IntensityScale;
import controller.commands.Load;
import controller.commands.LumaScale;
import controller.commands.RedScale;
import controller.commands.Save;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.ValueScale;
import model.ImageGallery;
import view.ImageView;

import static java.lang.Integer.parseInt;

/**
 * Represents an implementation of an ImageController.
 * Utilizes user input to perform commands on the image and calls on the model to do so.
 */
public class ImageControllerImpl implements ImageController {
  private final ImageGallery gallery;
  private final ImageView view;
  private final Scanner sc;

  /**
   * Constructor for the ImageControllerImpl class.
   *
   * @param gallery the gallery that stores the images.
   * @param view    the view that displays the images.
   * @param input   the input that is used to read from.
   * @throws IllegalArgumentException if the arguments are null.
   */
  public ImageControllerImpl(ImageGallery gallery, ImageView view, Readable input)
          throws IllegalArgumentException {
    if (gallery == null || view == null || input == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.gallery = gallery;
    this.view = view;
    this.sc = new Scanner(input);
  }

  /**
   * Starts the program by giving control to the controller.
   *
   * @throws IllegalStateException if the controller is unable to perform a command.
   */
  public void runProgram() throws IllegalStateException {

    Stack<ImageCommands> commands = new Stack<>();

    Map<String, Function<Scanner, ImageCommands>> knownCommands = knownCommands();

    while (sc.hasNext()) {
      ImageCommands c;
      String userInput = sc.next();
      if (userInput.toLowerCase().contains("q")
              || userInput.toLowerCase().contains("quit")) {
        this.view.renderMessage("Image processor terminated.");
        return;
      }
      try {
        Function<Scanner, ImageCommands> cmd = knownCommands.getOrDefault(userInput,
                null);
        if (cmd == null) {
          throw new IllegalArgumentException("Command cannot be null.");
        } else {
          c = cmd.apply(sc);
          commands.add(c);
          c.runCommands(this.gallery);
        }
      } catch (NullPointerException e) {
        this.view.renderMessage("Invalid command. Please try again." + System.lineSeparator());
      } catch (IllegalArgumentException e) {
        this.view.renderMessage(e.getMessage() + System.lineSeparator());
        this.view.renderMessage("Invalid command. Please try again." + System.lineSeparator());
      }
    }
  }

  /**
   * Creates a map of known commands.
   *
   * @return a map of known commands.
   */
  //
  private Map<String, Function<Scanner, ImageCommands>> knownCommands() {
    Map<String, Function<Scanner, ImageCommands>> knownCommands = new HashMap<>();
    knownCommands.put("load", (Scanner s) -> {
      return new Load(s.next(), s.next(), view);
    });
    knownCommands.put("save", (Scanner s) -> {
      return new Save(s.next(), s.next(), view);
    });
    knownCommands.put("red-component", (Scanner s) -> {
      String[] tokens = s.nextLine().split(" ");
      if (tokens.length == 3) {
        return new RedScale(tokens[1], tokens[2], view);
      } else if (tokens.length == 4) {
        return new RedScale(tokens[1], tokens[2], tokens[3], view);
      } else {
        throw new IllegalArgumentException("Incorrect parameters.");
      }
    });
    knownCommands.put("green-component", (Scanner s) -> {
      String[] tokens = s.nextLine().split(" ");
      if (tokens.length == 3) {
        return new GreenScale(tokens[1], tokens[2], view);
      } else if (tokens.length == 4) {
        return new GreenScale(tokens[1], tokens[2], tokens[3], view);
      } else {
        throw new IllegalArgumentException("Incorrect parameters.");
      }
    });
    knownCommands.put("blue-component", (Scanner s) -> {
      String[] tokens = s.nextLine().split(" ");
      if (tokens.length == 3) {
        return new BlueScale(tokens[1], tokens[2], view);
      } else if (tokens.length == 4) {
        return new BlueScale(tokens[1], tokens[2], tokens[3], view);
      } else {
        throw new IllegalArgumentException("Incorrect parameters.");
      }
    });
    knownCommands.put("value-component", (Scanner s) -> {
      String[] tokens = s.nextLine().split(" ");
      if (tokens.length == 3) {
        return new ValueScale(tokens[1], tokens[2], view);
      } else if (tokens.length == 4) {
        return new ValueScale(tokens[1], tokens[2], tokens[3], view);
      } else {
        throw new IllegalArgumentException("Incorrect parameters.");
      }
    });
    knownCommands.put("luma-component", (Scanner s) -> {
      String[] tokens = s.nextLine().split(" ");
      if (tokens.length == 3) {
        return new LumaScale(tokens[1], tokens[2], view);
      } else if (tokens.length == 4) {
        return new LumaScale(tokens[1], tokens[2], tokens[3], view);
      } else {
        throw new IllegalArgumentException("Incorrect parameters.");
      }
    });
    knownCommands.put("intensity-component", (Scanner s) -> {
      String[] tokens = s.nextLine().split(" ");
      if (tokens.length == 3) {
        return new IntensityScale(tokens[1], tokens[2], view);
      } else if (tokens.length == 4) {
        return new IntensityScale(tokens[1], tokens[2], tokens[3], view);
      } else {
        throw new IllegalArgumentException("Incorrect parameters.");
      }
    });
    knownCommands.put("horizontal-flip", (Scanner s) -> {
      return new FlipHorizontal(s.next(), s.next(), view);
    });
    knownCommands.put("vertical-flip", (Scanner s) -> {
      return new FlipVertical(s.next(), s.next(), view);
    });
    knownCommands.put("brighten", (Scanner s) -> {
      String[] tokens = s.nextLine().split(" ");
      if (tokens.length == 4) {
        return new Brighten(parseInt(tokens[1]), tokens[2], tokens[3], view);
      } else if (tokens.length == 5) {
        return new Brighten(parseInt(tokens[1]), tokens[2], tokens[3], tokens[4], view);
      } else {
        throw new IllegalArgumentException("Incorrect parameters.");
      }
    });
    knownCommands.put("greyscale", (Scanner s) -> {
      String[] tokens = s.nextLine().split(" ");
      if (tokens.length == 3) {
        return new GreyScale(tokens[1], tokens[2], view);
      } else if (tokens.length == 4) {
        return new GreyScale(tokens[1], tokens[2], tokens[3], view);
      } else {
        throw new IllegalArgumentException("Incorrect parameters.");
      }
    });
    knownCommands.put("sepia", (Scanner s) -> {
      String[] tokens = s.nextLine().split(" ");
      if (tokens.length == 3) {
        return new Sepia(tokens[1], tokens[2], view);
      } else if (tokens.length == 4) {
        return new Sepia(tokens[1], tokens[2], tokens[3], view);
      } else {
        throw new IllegalArgumentException("Incorrect parameters.");
      }
    });
    knownCommands.put("blur", (Scanner s) -> {
      String[] tokens = s.nextLine().split(" ");
      if (tokens.length == 3) {
        return new Blur(tokens[1], tokens[2], view);
      } else if (tokens.length == 4) {
        return new Blur(tokens[1], tokens[2], tokens[3], view);
      } else {
        throw new IllegalArgumentException("Incorrect parameters.");
      }
    });
    knownCommands.put("sharpen", (Scanner s) -> {
      String[] tokens = s.nextLine().split(" ");
      if (tokens.length == 3) {
        return new Sharpen(tokens[1], tokens[2], view);
      } else if (tokens.length == 4) {
        return new Sharpen(tokens[1], tokens[2], tokens[3], view);
      } else {
        throw new IllegalArgumentException("Incorrect parameters.");
      }
    });
    knownCommands.put("downscale", (Scanner s) -> {
      return new Downscale(s.nextInt(), s.nextInt(), s.next(), s.next(), view);
    });
    return knownCommands;
  }
}
