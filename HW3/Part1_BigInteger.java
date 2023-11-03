
import java.math.BigInteger;
import java.util.*;

class Part1_BigInteger{

    public static BigInteger sumSmaller(BigInteger n){
        BigInteger sum =  new BigInteger("0");
        BigInteger index =  new BigInteger("1");
        BigInteger bigOne =  BigInteger.ONE;
        
        while(index.compareTo(n)<0) {
        	sum = sum.add(index);
        	index = index.add(bigOne);
        }
        
       	return sum;
    }

    public static void printRandoms(int n){
        //Task 1.2
        Random r = new Random();
    	for(int i=0; i<n;i=i+1) {
    		System.out.println(r.nextInt());
    	}
    }

    public static   boolean isPrime(BigInteger n){ // using the naive algorithm from one of the lectures and using BigInteger class with it
        boolean ans= false;
        //Task 1.3
        if(n.longValue() > 1) {
        	ans = true;
        	BigInteger p = new BigInteger("2");
        	while (n.compareTo(p.multiply(p)) != -1 & ans) {
        		if (n.mod(p).longValue() == 0) {
        			ans = false;
        		}
        		p = p.add(new BigInteger("1"));
        	}
        }
        return ans;
    }

    public static BigInteger randomPrime(int n){ // generating Random prime number between 0 and 2^n
        BigInteger randBig = new BigInteger("0");
        //Task 1.4
        Random r = new Random();
        while (!isPrime(randBig)) // keep getting random number until getting prime number
        {
        	randBig = new BigInteger(n,r);
        }
        return randBig;
    }

    public static void main(String[] args) {
        
    }
}