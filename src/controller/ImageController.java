package controller;

/**
 * Represents operations that should be offered by a controller to run the image processing.
 * application. Allows users to perform transformations on images through input.
 */
public interface ImageController {

  /**
   * Starts the program by giving control to the controller.
   *
   * @throws IllegalStateException if the controller is unable to perform a command.
   */
  void runProgram() throws IllegalStateException;
}
