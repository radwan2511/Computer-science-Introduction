
class Part1_Change{

    public static boolean change(int [] coins, int n){
        boolean ans = false;
        //Task 2.1
        ans = change(coins,n,0);
        return ans;
    }
    
    //check if the sum can be taken from coin, starting at index i.
  	public static boolean change(int[] coin, int sum, int i){
  		boolean ans;
  		if(sum == 0)
  			ans = true;
  		else if(sum < 0 | i >= coin.length)
  			ans = false;
  		else 
  			ans =  change(coin, sum - coin[i], i) || change(coin, sum, i + 1) ;
  		return ans;
  	}

    public static boolean changeLimited(int[] coins, int n, int numOfCoinsToUse){
        boolean ans = false;
        //Task 2.2
        ans = changeLimited(coins,n,numOfCoinsToUse,0);
        return ans;
    }
  //check if the sum can be taken from coin, starting at index i. with the use of exactly numOfCoinsToUse coins
  	public static boolean changeLimited(int[] coin, int sum, int numOfCoinsToUse, int i){
  		boolean ans = false;
  		if(numOfCoinsToUse >= 0) { 
  			if(sum == 0 & numOfCoinsToUse == 0) // I added this condition (numOfCoinsToUse == 0) to make sure i have used exactly numOfCoinsToUse coin and got an answer
  				ans = true;
  			else if(sum < 0 | i >= coin.length)
  				ans = false;
  			else 
  				ans = changeLimited(coin, sum - coin[i],numOfCoinsToUse-1, i) || changeLimited(coin, sum,numOfCoinsToUse, i + 1);
  		}
  		return ans;
  	}

    public static void printChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        //Task 2.3
        printChangeLimited(coins,n,numOfCoinsToUse,0);
    }
    
    // this function is used to remove ',' from the begining of the answer string and prints the answer
    public static void printChangeLimited(int[] coin, int sum, int numOfCoinsToUse, int i) {
    	String ans = printChangeLimited(coin,sum,numOfCoinsToUse,0,"");
    	if(ans.length()>0) {
    		String[] answers = ans.split("\n");
    		System.out.println(answers[0].substring(1)); // printing only one answer
    	}
    }
  //check if the sum can be taken from coin, starting at index i. with the use of exactly numOfCoinsToUse coins and return the string contain the coins used if 
  	public static String printChangeLimited(int[] coin, int sum, int numOfCoinsToUse, int i,String s){
  		String ans = "";
  		if(numOfCoinsToUse >= 0) {
  			if(sum == 0 & numOfCoinsToUse == 0 & !"".equals(s)) {
  				ans = s + "\n";
  				s = "";//I also set s = "" and avoid printing when "".equals(s) in order to avoid counting the same answer multiple times.
  			}
  			else if(sum < 0 | i >= coin.length)
  				ans = "";
  			else {
  				ans = printChangeLimited(coin, sum - coin[i],numOfCoinsToUse-1, i,s+","+ coin[i])+
  				printChangeLimited(coin, sum,numOfCoinsToUse, i + 1,s);
  			}
  		}
  		return ans;
  	}

    public static void main(String[] args){
    

    }
}
