
import java.util.Scanner;

public class Task4e {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans1 = true;
        int ans2 = 0;

        //---------------write your code BELOW this line only!--------------
        // getting input from user
        int n = scanner.nextInt();
        int b = scanner.nextInt();
        int s = scanner.nextInt();
        int d = scanner.nextInt();
        
        int bd1 = 1; // bd1 = b^d
        int bd2 = 1; // bd2 = b^(2^i)*d
        
        // calculating and checking first condition : if b^d % n = 1
        // using code from task 3b
        if(n == 0)
        	bd1 = 1;
        else
        	bd1 = b;
        for(int i = 1; i < d; i = i+1)
        {
        	bd1 = ((bd1%n)*(b%n))%n;
        }
        
        // check if first condition is true or false and then deside how to proceed
        if(bd1 != 1)
        {
        	int num = 1; // we use this parameter to calculate 2^i
        	boolean bool = true; // we use this boolian to check if the second condition is true or false and to break the for loop 
        	// checking if the condition b^(2^i)*d   % n = n-1 in the range 0<=i<=s-1 is true or false
        	for(int i = 0; i<s & bool; i = i +1,num = num * 2)
        	{
        		bd2 = 1;
                for(int j = 0; j < num*d; j = j+1)
                {
                	bd2 = ((bd2%n)*(b%n))%n;
                }
                
                if (bd2 == (n-1)) // if its true no need to continue checking
                	bool = false;
        		
        	}
        	// updating the result
        	if (bool)
        	{
        		ans1 = false;
        		ans2 = b;
        	}
        	else
        	{
        		ans1 = true;
        		ans2 = -1;
        	}
        }
        else
        {
        	ans1 = true;
        	ans2 = -1;
        }
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans1);
        System.out.println(ans2);
    }
}