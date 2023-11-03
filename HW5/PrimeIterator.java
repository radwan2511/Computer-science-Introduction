/*---------------------------------------
 Genuine author: <name>, I.D.: <id number>
 Date: xx-xx-2020 
---------------------------------------*/
import java.util.Iterator;

public class PrimeIterator implements Iterator<Integer> {

    private List<Integer> primes;
   
	//Complete the following methods
    public PrimeIterator(){
    	// task 0
    	//throw new UnsupportedOperationException("Delete this line and implement this function");
		primes = new DynamicArray<Integer>();
		primes.add(2);
    }

    public boolean hasNext(){
    	// task 0
    	return primes != null & primes.get(primes.size()-1)>0;
    }

    public Integer next(){
    	// task 0
    	if(!hasNext())
			throw new IllegalArgumentException("primes is null.");
		//if(primes.size() == 0) // starting with two the first prime number
		//{
		//	primes.add(2);
		//}
		//else { // getting the next prime number
		//	primes.add(nextPrime(primes.get(primes.size()-1)));
		//}
    	primes.add(nextPrime(primes.get(primes.size()-1)));
		return primes.get(primes.size()-2);
    }
    
 // Function that returns true if n  
 // is prime else returns false  
 private boolean isPrime(int n)  
 {  
     // Corner cases  
     if (n <= 1)  return false;  
     if (n <= 3)  return true;  
     
     // This is checked so that we can skip   
     // middle five numbers in below loop  
     if (n%2 == 0 || n%3 == 0) return false;  
     
     for (int i=5; i*i<=n; i=i+6)  
         if (n%i == 0 || n%(i+2) == 0)  
            return false;  
     
     return true;  
 }  
   
 // Function to return the smallest 
 // prime number greater than N 
 private int nextPrime(int n) 
 { 
   
     // Base case 
     if (n <= 1) 
         return 2; 
   
     int prime = n; 
     boolean found = false; 
   
     // Loop continuously until isPrime returns 
     // true for a number greater than n 
     while (!found) { 
         prime++; 
   
         if (isPrime(prime)) 
             found = true; 
     } 
   
     return prime; 
 } 
    
    
}
