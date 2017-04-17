import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageProcessor {
	int H = 0;
	int W = 0;
	/**
	 * Parameter imageFile holds the name of the image
	 * that will be manipulated. We will use W to denote the width and H to denote the height of the
	 * image.
	 * @param imageFile
	 * @throws MalformedURLException 
	 */
	public ImageProcessor(String imageFile) throws MalformedURLException{
		
		//Initilize picture
		Picture picture = new Picture(imageFile);
		//get the data
		H = picture.height();
		W = picture.width();
		//Make an array to hold the values
		int[][] pixelData = new int[H][W];
		Color color;
		//Add the rgb data into pixelData
		for(int i = 0; i<H; i++){
			for(int j = 0; j<W; j++){
				//Take care! Add only RGB values. Make pixelData to be a array holding the Seperate RGB values 
				color = picture.get(j, i);
				System.out.println(pixelData[i][j]);
			}
		}
	}
	/**
	 * Returns a new Picture whose width is dx × We. Note that the type
	 * of this method must be Picture. Your method must use the algorithm described earlier to reduce
	 * the image width.
	 * @param x
	 * @return
	 */
	 public Picture reduceWidth(double x) {
		 
		 
		 return null;
	 }
}
