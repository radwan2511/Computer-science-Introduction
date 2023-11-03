
public class Assignment2 {

	
	/*-----------------------
	 *| Part A - tasks 1-11 |
	 * ----------------------*/
	
	// task 1
	public static boolean isSquareMatrix(boolean[][] matrix) {
		if (matrix == null)
			return false;
		boolean isSquare = true;
		// looping over all the rows and check if their length eguals to the matrix length 
		for (int i=0; i<matrix.length & isSquare; i=i+1)
		{
			if(matrix[i].length != matrix.length)
				isSquare = false;
		}
		
		return isSquare;
	}
	
	// task 2
	public static boolean isSymmetricMatrix(boolean[][] matrix) {
	    boolean isSymmetric = true;
		
		// checking if arr[i][j] == arr[i][j] while i!=j in the array and decide if it is symmetric
		for(int i=0;i<matrix.length & isSymmetric;i++)
		{
			for(int j=0;j<matrix.length & isSymmetric;j=j+1)
			{
				if(matrix[i][j] != matrix[j][i])
					isSymmetric = false;
			}
		}
		
		return isSymmetric;
	}

	// task 3
	public static boolean isAntiReflexiveMatrix(boolean[][] matrix) {
		boolean isAntiReflexive = true;
		// checking if for all i value matrix[i][i] is false
		for (int i = 0; i<matrix.length & isAntiReflexive; i=i+1)
		{
			if (matrix[i][i] != false)
				isAntiReflexive = false;
		}
		
		
		return isAntiReflexive;
	}
	
	// task 4
	public static boolean isLegalInstance(boolean[][] matrix) { // check if  matrix is symmetric ,AntiReflexive and square
	    return matrix != null && isSquareMatrix(matrix) && isSymmetricMatrix(matrix) && isAntiReflexiveMatrix(matrix);
	}
	
	// task 5
	public static boolean isPermutation(int[] array) {// checking if all array values are 0-arr.length -1 , and there are no duplicates
		if (array == null)
			return true;
		boolean isPermutation = true;
		for (int i=0; i<array.length & isPermutation; i = i+1)
		{
			boolean found = false;
			// checking if the index value contains in the array and if one index is not found then stoping the loop and return false
			for (int j=0; j<array.length & !found; j = j+1)
			{
				if(i == array[j])
					found = true;
			}
			if(!found)
				isPermutation = false;
		}
		return isPermutation;	
	}
	
	// task 6
	public static boolean hasLegalSteps(boolean[][] flights, int[] tour) { // check if the tour is legal by checking the flight boolean matrix
		boolean isLegal = true;
		
		if (!flights[tour[0]][tour[tour.length - 1]])
			isLegal = false;
		for (int i=0; i<flights.length - 1 & isLegal; i = i+1)
		{
			if (!flights[tour[i]][tour[i+1]])
				isLegal = false;
		}
		
		return isLegal;
	}
	
	// task 7
	public static boolean isSolution(boolean[][] flights, int[] tour) { //check ig the tour has legal steps and Permutation
		return tour != null && tour.length == flights.length && isPermutation(tour) && hasLegalSteps(flights,tour);
	}
	
	// task 8
	public static boolean evaluate(int[][] cnf, boolean[] assign) { // check and return cnf is assignable
		boolean isAssignable = true;
		
		for(int i=0; i<cnf.length & isAssignable; i=i+1)
		{
			isAssignable = evaluateClause(cnf[i],assign);
		}
		
		return isAssignable;
	}
	
	// function return literal assignment value
	public static boolean evaluateLiteral(int literal, boolean[] assign)
	{
		boolean litValue;
		if ( literal > 0)
			litValue = assign[literal];
		else
			litValue = !assign[(-1*literal)];
		return litValue;
	}
	// return clause assignment value
	public static boolean evaluateClause(int[] clause, boolean[] assign)
	{
		boolean isClause = false;
		for (int i=0;i<clause.length & !isClause;i=i+1)
		{
			isClause = evaluateLiteral(clause[i],assign);
		}
		return isClause;
	}
	
	// task 9
	public static int[][] atLeastOne(int[] lits) { // return one literal
		int[][] one = {lits};
		return one;
		}

	// task 10
	public static int[][] atMostOne(int[] lits) { // returning all nigative possible pairs of literals from lits array
		int numOfLits = lits.length;
		int numOfClauses = numOfLits*(numOfLits-1)/2;
		int currIndex = 0;
		int[][] cnf = new int[numOfClauses][numOfLits];
		for (int i = 0; i < lits.length; i = i + 1) {
			for (int j = i + 1; j < lits.length; j=j+1,currIndex=currIndex+1) 
			{
				int[] clause = {-lits[i],-lits[j]};
				cnf[currIndex] = clause;
			}
		}
		return cnf;
	}
	
	// task 11
	public static int[][] exactlyOne(int[] lits) { // return cnf matrix the have only one assignable literal 
	    int[][] cnf = new int[(lits.length * (lits.length - 1) / 2)+1][];

		int[][] one = atMostOne(lits);

		for (int i = 0; i < cnf.length - 1; i++) {
			cnf[i] = one[i];
		}
		cnf[cnf.length - 1] = atLeastOne(lits)[0];

		return cnf;	
	}

	
	
}
