
import java.util.Scanner;

public class Task3a {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        if(n == 0)
        	ans = 1;
        else
        	ans = 2;
        for(int i = 1; i < n; i = i+1)
        {
        	ans = ans * 2;
        }
        //---------------write your code ABOVE this line only!--------------
        
        System.out.println(ans);
    }
}