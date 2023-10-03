# Image Processor

Allows users to load images from a file and perform a variety of image processing operations
on them.

# Authors

- [Chirag Chadha](https://www.github.com/chiragchadha1)
- [Rhyme Dochtermann](https://github.com/RhymeTheRapper)

# Running the program

There are 3 ways to run the Image Processor program:

1. Using command-line arguments
2. Using a script file
3. Using a JAR file

*See USEME for more information on acceptable commands and documentation on how to run the program.*

# Code Structure

## Class Diagram

![Class Diagram](class-diagram.png)

## 1. Model

The model controls the logic of the image processor. It also has a gallery that the user loads
images into.
The model has three interfaces:

### RGB

- Represents an RGB value, four integers put together.
- RGBImpl implements RGB and has a constructor to represent an RBG value with and without the alpha
  channel. It has methods that
  returns the RGB values individually

### SingleImage

- Represents an image with an array of RGB pixels.
- SingleImageImpl implements SingleImage and has a constructor that has a width, height, maxValue (
  max RGB pixel value), and a 2D array of RGB pixels. It can only be constructed if the width and
  height are valid and if there is no RGB pixel that is above the maxValue. It has methods that
  return the various fields and a method that returns a specified pixel’s RGB value.

### Transformations

- Represents transformations the user can make on an image
- TransformationsImpl implements Transformations and allows the user to transform the image by
  turning it to greyscale in red component, green component, blue component, value component,
  luminosity component, intensity component, turn it to sepia, brighten or darken the image, or flip
  it vertically or
  horizontally.

### Filter

- Represents color filter operations utilizing that can be applied on images.
- Utilizes a kernel of numbers represented in a matrix format to do so.

### Gallery

- A gallery of images controlled by a hash map.
- GalleryImpl implements Gallery and has methods that put the image in the gallery with a specified
  name, gets the image from the gallery with a specified name, and returns the entire gallery.

## 2. Controller

Represents operations that should be offered by a controller to run the image processing
application.
The Controller has three interfaces:

### ImageController

- Represents operations that should be offered by a controller to run the image processing
  application.
- ImageControllerImpl implements ImageController and has a constructor that takes in an image
  gallery, view, and user input. It has a go method that accounts for the various user inputs like
  load, save, transformations, and quiting and transmits them to the commands and view.

### ImageControllerGUI

- Represents a GUI implementation of an ImageController.
- Implements the ImageViewActions interface which acts as the mediator between the view and the
  controller.
- Performs commands on the image and calls on the model ImageGallery to do so.

### ImageCommands

- Represents commands used by the controller.
- It has various command classes that implement the ImageCommands Interface.
- The utility command load takes in a file, a name for a file, and a view. It loads the file into a
  single image and puts it into the gallery. It then transmits a message back to the user through
  the view confirming this.
- The utility command save takes in a single image, view, and file to output the image to. It saves
  the image to the specified file and transmits a message back to the user confirming the save.
- The various transformation commands take in a single image, a new name to specify the new image
  as, and a view. They perform the transformation on the image and transmits a message back to the
  user confirming the action.

### ImageUtils

- Contains utility methods for the controller
- ImageUtilsImpl implements ImageUtils and contains two methods
- loadImage loads a given image from a file name through the ImageGallery
- saveImage writes a given image to a file through the ImageGallery

## 3. View

- The view prints out messages to the user. It contains one interface:

### ImageView

- Represents the text view for the image processor
- ImageViewImpl implements ImageView and has a constructor that takes in an ImageGallery and
  appendable object. It has a method that renders messages back to the controller.

### ImageViewActions

- Represents an ImageViewActions interface to handle user input from the GUI and update the model
  and gallery accordingly.

### ImageViewGUI

- Represents an ImageViewGUI interface to display the GUI of the image processing app.
- Handles all user interactions with the GUI and filtering/transformation of images.

# Code Changes

## Assignment 5

- Added support for more conventional file formats (JPG, JPEG, PNG, BMP, etc.)
    - Created seperate image loaders (PPM and conventional) to handle different loading methods
- Added new image filtering and transformations (greyscale, sepia, blur, & sharpen)
    - Created a `Filter` class that extends `TransformationsImpl` to improve readability and follow
      SOLID principles
    - Created a `colorTransformation()` method that transforms images by color matrixes like sepia
      and grayscale
    - Added tests for new filter and transformation methods
- Added support to run the program via scripts and a JAR
- Created a new constructor in `RGBImpl` to use an alpha channel
    - Refractored alpha channel throughout the model so that png's can have transparency correctly
- Abstracted color component methods in `TransformationsImpl` so they are handled by a single method
- Abstracted the run method in `ControllerImpl` so that the part that handles known commands is
  contained within a private method
    - Added more specific Exception catches so they are not handled by a single catch block

## Assignment 6

- Added support for GUI.
    - Created a new interface 'ImageViewGUI' which is implemented by `ImageViewGUIImpl`.
    - Created a new interface `ImageViewActions` to act as a mediator between the controller and
      view.
- Added support for downscaling images by a given width and height.
    - Added a 'downscale' method to `Transformations` which was implemented in `TransformationsImpl`
      .
    - Created a 'Downscale' class to be used by the `ImageCommands` interface, following the command
      design pattern.
- Added a `HistogramModel` that outputs a histogram of the current image to the view
- Updated the `setRGB()` method in `ImageUtilsIO` to be protected so it can be used by the GUI to
  convert a `SingleImage` to a `BufferedImage`.
- Added a `getGallery()` method in `ImageGallery` to return the gallery in the GUI.
- Updated the documentation for `Transformations`, `TransformationsImpl`, and `Filter` to be more
  descriptive.
- Made the `RunProcessor` main method run the GUI when no arguments are passed in.
- Updated the USEME file to include a detailed description of the GUI's usage.
- Changed the if-statements in `RunProcessor` to a switch-case statement to better handle the
  different commands (text, file, and GUI).
- Changed `ImageControllerImpl` KnownCommands method so that scanner splits the line by spaces for
  the commands that can use an optional mask
- Added support for masking images by making a mask an optional input in the controller.
- Changed the commands, `Filter`, and `TransformationsImpl` to support an optional mask input.
- Mask works by only changing the pixels of an image where the mask image is black.

# Image Citation

The image `img/personal.ppm` is owned by group member Rhyme Dochtermann and she has given all
copyrights to be used in this project.

The image `img/vadim-sadovsk.jpg` is owned by [Vadim Sadovski](https://unsplash.com/@vadimsadovski)
on [Unsplash](https://unsplash.com/) and is available for free
under [Unsplash License](https://unsplash.com/license).

The image `img/monique-sherar.bmp` is owned by [Monique Sherar](https://unsplash.com/@monsherar)
on [Unsplash](https://unsplash.com/) and is available for free
under [Unsplash License](https://unsplash.com/license).