
import java.util.Scanner;

public class Task4a {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans = true;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        
        for (int p=2; p*p<=n & ans; p = p+1)
        {
        	if(n%p == 0)
        		ans = false;
        }

        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
    }
}