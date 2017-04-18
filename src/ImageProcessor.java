import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageProcessor {
	int H = 0;
	int W = 0;
	Picture picture;

	// int[][] pixelData;
	/**
	 * Parameter imageFile holds the name of the image that will be manipulated.
	 * We will use W to denote the width and H to denote the height of the
	 * image.
	 * 
	 * @param imageFile
	 * @throws MalformedURLException
	 */
	public ImageProcessor(String imageFile) {

		// Initilize picture
	    picture = new Picture(imageFile);
		// get the data
		H = picture.height();
		W = picture.width()-1;
		// Make an array to hold the values
		// pixelData = new int[H][W];
		Color color;
		// Add the rgb data into pixelData

		/*
		 * for(int i = 0; i<H; i++){ //H for(int j = 0; j<W; j++){ //W
		 * 
		 * color = picture.get(j, i);
		 * 
		 * pixelData[i][j] = color.getRGB();
		 * 
		 * //System.out.print(pixelData[i][j]); } //System.out.println(); }
		 */
	}

	/**
	 * Returns a new Picture whose width is x × W. Note that the type
	 * of this method must be Picture. Your method must use the algorithm described earlier to reduce
	 * the image width.
	 * @param x
	 * @return
	 */
	 public Picture reduceWidth(double x) {
		 
		 for(int m = W - 1; m >= W *x ; m --){
			 
			 int[][] Importance = new int[H][W];
			 //Compute a Matrix named I, where I[i, j] = Importance(M[i, j]).
			 for(int i = 0; i<H; i++){
				 for(int j = 0; j<W; j++){
					 Importance[i][j] = Importance(i, j);
				 }
			 }
			 
			 ArrayList<Integer> minImportanceCost = new ArrayList<Integer>();
			 
			 //DynamicProgramming dp = new DynamicProgramming();
			 minImportanceCost = DynamicProgramming.minCostVC(Importance);
			 
			 modifiedPic(picture, minImportanceCost, x);
			 
			 
		 }
		
		 //Picture newPicture = picture;
		
		 
		 //newPicture.save("newPic.jpg");
		 //System.out.println("New picture: ");
		 picture.show();
		 return picture;
	 }

	// private compute
	 
	 private Picture modifiedPic(Picture pic, ArrayList<Integer> minImpCost, double x){
		 
		 
		//for(int j = W - 1; j >= W *x ; j --){
			
			for(int i = 0; i<minImpCost.size(); i = i+2){
				 int xCoord = minImpCost.get(i);
				 int yCoord = minImpCost.get(i+1);

				 pic.set(yCoord, xCoord, new Color(0));//pic.get(yCoord - 2, xCoord));
			 }
			 
		 
		 Color c = new Color(255);
		 pic.set((int) ((pic.width()) - 1), pic.height() - 1, c);
		 
		 //pic.show();
		 return pic;
		 
	 }
	 
	 
	private int Importance(int i, int j) {

		int Importance = -1;
		int Yimportance = -1;
		int Ximportance = -1;

		if (i == 0) {
			Yimportance = Dist(picture.get(j, H - 1), picture.get(j, i + 1));
		}

		else if (i == H - 1) {
			Yimportance = Dist(picture.get(j, i - 1), picture.get(j, 0));
		}

		else {
			Yimportance = Dist(picture.get(j, i - 1), picture.get(j, i + 1));
		}

		if (j == 0) {
			Ximportance = Dist(picture.get(W - 1, i), picture.get(j + 1, i));
		}

		else if (j == W - 1) {
			Ximportance = Dist(picture.get(0, i), picture.get(j - 1, i));
		} else {
			Ximportance = Dist(picture.get(j - 1, i), picture.get(j + 1, i));
		}
		Importance = Ximportance + Yimportance;

		return Importance;
	}

	/**
	 * Compute the distance
	 * 
	 * @param c1
	 * @param c2
	 * @return
	 */
	private int Dist(Color c1, Color c2) {

		// Calculate distance
		int dist = 0;

		dist = (c1.getRed() - c2.getRed()) * (c1.getRed() - c2.getRed())
				+ (c1.getBlue() - c2.getBlue()) * (c1.getBlue() - c2.getBlue())
				+ (c1.getGreen() - c2.getGreen()) * (c1.getGreen() - c2.getGreen());
		return dist;

	}
}
