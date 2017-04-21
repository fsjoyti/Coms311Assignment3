import java.util.*;

public class DynamicProgramming {

	private static ArrayList<Integer> returnedList = new ArrayList<Integer>();

	private static int count = 0;

	// TODO remove
	public DynamicProgramming() {

	}

	
	
	public static String stringAlignment(String x, String y) {
		int penalty = 4;
		System.out.println(x);
		System.out.println(y);
		int score = 0;
		String result = "";
		
		int[][] scoringMatrix = new int[y.length()+1][x.length()+1];
		scoringMatrix[0][0]=0;
		
		for (int i = 1; i <= y.length();i++){
			 scoringMatrix[i][0]= 999999;
			
		}
		
		for (int j = 1; j<=x.length();j++){
			
			 scoringMatrix[0][j]= scoringMatrix[0][j-1]+penalty;
			
		}
		
		for (int i = 1; i <= y.length();i++){
			for (int j = 1; j <=x.length(); j++){
				
				
				char first = y.charAt(i-1);
				
				char second = x.charAt(j-1);
				
				//System.out.println(x.charAt(i-1));
				//System.out.println(y.charAt(i-1));
				int match = scoringMatrix[i-1][j-1]+penalty(first,second);
				int insert = scoringMatrix[i][j-1]+penalty;
				int delete = scoringMatrix[i-1][j]+penalty;
				int min = min(match,delete,insert);
				scoringMatrix[i][j] = min;
				
				if (i>j){
					scoringMatrix[i][j] = 999999;
				}
				
		
				
				
				
			}
		}
		
		 for (int i = 0; i <= y.length();i++){
			 for(int j=0; j<= x.length();j++){
				 System.out.print(scoringMatrix[i][j]+" ");
			 }
			 System.out.println();
		 }
		
		int i = y.length();
		int j = x.length();
		int count = 0;
		int length_difference = x.length()-y.length();
		
		/*
		while (i < x.length()&& j < y.length()){
			//System.out.println(i);
			//System.out.println(j);
			 char first  = y.charAt(i-1);
			 char second = x.charAt(j-1);
			 int match = scoringMatrix[i-1][j-1];
			 int insert = scoringMatrix[i][j-1];
			 int delete = scoringMatrix[i-1][j];
			 int currentCell = scoringMatrix[i][j];
			 
			 System.out.println(currentCell);
			 System.out.println(penalty(first,second));
			 
			 if (currentCell == match+penalty(first,second) )
			 {
				 result +=first;
				 i++;
				 j++;
			 }
			 
			 if (currentCell == insert +penalty){
				 j++;
			 }
			 
			 else {
			
				 
				 
					 result +='$';
					 i++;
				 }
				 
			 
			 System.out.println();
			
		
		}
		*/

		while (i > 0 && j > 0){
			
			if ( i > j){
				System.out.println("Y is bigger than X. Sorry we cannot perform string alignment");
				break;
			}
			 char first  = y.charAt(i-1);
			 System.out.println("Value of i "+i);
			 System.out.println("Value of j "+j);
			 
			
			 char second = x.charAt(j-1);
			 int match = scoringMatrix[i-1][j-1];
			 int insert = scoringMatrix[i][j-1];
			 int delete = scoringMatrix[i-1][j];
			 int penalty_value = penalty(first,second);
			 
			 int currentCell = scoringMatrix[i][j];
			 System.out.println(currentCell);
			 
			 
			 
			 if (currentCell == match + penalty_value){
				
				
				
				 
				 
				 
				 
			
				 result += first;
				 
				 
				 
				
				 
				 
			
				 
				 i--;
				 j--;
			 }
			 
			 
			 
			
			 
			 else if (currentCell == delete +penalty ){
				 //result += '$';
				// System.out.println("Inside delete "+i);
				 i--;
				 
			 }
			  
			  
			 else  if (currentCell == insert +penalty ){
					 
					 result += "$";
				
					
					 j--;
					 
				 }
			 
			
			 
			 
			 
			 
			 
			 
			
		 }
		 
		 
		 /*
		 while (i > 0){
			 char first  = y.charAt(i-1);
			
			 result += first;
			 
			
			 i--;
			 
		 }
		*/ 
		 while (j > 0){
			 
			if (i > j){
				break;
			}
			 
			 result += '$';
			
			
			 j--;
			 
		 }
		 
		 
		 
		 
		
		 
		 for (i = 0; i <= y.length();i++){
			 for(j=0; j<= x.length();j++){
				 System.out.print(scoringMatrix[i][j]+" ");
			 }
			 System.out.println();
		 }
		 String finalString = new StringBuilder(result).reverse().toString();
		System.out.println(finalString);
		
	
		return result;
		
	}
	
	/**
	 * Returns a Min-cost vertical cut. Type of this method must be array list
	 * of integers.
	 * 
	 * @param M
	 * @return
	 */

