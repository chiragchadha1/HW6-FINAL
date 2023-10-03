# Running the Image Processor

There are 3 ways to run the Image Processor program:

1. Using command-line arguments
2. Using a script file
3. Using the GUI

## Acceptable commands

`[file-name]` represents the name of the image in the image processor and it does not need to be the
same as the original file name.
`(file-mask)` represents the name of the image to be masked. This field is optional 


#### Load

```load [file-path] [file-name]```

#### Save

```save [file-path-output] [file-name]```

#### Red component greyscale

```red-component [file-name] (file-mask) [file-name-new]```

#### Green component greyscale

```green-component [file-name] (file-mask) [file-name-new]```

#### Blue component greyscale

```blue-component [file-name] (file-mask) [file-name-new]```

#### Value component greyscale

```value-component [file-name](file-mask) [file-name-new]```

#### Luma component greyscale

```luma-component [file-name] (file-mask) [file-name-new]```

#### Intensity component greyscale

```intensity-component [file-name] (file-mask) [file-name-new]```

#### Flip Horizontally

```horizontal-flip [file-name] (file-mask) [file-name-new]```

#### Flip Vertically

```vertical-flip [file-name] (file-mask) [file-name-new]```

#### Brighten

```brighten [intensity] [file-name] (file-mask) [file-name-new]```
*Positive values represent brightening whereas negative values represent darkening*

#### Greyscale

```greyscale [file-name] (file-mask) [file-name-new]```

#### Sepia

```sepia [file-name] (file-mask) [file-name-new]```

#### Blur

```blur [file-name] (file-mask) [file-name-new]```

#### Sharpen

```sharpen [file-name] (file-mask) [file-name-new]```

#### Downscale

```downscale [width-new] [height-new] [file-name] [file-name-new]```

## Using command-line arguments

1. Ensure that the program arguments in the run configurations of the `RunProcessor` class are
   empty.
2. Run the `RunProcessor` class.
3. Type acceptable commands in console. Invalid commands will return "Invalid command. Please try
   again." and the program will wait for further user input.
4. At any point to quit the program, type `q` or `quit`.

The following is an example sequence of commands:

```java
load img/personal.ppm p
        red-component p red-p
        brighten 30red-p bright-p
        vertical-flip bright-p vertical-p
        sepia vertical-p sepia-p
        blur sepia-p blur-p
        save personal-new.ppm blur-p 
```

## Using a script file

1. Create a text file in the root folder with acceptable commands. Separate each command with a new
   line.
    * An example text file has been provided in the root folder as `script.txt`. It will produce 4
      modified images in the `img/results` folder and it utilizes PPM, JPG, PNG, and BMP file
      formats.
    * Another text file is provided in the root folder as `res-files.txt` which represents examples
      of all possible image processing commands available. It produces the images in the `res`
      folder.
2. In the program arguments in the run configurations of the `RunProcessor` class
   add `-file [file-name.txt]`.
3. Run the `RunProcessor` class.

## Using the GUI

1. Run `ImageProcessor.jar` file located in the res/ folder. This will launch the GUI. 
   1. Load an image using the "File > Load" menu item.
   2. Select an operation to perform on the image using the "Filter" or "Transform" menu items.
   3. Click the "File > Save" menu item to save the image.
   4. To all images loaded double click on the file name in the "File > Image Gallery" menu item.
