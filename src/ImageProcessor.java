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
	Picture newPicture;
	int width = 0;
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
		 int w_temp = W;
		 int h_temp = H;
		 Picture pic = picture;
		 //newPicture = picture;
		 width = (int) (W*x);
		 for(int m = w_temp - 1; m >= w_temp *x ; m --){
			 
			 
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
			 
			 
			 
			 pic = modifiedPic(picture, minImportanceCost, x, m);
			 
			 picture = pic;
			 H = picture.height();
			 W = picture.width();
			 
		 }
		
		 //Picture newPicture = picture;
		
		 
		 //newPicture.save("newPic.jpg");
		 //System.out.println("New picture: ");
		 picture.show();
		 return picture;
	 }

	// private compute
	 
	 private Picture modifiedPic(Picture pic, ArrayList<Integer> minImpCost, double x, int width){
		 
		 Picture newPic = new Picture(width, H);
		 int flag;
		 int counter = 1;
		 for(int i = 0; i< H; i++){ // this would be height of the old picture
			 flag = 0;
			 //Get j coord to remove
			 int yCoord = minImpCost.get(counter);
			 counter = counter + 2;
			 
			 for(int j = 0; j<W; j++){
				 //int flag = 0;
				 if(j != yCoord){
					 
					 newPic.set(j - flag, i, pic.get(j , i));
				 }
				 else{
					 flag = 1;
				 }
			 }
		 }
		 
		 //Color c = new Color(255);
		// pic.set((int) ((pic.width()) - 1), pic.height() - 1, c);
		 //newPicture = new Picture(newPic.width(), newPic.height());
		 //newPicture = newPic;
		 //pic.show();
		 
		 return newPic;
		 
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