	public static ArrayList<Integer> minCostVC(int[][] M) {
		// List<ArrayList<Integer>> returnedList = new
		// ArrayList<ArrayList<Integer>>();
		int r = 0;
		int c = 0;

		// Go to the last row and find the cell with the min cost.

		int[][] MinCostMatrix = minCostMatrix(M);

		int numRows = MinCostMatrix.length;
		int numCols = MinCostMatrix[0].length;

		// List to maintain the number of equal costs
		ArrayList<Integer> simElements = new ArrayList<Integer>();
		// Set the first element of the last row to be min
		int min = MinCostMatrix[numRows - 1][0];
		// Find the min cost to get to the last row (Min in the last row)
		for (int i = 0; i < numCols; i++) {

			if (MinCostMatrix[numRows - 1][i] < min) {
				min = MinCostMatrix[numRows - 1][i];
				// simElements.add(i);
			}
			/*
			 * else if(MinCostMatrix[numRows - 1][i] == min){ //Add the index of
			 * that element into an arraylist System.out.println(i);
			 * simElements.add(i); }
			 */
		}
		// Find the indices with the same min rating
		for (int i = 0; i < numCols; i++) {

			if (MinCostMatrix[numRows - 1][i] == min) {
				// Add the index of that element into an arraylist
				// System.out.println(i);
				simElements.add(i);
			}

		}

		// List<ArrayList<Integer>> Map = new ArrayList<ArrayList<Integer>>();

		HashMap<Integer, ArrayList<Integer>> Map = new HashMap<Integer, ArrayList<Integer>>();

		// Handle case for multiple paths to calculate cost
		if (simElements.size() > 0) {
			for (int i = 0; i < simElements.size(); i++) {

				int currentColumn = simElements.get(i);
				// Handle edge case when the element exits in the first column
				if (simElements.get(i) == 0) {
					ArrayList<Integer> parent = new ArrayList<Integer>();
					parent.add(MinCostMatrix[numRows - 2][0]);
					parent.add(MinCostMatrix[numRows - 2][1]);
					Map.put(0, parent);
				}

				else if (simElements.get(i) == numCols - 1) {
					ArrayList<Integer> parent = new ArrayList<Integer>();
					parent.add(MinCostMatrix[numRows - 2][numCols - 1]);
					parent.add(MinCostMatrix[numRows - 2][numCols - 2]);
					Map.put(numCols - 1, parent);
				}

				else {
					ArrayList<Integer> parent = new ArrayList<Integer>();
					// if(currentColumn - 1 >0){
					parent.add(MinCostMatrix[numRows - 2][currentColumn - 1]);// }
					parent.add(MinCostMatrix[numRows - 2][currentColumn]);
					parent.add(MinCostMatrix[numRows - 2][currentColumn + 1]);
					Map.put(currentColumn, parent);
				}

			}
		}
		HashMap<Integer, Integer> MinCostMap = new HashMap<Integer, Integer>();

		for (Integer key : Map.keySet()) {

			ArrayList<Integer> myList = Map.get(key);

			Collections.sort(myList);
			// Get the first smallest element
			MinCostMap.put(key, myList.get(0));
		}

		// ArrayList<Integer>
		int minVal = Integer.MAX_VALUE;
		int Colindex = 0;

		for (Integer key : MinCostMap.keySet()) {

			Integer value = MinCostMap.get(key);

			if (value < minVal) {
				minVal = value;
				Colindex = key;
			}

		}

		//System.out.println("The minValue is: " + minVal + " Index is: " + Colindex);
		/*
		 * ArrayList<Integer> vertices = new ArrayList<Integer>();
		 * vertices.add(numRows - 1); vertices.add(Colindex);
		 */
		returnedList.add(numRows - 1);
		returnedList.add(Colindex);

		Traversal(numRows - 1, Colindex, MinCostMatrix);
		// System.out.println(returnedList);
		// Handle case for which there can be multiple min costs:
		// System.out.println("Number of sim elements " +simElements);
		// System.out.println("Length of the matrix " +simElements.size() );
		// System.out.println(min);
		// System.out.println(Arrays.asList(Map));
		// System.out.println(Arrays.asList(MinCostMap));
		//System.out.println("The returned list is: " + returnedList);

		//Inverse the arraylist to output in the right order
		int counter = 0;
		ArrayList<Integer> newList = new ArrayList<Integer>();
		for (int i = returnedList.size() - 1; i >= 0; i--) {

			counter++;
			if (counter == 2) {
				newList.add(returnedList.get(i));
				newList.add(returnedList.get(i + 1));

				counter = 0;
			}
		}
		//System.out.println(newList);

		return newList;

	}

	/**
	 * Executes the traversal from the given cell
	 * 
	 * @param row
	 * @param col
	 * @return
	 */

