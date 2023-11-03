import java.util.*;
import java.io.*;
class Part2_Change{

    public static int countChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        int ans = 0;
        //Task 2.4
        ans = countChangeLimited(coins,n,numOfCoinsToUse,0,"");
        return ans;
    }
  //check if the sum can be taken from coin, starting at index i. with the use of exactly numOfCoinsToUse coins and return the string contain the coins used if 
  	public static int countChangeLimited(int[] coin, int sum, int numOfCoinsToUse, int i,String s){
  		int ans = 0;
  		if(numOfCoinsToUse >= 0) {
  			if(sum == 0 & numOfCoinsToUse == 0 & !"".equals(s)) {
  				ans = 1;
  				s = "";//I also set s = "" and avoid printing when "".equals(s) in order to avoid counting the same answer multiple times.
  			}
  			else if(sum < 0 | i >= coin.length){
  			    ans = 0;
  			}
  			else if(coin[i] > sum)
  			    ans = 0;
  			else {
  				ans = countChangeLimited(coin, sum - coin[i],numOfCoinsToUse-1, i,s+","+ coin[i]) + 
  						countChangeLimited(coin, sum,numOfCoinsToUse, i + 1,s);	
  			}
  		}
  		return ans;
  	}
    
    public static void printAllChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        //Task 2.5
    	printAllChangeLimited(coins,n,numOfCoinsToUse,0);
    }

    
    // this function is counting the diffrent answer options 
    public static void printAllChangeLimited(int[] coin, int sum, int numOfCoinsToUse, int i) {
     	String ans = "";
     	ans = printAllChangeLimited(coin,sum,numOfCoinsToUse,0,"");
     	if(ans.length() > 0) {
		 	String[] answers = ans.split("\n");
		 	for(int j=0;j<answers.length;j=j+1)
		 		System.out.println(answers[j].substring(1));
		 	}
     }
     
   //check if the sum can be taken from coin, starting at index i. with the use of exactly numOfCoinsToUse coins and return the string contain the coins used if 
   	public static String printAllChangeLimited(int[] coin, int sum, int numOfCoinsToUse, int i,String s){
   		String ans = "";
   		if(numOfCoinsToUse >= 0) {
   			if(sum == 0 & numOfCoinsToUse == 0 & !"".equals(s)) {
   				ans = s+"\n";
   				s = "";//I also set s = "" and avoid printing when "".equals(s) in order to avoid counting the same answer multiple times.
   			}
   			else if(sum < 0 | i >= coin.length)
   				ans = "";
   			else if(coin[i] > sum)
  			    ans = "";
   			else {
   				ans = printAllChangeLimited(coin, sum,numOfCoinsToUse, i + 1,s) + 
   					 printAllChangeLimited(coin, sum - coin[i],numOfCoinsToUse-1, i,s+","+ coin[i]);
   			}
   		}
   		return ans;
   	}
    

    public static int changeInCuba(int cuc){
        int ans = 0;
        //Task 2.6
        ans = changeInCuba(cuc,0);
        return ans;
    }
    
    public static int changeInCuba(int cuc,int i){
    	int ans = 0;
    	int[] toCUP = {1,3,3,5,9,10,15,20,30,50,60,100,150,300};
    	int answers=0; // this parameter get all diffrent possible answers on i number of coins
    	for(i = 1;i<=cuc*3;i=i+1) {
    		answers = countChangeLimited(toCUP,cuc*3,i,0,"");
    		ans = ans + answers;
    	}
    	return ans;
    }
    
    
  	
    public static void main(String[] args){
        
    }
}
