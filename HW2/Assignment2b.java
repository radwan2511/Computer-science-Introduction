
public class Assignment2 {

	// functions from part A
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
	
	
	
	/*------------------------
	 *| Part B - tasks 12-20 |
	 * -----------------------*/
	
	// task 12a
	public static int map(int i, int j, int n) {
		return (n*i+j+1);	
	}
	
	// task 12b
	public static int[] reverseMap(int k, int n) {
		int[] ans = new int[2];
		int i = (k-1)/n;
		int j = (k-1)%n;
		ans[0] = i;
		ans[1] = j;
		return ans;
	}
	
	// task 13
	public static int[][] oneCityInEachStep(int n) {
		int[][] answer = new int[n*((n*(n-1)/2)+1)][]; // array size is n * matrix length that exactly one function returns
	    int currIndex = 0;
		for(int i=0;i<n;i=i+1) // with every value of i: i,j,n
		{	
		    int[] clause = new int[n];
		    for(int j=0;j<n;j=j+1){
		        clause[j] = map(i,j,n);
		    }
		    int[][] exactlyOne = exactlyOne(clause);
		    for(int j=0;j<exactlyOne.length;j=j+1,currIndex = currIndex + 1){
		 
		        answer[currIndex] = exactlyOne[j];
		    }
		}
		return answer;
	}

	// task 14
	public static int[][] eachCityIsVisitedOnce(int n) {
		int[][] answer = new int[n*((n*(n-1)/2)+1)][]; // array size is n * matrix length that exactly one function returns
	    int currIndex = 0;
		for(int i=0;i<n;i=i+1) // with every value of i: i,j,n
		{	
		    int[] clause = new int[n];
		    for(int j=0;j<n;j=j+1){
		        clause[j] = map(j,i,n); // exactly like the function above but onlly switching i and j in map function
		    }
		    int[][] exactlyOne = exactlyOne(clause);
		    
		    for(int j=0;j<exactlyOne.length;j=j+1,currIndex = currIndex + 1){
		 
		        answer[currIndex] = exactlyOne[j];
		    }
		}
		return answer;
	}
	
	// task 15
	public static int[][] fixSourceCity(int n) {// fixing source city by sending the literal map(0,0,n) to exactlyOne func 
		int[]  literal = new int[1];
		literal[0] = map(0,0,n); 
		return exactlyOne(literal);
	}
	
	// task 16
	public static int[][] noIllegalSteps(boolean[][] flights) {
		int[][] answer;
	    int answerLength = 0;
	    int[][] answerTmp = new int[flights.length*flights.length][];
		int[][] atMostOne;
		int map1,map2;
		int currIndex = 0;
		for(int i=0;i<flights.length;i=i+1)
		{
			for(int j=0;j<flights.length;j=j+1) {
			        if(i!=j)
					    for(int k = 0;k<flights.length;k=k+1) // cheking if we can fly to j and not to k and the sending thier map to atMostOne func
					    {
						    if(!flights[j][k] & k!=j)
						    {
						    map1 = map(i,j,flights.length);
						    map2 = map((i+1)%flights.length ,k,flights.length);
						    int[] tmp = {map1,map2};
						    atMostOne = atMostOne(tmp);
						    answerTmp[currIndex] = atMostOne[0];
						    currIndex = currIndex+1;
						 }
					}
				
							
				}
			}
		
		// here we copying the answerTmp to answer without the null rows
		for(int i=0;i<answerTmp.length & answerTmp[i]!= null;i=i+1)
		    answerLength = answerLength+1;
		//System.out.println("length = "+answerLength);
		answer = new int[answerLength][];
		for(int i=0;i<answerLength;i=i+1)
		    answer[i] = answerTmp[i];
		
		return answer;
		
		
		
	}
	
	// task 17
	public static int[][] encode(boolean[][] flights) { // getting all cnf matrixes from all aylotsem and putting them in one cnf matrix
		int[][] a = oneCityInEachStep(flights.length);
		int[][] b = eachCityIsVisitedOnce(flights.length);
		int[][] c = fixSourceCity(flights.length);
		int[][] d = noIllegalSteps(flights);
		int[][] answer = new int[a.length + b.length + c.length + d.length][];
		int currIndex = 0;
		for(int i=0;i<a.length;i=i+1,currIndex = currIndex+1)
			answer[currIndex] = a[i];
		
		for(int i=0;i<b.length;i=i+1,currIndex = currIndex+1)
			answer[currIndex] = b[i];
		
		for(int i=0;i<c.length;i=i+1,currIndex = currIndex+1)
			answer[currIndex] = c[i];
		
		for(int i=0;i<d.length;i=i+1,currIndex = currIndex+1)
			answer[currIndex] = d[i];
		
		return answer;		
	}

	// task 18
	public static int[] decode(boolean[] assignment, int n) {
		if(assignment == null)
			throw new IllegalArgumentException("assignment array is null.");		
		if(assignment.length != n*n + 1)
			throw new IllegalArgumentException("assignment array length not equals to ((n*n) +1)");	
		int[] answer = new int[n];
		int[] mapParameters;
		for(int k=1;k<assignment.length;k=k+1)
			if(assignment[k])
			{
				mapParameters = reverseMap(k,n); // using reverse map tp get the j value if the assignment is true and storing it in the answer array
				answer[mapParameters[0]] = mapParameters[1];
			}
		return answer;
	}
	
	// task19
	public static int[] solve(boolean[][] flights) {
	    if(!isLegalInstance(flights))
			throw new IllegalArgumentException("flights matrix is not legal instance.");
		int[] answer = null;
		int nVars = flights.length * flights.length;
		int[][] cnf = encode(flights); // getting the cnf fro flights boolean matrix
		SATSolver.init(nVars);
		SATSolver.addClauses(cnf); // adding all the clauses from cnf to SatSolver
		boolean[] assignment = SATSolver.getSolution() ; // getting the sat solution
		if (assignment == null)
			throw new IllegalArgumentException("TIMEOUT");
		int[] tour = decode(assignment,flights.length); // decoding the answer
		if(isSolution(flights,tour))
		    if (assignment.length == nVars+1) // checking if the answer is SAT
	        	answer = tour;
		else
			throw new IllegalArgumentException("Solution is not legal");
		return answer;
		
		
	}
	
	// help function to swap elements in array. this function is taken from the lecture in the course. source: https://moodle2.bgu.ac.il/moodle/course/view.php?id=31758
	public static void swap(int[] arr, int i, int j) {
	    int temp = arr[i];
	    arr[i] = arr[j];
	    arr[j]  = temp;
	}  
	
	
	// task20
	public static boolean solve2(boolean[][] flights) { // there is no need to check flight and throw exception because  solve function check if its value is legal and throws exception that is needed
	    
		int[] tour = solve(flights);
		boolean isTourLegal = tour != null;
		boolean ans = false;
		if(isTourLegal){
		    int[] isTour = new int[tour.length];
		    for(int i=0;i<tour.length;i=i+1)
		    {
			    isTour[i] = tour[i];
		    }
		    for(int i=1;i<tour.length-1 & !ans ;i=i+1) // swaping value in index i and index i+1 and check if it is solution, if it finds one then there is another answer
		    {
			    swap(isTour,i,i+1);
			    ans = isSolution(flights,isTour);
		    }
		}
		return ans;		
	}
		
}
