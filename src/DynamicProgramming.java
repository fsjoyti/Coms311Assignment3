import java.util.*;

public class DynamicProgramming {
	
	
	//TODO remove
	public DynamicProgramming(){
		
		
		
	}
	
	
	/**
	 * Returns a Min-cost vertical cut. Type of this method must be array list
	 * of integers.
	 * 
	 * @param M
	 * @return
	 */

	public static ArrayList<Integer> minCostVC(int[][] M) {
		
		//Go to the last row and find the cell with the min cost. 
		
		int[][] MinCostMatrix = minCostMatrix(M);
		
		int numRows = MinCostMatrix.length;
		int numCols = MinCostMatrix[0].length;
		
		//List to maintain the number of equal costs 
		ArrayList<Integer> simElements = new ArrayList<Integer>();
		//Set the firt element of the last row to be min
		int min = MinCostMatrix[numRows - 1][0];
		//Find the min cost to get to the last row (Min in the last row)
		for(int i = 1; i< numCols; i++){
			
			if(MinCostMatrix[numRows - 1][i] < min){
				min = MinCostMatrix[numRows - 1][i];
				//simElements.add(i);
			}
			/*else if(MinCostMatrix[numRows - 1][i] == min){
				//Add the index of that element into an arraylist
				System.out.println(i);
				simElements.add(i);
			}*/
		}
		//Find the indices with the same min rating
		for(int i = 1; i< numCols; i++){
			
			 if(MinCostMatrix[numRows - 1][i] == min){
				//Add the index of that element into an arraylist
				//System.out.println(i);
				simElements.add(i);
			}
			
		}
		
		//List<ArrayList<Integer>> Map = new ArrayList<ArrayList<Integer>>();
		
		HashMap<Integer, ArrayList<Integer>> Map = new HashMap<Integer, ArrayList<Integer>>();
		
		
		//Handle case for multiple paths to calculate cost
		if(simElements.size()>1){
			for(int i = 0; i<simElements.size(); i++){
				
				int currentColumn = simElements.get(i);
				//Handel edge case when the element exits in the first column
				if(simElements.get(i) == 0){
					ArrayList<Integer> parent = new ArrayList<Integer>();
					parent.add(MinCostMatrix[numRows - 2][0]);
					parent.add(MinCostMatrix[numRows - 2][1]);
					Map.put(0, parent);
				}
				
				
				if(simElements.get(i) == numCols - 1){
					ArrayList<Integer> parent = new ArrayList<Integer>();
					parent.add(MinCostMatrix[numRows - 2][numCols - 1]);
					parent.add(MinCostMatrix[numRows - 2][numCols - 2]);
					Map.put(numCols - 1, parent);
				}
				
				else{
					ArrayList<Integer> parent = new ArrayList<Integer>();
					parent.add(MinCostMatrix[numRows - 2][currentColumn - 1]);
					parent.add(MinCostMatrix[numRows - 2][currentColumn]);
					parent.add(MinCostMatrix[numRows - 2][currentColumn + 1]);
					
					Map.put(currentColumn, parent);
				}
				
			}
		}
		
		//Handle case for which there can be multiple min costs:
		//System.out.println("Number of sim elements " +simElements);
		//System.out.println("Length of the matrix " +simElements.size() );
		//System.out.println(min);
		System.out.println(Arrays.asList(Map));
		return null;

	}

	/**
	 * This method computes the minimum cost to get to a certain destination
	 * 
	 * @param origMatrix
	 * @return
	 */
	
	public static int[][] minCostMatrix(int[][] origMatrix) {

		// Base case -- fill the first row with the first row of the original
		// matrix
		
		int numRows = origMatrix.length;
		int numCols = origMatrix[0].length;

		int[][] MinMatrix = new int[numRows][numCols];

		for (int i = 0; i < numRows; i++) {

			MinMatrix[0][i] = origMatrix[0][i];

		}

		for (int i = 1; i < numRows; i++) {

			for (int j = 0; j < numCols; j++) {

				if (j == 0) {

					MinMatrix[i][j] = origMatrix[i][j] + minForTwo(MinMatrix[i - 1][j], MinMatrix[i - 1][j + 1]);
					ArrayList<Integer> al = new ArrayList<Integer>();
					al.add(MinMatrix[i - 1][j]);
					al.add(MinMatrix[i - 1][j + 1]);
					//System.out.println("the array List is: " +al +"for: " +MinMatrix[i][j]);
					
					
					
				}
				else if (j == numCols - 1) {

					MinMatrix[i][j] = origMatrix[i][j] + minForTwo(MinMatrix[i - 1][j], MinMatrix[i - 1][j - 1]);
					
					ArrayList<Integer> al = new ArrayList<Integer>();
					al.add(MinMatrix[i - 1][j]);
					al.add(MinMatrix[i - 1][j - 1]);
					//System.out.println("the array List is: " +al +"for: " +MinMatrix[i][j]);
					
					
				}
				
				else{
					
					MinMatrix[i][j] = origMatrix[i][j] + minForThree(MinMatrix[i-1][j-1], MinMatrix[i-1][j], MinMatrix[i-1][j+1]);
					ArrayList<Integer> al = new ArrayList<Integer>();
					al.add(MinMatrix[i - 1][j - 1]);
					al.add(MinMatrix[i - 1][j]);
					al.add(MinMatrix[i - 1][j + 1]);
					//System.out.println("the array List is: " +al +"for: " +MinMatrix[i][j]);
					
				}
			}

		}
		System.out.println("Matrix is: ");
		for(int i = 0; i< MinMatrix.length; i++){
			for(int j = 0; j< MinMatrix[0].length; j++){
				
				System.out.print(MinMatrix[i][j] + " ");
			}
			System.out.println();
		}
		
		return MinMatrix;

	}

	/**
	 * computes min b/w two nums
	 * @param a
	 * @param b
	 * @return
	 */
	private static int minForTwo(int a, int b) {

		if (a <= b)
			return a;

		else
			return b;

	}

	/**
	 * returns min b/w three nums
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	private static int minForThree(int a, int b, int c) {

		if ((a <= b && a <= c))
			return a;

		else if (b < a && b <= c)
			return b;

		else
			return c;

	}
	
	private class Coordinate{
		private int X;
		private int Y;
		
		public Coordinate(int X, int Y){
			this.X = X;
			this.Y = Y;
			
		}
		
		public int getX(){
			return X;
		}
		public int getY(){
			return Y;
		}

		public boolean equals(Coordinate t){
			   if(t==null){
				   return false;
			   }
			   return X == t.X && Y == t.Y;
		   }
	}
}
