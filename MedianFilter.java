/**
 * Program 4
 * Median Filter
 * @author Elizabeth Reddy
 * RedID: 824852061 
 **/

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class MedianFilter {
		
		private BufferedImage filteredImage;
		private BufferedImage[] images;
		
		public MedianFilter(String[] imageInputFilenames) {
		// constructor with array of names of the noisy images
			if (imageInputFilenames != null) {
				int i;
				images = new BufferedImage[imageInputFilenames.length];
				for (i=0; i<imageInputFilenames.length; i++) {

					File f = new File(imageInputFilenames[i]);
					images[i] = readImage(f);
				}
			}
		}

		public BufferedImage readImage(File imageFile) {
		// opens and reads in an image file
			BufferedImage img = null; 
			try {
				img = ImageIO.read(imageFile); // read file
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return img;
		}
		
		public BufferedImage removeNoise() {
		// gets the median value for all pixels and returns the filtered noiseless image
			int i;
			int j;
			int x;
			int k;
			filteredImage = images[0];
			ArrayList<Integer> pixels = new ArrayList<Integer>(images.length);
			for (i=0; i<images.length; i++) {
				pixels.add(0);
			}
			for (i=0; i<filteredImage.getHeight(); i++) {
				for (j=0; j<filteredImage.getWidth(); j++) {
					for (k = 0; k < pixels.size(); k++ ) {
						pixels.set(k, (images[k].getRGB(j, i)));
					}
					x = getMedianValue(pixels);
					
					filteredImage.setRGB(j, i, x);
				}
			}
			return filteredImage;
			
		}
		
		public int getMedianValue(ArrayList<Integer> pixels) {
		// returns the median value of the pixel(x,y) for all images
			int x;
			Collections.sort(pixels);
			x = pixels.get(pixels.size()/2);
			return x;
		}
		
		public int writeImage(String outputFilename) {
		// writes filteredImage to the outputFilename jpg file. Returns 0 if successful, or -1 if an exception was thrown.
			File f2 = new File(outputFilename);
			try {
				ImageIO.write(filteredImage, "jpg", f2);
				return 0;
			} catch (IOException e) {
				System.out.print("Error writing file");
				return -1;
			} // write to file
		}
		
		public int getHeight() {
		// returns height (y-dimension) of filteredImage
			return images[0].getHeight();
		}
		
		public int getWidth() {
		// returns width (x-dimension) of filteredImage
			return images[0].getWidth();
		}
	}
