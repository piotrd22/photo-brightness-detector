It is a program that checks all pictures (.jpg, .png, .jpeg), converts them and copies them to the output folder with the answer whether it is dark or clear. It also gives the image brightness on a scale of 0-100 (0 - completely white, 100 completely black).

The program initially deletes all photos from the out folder (so you don't have to do it manually), then processes the photos one by one (each pixel) and calculates its luminance. Finally, it returns to the out folder with the footnotes.

The problem may occur when the photo is taken with a completely black background, in black clothes, and with black hair. Then, despite the good visibility of the face, the photo will be identified as dark.

//////////////////////////////////////////////////////////////////////////////////////

To start put your photos (.png, .jpg, .jpeg) in the "In" folder.
Then run it with a simple "sbt run".

You will see a message. To skip it, just press enter, and if you want to change the settings, type the integer and press enter.

The program should properly recognize and create copies of photos with appropriate annotations in the folder "Out".

If you want to identify more pictures, just put them in the "In" folder, you don't need to remove the pictures from the "Out" folder -- the program will delete them itself.
