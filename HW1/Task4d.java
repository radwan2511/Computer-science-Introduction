
import java.util.Scanner;

public class Task4d {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans1 = 0;
        int ans2 = 0;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        n = n-1;
        // counting how many times (n-1) can be subtracted by 2 until it wont substract with 2
        //and then storing the number in ans2
        while (n%2 == 0)
        {
        	ans1 = ans1 + 1;
        	n  = n / 2;
        }
        ans2 = n;
        
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans1);
        System.out.println(ans2);
    }
}