	private static void Traversal(int row, int col, int[][] M) {

		int numRows = M.length;
		int numCols = M[0].length;
		int minCost = 0;
		while (row != 0) {

			if (col == 0) {

				minCost = minForTwo(M[row - 1][col], M[row - 1][col + 1]);

				if (count == 1) {
					// ArrayList<Integer> vertices = new ArrayList<Integer>();
					returnedList.add(row - 1);
					returnedList.add(col);
					row = row - 1;

					// returnedList.add(vertices);
				} else if (count == 2) {
					// ArrayList<Integer> vertices = new ArrayList<Integer>();
					returnedList.add(row - 1);
					returnedList.add(col + 1);
					row = row - 1;
					col = col + 1;
					// returnedList.add(vertices);
				}

			}

			else if (col == numCols - 1) {
				minCost = minForTwo(M[row - 1][col], M[row - 1][col - 1]);

				if (count == 1) {
					ArrayList<Integer> vertices = new ArrayList<Integer>();
					returnedList.add(row - 1);
					returnedList.add(col);
					row = row - 1;
					col = col;
					// returnedList.add(vertices);
				} else if (count == 2) {
					// ArrayList<Integer> vertices = new ArrayList<Integer>();
					returnedList.add(row - 1);
					returnedList.add(col - 1);
					row = row - 1;
					col = col - 1;
					// returnedList.add(vertices);
				}
			}

			else {
				minCost = minForThree(M[row - 1][col - 1], M[row - 1][col], M[row - 1][col + 1]);

				if (count == 1) {
					ArrayList<Integer> vertices = new ArrayList<Integer>();
					returnedList.add(row - 1);
					returnedList.add(col - 1);
					row = row - 1;
					col = col - 1;
					// returnedList.add(vertices);
				} else if (count == 2) {
					ArrayList<Integer> vertices = new ArrayList<Integer>();
					returnedList.add(row - 1);
					returnedList.add(col);
					row = row - 1;
					// returnedList.add(vertices);
				}

				else if (count == 3) {
					ArrayList<Integer> vertices = new ArrayList<Integer>();
					returnedList.add(row - 1);
					returnedList.add(col + 1);
					row = row - 1;
					col = col + 1;
					// returnedList.add(vertices);
				}

			}

		}
		// return null;

	}

	/**
	 * This method computes the minimum cost to get to a certain destination
	 * 
	 * @param origMatrix
	 * @return
	 */

	private static int[][] minCostMatrix(int[][] origMatrix) {

		// Base case -- fill the first row with the first row of the original
		// matrix

		int numRows = origMatrix.length;
		int numCols = origMatrix[0].length;

		int[][] MinMatrix = new int[numRows][numCols];

		//for (int i = 0; i < numRows; i++) {
		for (int i = 0; i < numCols; i++) {
			MinMatrix[0][i] = origMatrix[0][i];

		}

		for (int i = 1; i < numRows; i++) {

			for (int j = 0; j < numCols; j++) {

				if (j == 0) {

					MinMatrix[i][j] = origMatrix[i][j] + minForTwo(MinMatrix[i - 1][j], MinMatrix[i - 1][j + 1]);
					ArrayList<Integer> al = new ArrayList<Integer>();
					al.add(MinMatrix[i - 1][j]);
					al.add(MinMatrix[i - 1][j + 1]);
					// System.out.println("the array List is: " +al +"for: "
					// +MinMatrix[i][j]);

				} else if (j == numCols - 1) {

					MinMatrix[i][j] = origMatrix[i][j] + minForTwo(MinMatrix[i - 1][j], MinMatrix[i - 1][j - 1]);

					ArrayList<Integer> al = new ArrayList<Integer>();
					al.add(MinMatrix[i - 1][j]);
					al.add(MinMatrix[i - 1][j - 1]);
					// System.out.println("the array List is: " +al +"for: "
					// +MinMatrix[i][j]);

				}

				else {

					MinMatrix[i][j] = origMatrix[i][j]
							+ minForThree(MinMatrix[i - 1][j - 1], MinMatrix[i - 1][j], MinMatrix[i - 1][j + 1]);
					ArrayList<Integer> al = new ArrayList<Integer>();
					al.add(MinMatrix[i - 1][j - 1]);
					al.add(MinMatrix[i - 1][j]);
					al.add(MinMatrix[i - 1][j + 1]);
					// System.out.println("the array List is: " +al +"for: "
					// +MinMatrix[i][j]);

				}
			}

		}
		/*System.out.println("Matrix is: ");
		for (int i = 0; i < MinMatrix.length; i++) {
			for (int j = 0; j < MinMatrix[0].length; j++) {

				System.out.print(MinMatrix[i][j] + " ");
			}
			System.out.println();
		}
*/
		return MinMatrix;

	}

	/**
	 * computes min b/w two nums
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private static int minForTwo(int a, int b) {

		if (a <= b) {
			count = 1;
			return a;
		} else {
			count = 2;
			return b;
		}

	}

	/**
	 * returns min b/w three nums
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	private static int minForThree(int a, int b, int c) {

		if ((a <= b && a <= c)) {
			count = 1;
			return a;
		} else if (b < a && b <= c) {
			count = 2;
			return b;
		}
		else {
			count = 3;
			return c;
		}

	}
	
	private static int min (int a, int b,int c){
		if ((a <= b && a<=c )) {
			return a;
		}
		
		else if (b <=a && b<=c){
			return b;
		}
		
		
		 else {
				
				return c ;
			}
		
		
	}
	private static int penalty(char a, char b){
	if (a==b){
		return 0;
	}
	else if (a=='$'||b=='$'){
		return 4;
	}
	else {
		return 2;
	}
	
	}
	

}


