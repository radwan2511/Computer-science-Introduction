

import java.util.Iterator;

public class BinaryNumber implements Comparable<BinaryNumber>{
    private static final BinaryNumber ZERO = new BinaryNumber(0);
    private static final BinaryNumber ONE = new BinaryNumber(1);

    private BitList bits;

    // Copy constructor
    //Do not chainge this constructor
    public BinaryNumber(BinaryNumber number) {
        bits = new BitList(number.bits);
    }

    //Do not change this constructor
    private BinaryNumber(int i) {
        bits = new BitList();
        bits.addFirst(Bit.ZERO);
        if (i == 1)
            bits.addFirst(Bit.ONE);
        else if (i != 0)
            throw new IllegalArgumentException("This Constructor may only get either zero or one.");
    }

    //Do not change this method
    public int length() {
        return bits.size();
    }

    //Do not change this method
    public boolean isLegal() {
        return bits.isNumber() & bits.isReduced();
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.1 ================================================
    public BinaryNumber(char c) {
        if(!Character.isDigit(c))
    		throw new IllegalArgumentException("input argument is nor digit.");
    	int num = c - '0';
    	bits = new BitList();
    	if(num == 0)
    		bits.addFirst(Bit.ZERO);
    	else {
	    	int len = (int)(Math.log(num) / Math.log(2)) + 2; // length of binary number array
	    	int binary[] = new int[len];
	        int index = 0;
	        while(num > 0){
	          binary[index++] = num%2;
	          num = num/2;
	        }
	        for(int i = index-1;i >= 0;i--){
	        	bits.addFirst(new Bit(binary[i]));
	        }
	        bits.addLast(new Bit(0));
    	}
    }

  //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.2 ================================================
    public String toString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        String ans = bits.toString();
        return ans.substring(1,ans.length()-1); // removing < and > from the string
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.3 ================================================
    public boolean equals(Object other) {
        boolean ans = true;
    	ans = this.toString().equals(other.toString()) & this.getClass().equals(other.getClass()); // boolean check if equals value and class
    	return ans;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.4 ================================================
    public BinaryNumber add(BinaryNumber addMe) {
        if (!addMe.isLegal())
    		throw new IllegalArgumentException("other input is illegal.");
    	BinaryNumber ans = new BinaryNumber(0);
    	ans.bits.removeLast();
    	Bit carry = new Bit(0);
    	
    	// boolean of result is positive or negative
    	boolean positive = addMe.bits.getLast().equals(Bit.ZERO) & bits.getLast().equals(Bit.ZERO);
    	positive = positive | (addMe.bits.getLast().equals(Bit.ZERO) & addMe.bits.size() > bits.size());
    	positive = positive | (bits.getLast().equals(Bit.ZERO) & bits.size() > addMe.bits.size());
    	
    	// making the two bits same size
    	bits.padding(addMe.bits.size());
    	addMe.bits.padding(bits.size());
    	
    	Iterator<Bit> current = bits.iterator();
    	Iterator<Bit> currentAddMe = addMe.bits.iterator();
    	
    	while (current.hasNext() & currentAddMe.hasNext()) {
    		Bit a = current.next();
    		Bit b = currentAddMe.next();
    		ans.bits.addLast(Bit.fullAdderSum(a, b, carry)); //adding sum
    		carry = Bit.fullAdderCarry(a, b, carry); // updating the carry
    	}
    	
    	if(carry.toInt() != 0  & addMe.bits.equals(bits)) {
    		ans.bits.addLast(carry); // adding the carry if its 1
    	}
    	if(positive) {
    		ans.bits.addLast(new Bit(0));
    	}
    	// restoring and reducing the bits to legal length
    	ans.bits.reduce();
    	bits.reduce();
    	addMe.bits.reduce();
    	return ans;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.5 ================================================
    public BinaryNumber negate() {
    	BinaryNumber ans = new BinaryNumber(0);
    	ans.bits = this.bits.complement(); // using complement function to turn every bit from zero to one and from one to zero
    	ans = ans.add(ONE);// adding one to the result
    	return ans;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.6 ================================================
    public BinaryNumber subtract(BinaryNumber subtractMe) {
        if (!subtractMe.isLegal())
    		throw new IllegalArgumentException("other input is illegal.");
    	return add(subtractMe.negate()); // simply summing: this + (-subtractMe)
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.7 ================================================
    public int signum() {
        if (!isLegal())
    		throw new IllegalArgumentException("bits is illegal.");
    	int ans = 0; // check and return if number is zero or positive or negative
    	if(bits.size() == 1)
    		ans = 0;
    	else if(bits.getLast().equals(Bit.ZERO))
    		ans = 1;
    	else if(bits.getLast().equals(Bit.ONE))
    		ans = -1;
    	return ans;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.8 ================================================
    public int compareTo(BinaryNumber other) {
        if (other == null)
    		throw new IllegalArgumentException("input argument is null.");
    	if (!isLegal())
    		throw new IllegalArgumentException("other is illegal.");
        int ans = 0;
        int sign1 = signum(); // first comparing by sign
        int sign2 = other.signum();
        if (sign1 == sign2) {
        	if(sign1 == 1) {
		        if(!bits.equals(other.bits)) { //then comparing by size of the numbers
		        	if(bits.size() > other.bits.size())
		        		ans = 1;
		        	else if(bits.size() < other.bits.size())
		        		ans = -1;
		        	else {
		        			ans = toString().compareTo(other.toString()); // if same size and sign comparing by bigger string using string built in compare to function
		        		}
		        	}
        	}
		    else
		    {
		    	if(bits.size() > other.bits.size())
	        		ans = -1;
	        	else if(bits.size() < other.bits.size())
	        		ans = 1;
	        	else {
	        			ans = toString().compareTo(other.toString());
	        		}
	        	}
        }
        else
        	if(sign1 > sign2)
        		ans = 1;
        	else
        		ans = -1;
    
        return ans;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.9 ================================================
    public int toInt() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        int ans = 0;
        if(!(signum() == 0)) {
	    	for(int i=0; i < bits.size()-1; i=i+1) {
	    		int digit = bits.get(i).toInt();
	    		if((digit != 0 & i == 31 & signum() == 1) | (digit != 0 & i == 32 & signum() == -1) | (i == 31 & ans > 0))
	    			throw new RuntimeException("binary number value is larger than integer max value.");
	    		int add = (int)(Math.pow(2, i) * digit);
	    		ans += add;
	    		if(digit != 0 & i == 31 & signum() == -1)
	    			ans = -2147483648;
	    	}
	        if(signum() == -1 & ans != -2147483648)
	        	ans = ans - (int)(Math.pow(2, bits.size()-1));
        }
        return ans;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.10 ================================================
    // Do not change this method
    public BinaryNumber multiply(BinaryNumber multiplyMe) {
    	if (multiplyMe == null)
    		throw new IllegalArgumentException("input argument is null.");
    	if (!isLegal()) // Do not change this line
            throw new IllegalArgumentException("input argument is illegal."); 
            
    	BinaryNumber ans = null; // sending only positive binary number to multipypositive funtion and then updating the answer sign
    	if(signum() == 0 | multiplyMe.signum() == 0)
    		ans = new BinaryNumber(0);
    	else if(signum() == multiplyMe.signum()) {
    		if(signum() == -1) 
    			ans = negate().multiplyPositive(multiplyMe.negate());
    		else
    			ans = multiplyPositive(multiplyMe);
    	}
    	else {
    		if(signum() == -1)
    			ans = negate().multiplyPositive(multiplyMe).negate();
    		else
    			ans = multiplyPositive(multiplyMe.negate()).negate();
    	}
    	return ans;
    }
    
    private BinaryNumber multiplyPositive(BinaryNumber multiplyMe) {
    	return multiply(this,multiplyMe); // calling for the multiply recursive function
    }
    
    public BinaryNumber multiply(BinaryNumber x, BinaryNumber y) {
    	BinaryNumber ans = ZERO;
    	if (!(x.equals(ZERO) || y.equals(ZERO))) { // if one of the numbers is zero then result is zero
    		if(y.equals(ONE)) // if y == 1 the the result is x
    			ans = x;
    		else if(y.bits.getFirst().equals(Bit.ONE)) // if y binary number is odd then adding x value to result
    			ans = (multiply(x.multBy2(), y.divBy2())).add(x);
    		else
    			ans = (multiply(x.multBy2(), y.divBy2())); // if y binary number is even calling for the function again with updated values 
        }
    	return ans;
    }
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.11 ================================================
     // Do not change this method
    public BinaryNumber divide(BinaryNumber divisor) {
    	if (divisor == null)
    		throw new IllegalArgumentException("input argument is null.");
    	// Do not remove or change the next two lines
    	if (divisor.equals(ZERO)) // Do not change this line
            throw new RuntimeException("Cannot divide by zero."); // Do not change this line
    	//
    	BinaryNumber ans = null; // sending only positive binary number to dividePositive funtion and then updating the answer sign
    	if(signum() == 0 | divisor.signum() == 0)
    		ans = new BinaryNumber(0);
    	else if(signum() == divisor.signum()) {
    		if(signum() == -1) 
    			ans = negate().dividePositive(divisor.negate());
    		else
    			ans = dividePositive(divisor);
    	}
    	else {
    		if(signum() == -1)
    			ans = negate().dividePositive(divisor).negate();
    		else
    			ans = dividePositive(divisor.negate()).negate();
    	}
    	return ans;
    }

    private BinaryNumber dividePositive(BinaryNumber divisor) {
        return divide(this,divisor);
    }
    
    private BinaryNumber divide(BinaryNumber b1,BinaryNumber b2) {
    	
    	BinaryNumber x = new BinaryNumber(b1);
    	BinaryNumber y = new BinaryNumber(b2);
    	
    	BinaryNumber mask = new BinaryNumber(1);
    	BinaryNumber quotient = new BinaryNumber(0);
 
        while (y.compareTo(x) != 1) 
        {
            y.bits.shiftLeft();
            mask.bits.shiftLeft();
        }
 
        while (mask.compareTo(ONE) == 1) 
        {
        	y.bits.shiftRight();
            mask.bits.shiftRight();
            if (x.compareTo(y) != -1) 
            {
                x = x.subtract(y);
                
                int maxLen = (int)Math.max(quotient.length(), mask.length());
                
                BinaryNumber updated = new BinaryNumber(0);
                BinaryNumber mask2 = new BinaryNumber(mask);
                updated.bits.removeLast();
                
                quotient.bits.padding(maxLen);
                mask2.bits.padding(maxLen);
                
                for(int i=0;i<maxLen;i=i+1) {
                	Bit a = quotient.bits.get(i);
                	if(a.equals(mask2.bits.get(i)) &&  a.equals(Bit.ZERO))
                		updated.bits.addLast(Bit.ZERO);
                	else
                		updated.bits.addLast(Bit.ONE);
                }
                quotient.bits = updated.bits;
            }
        }
 
        //System.out.println("Remainder is "+x.toInt());
        return  quotient;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.12 ================================================
    public BinaryNumber(String s) {
    	if (s == null)
    		throw new IllegalArgumentException("input argument is null.");
    	if (!isInteger(s))
    		throw new IllegalArgumentException("input argument is not number.");
    	bits = new BitList();
    	
    	BinaryNumber ans = new BinaryNumber(0);
    	BinaryNumber base10 = new BinaryNumber('5').multiply(new BinaryNumber('2')); // initialize base10 as 10 in binaryNumber
    	BinaryNumber currBase = new BinaryNumber(1); // this parameter will have the binary value of 10 ^ j
    	boolean positive = true;
    	if( s.charAt(0) == '-') { // check if binary is positive or not
    		positive = false;
    		s = s.substring(1); // removing '-' char at the start of the string
    	}
    	for(int j = 0, i = s.length()-1; i >= 0; i=i-1,j=j+1) {
    		ans = ans.add((new BinaryNumber(s.charAt(i))).multiply(currBase));
    		currBase = currBase.multiply(base10);
    	}
    	if(!positive)
    		ans = ans.negate(); // updating the sign of the binary number
    	bits = ans.bits;
    }

    // this function returns boolean if the string represents an integer number
    public static boolean isInteger(String str) {
        boolean ans = true;
    	if (str == null) {
            ans =  false;
        }
    	else{
    		int length = str.length();
	        if (length == 0) {
	            ans = false;
	        }
	        else {
		        int i = 0;
		        if (str.charAt(0) == '-') {
		            if (length == 1) {
		                ans = false;
		            }
		            i = 1;
		        }
		        for (; i < length; i++) {
		            char c = str.charAt(i);
		            if (c < '0' || c > '9') {
		                ans = false;
		            }
		        }
	        }
    	}
        return ans;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.13 ================================================
   public String toIntString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        
        String ans ="";
        String toDecimal = "0";
        String bitString = toString();
        boolean negative = false;
        if (bitString.charAt(0) == '1') { // check if binary number is negative
        	negative = true;
        	bitString = negate().toString();
        }
        for(int power = 0, index = bitString.length()-1; index >= 0; index = index - 1,power = power + 1 ) // getting the number value in string
        {
        	if(bitString.charAt(index) == '1')
        	{
        		String toDec = power(2, power);
        		if(toDec != "0")
        			toDecimal = findSum(toDecimal,toDec);
        	}
        }
        if(bitString.length()-1 >= 0)
        	ans = toDecimal;
        if(negative) // adding '-' char at the start of the number if the number is negative
        	ans = "-"+ans;
        
        return ans;
    }
    
 // Function for finding sum of larger numbers   source: https://www.geeksforgeeks.org/sum-two-large-numbers/
    private static String findSum(String str1, String str2)  
    {  
        // Before proceeding further, make sure length  
        // of str2 is larger.  
        if (str1.length() > str2.length()){ 
            String t = str1; 
            str1 = str2; 
            str2 = t; 
        } 
      
        // Take an empty String for storing result  
        String str = "";  
      
        // Calculate length of both String  
        int n1 = str1.length(), n2 = str2.length();  
        int diff = n2 - n1;  
      
        // Initially take carry zero  
        int carry = 0;  
      
        // Traverse from end of both Strings  
        for (int i = n1 - 1; i>=0; i--)  
        {  
            // Do school mathematics, compute sum of  
            // current digits and carry  
            int sum = ((int)(str1.charAt(i)-'0') +  
                (int)(str2.charAt(i+diff)-'0') + carry);  
            str += (char)(sum % 10 + '0');  
            carry = sum / 10;  
        }  
      
        // Add remaining digits of str2[]  
        for (int i = n2 - n1 - 1; i >= 0; i--)  
        {  
            int sum = ((int)(str2.charAt(i) - '0') + carry);  
            str += (char)(sum % 10 + '0');  
            carry = sum / 10;  
        }  
      
        // Add remaining carry  
        if (carry > 0)  
            str += (char)(carry + '0');  
      
        // reverse resultant String 
        return new StringBuilder(str).reverse().toString(); 
    }  
    
    
 // This function finds
 // power of a number x    source: https://www.geeksforgeeks.org/writing-power-function-for-large-numbers/
    private static String power(int x, int n) {
         String ans = "";
         //returning value "1" for power = 0
         if(n == 0 ){ 
         ans = "1";
        }
        else {
	     int res[] = new int[100000];
	     int res_size = 0;
	     int temp = x;
	  
	     // Initialize result
	     while (temp != 0) {
	     res[res_size++] = temp % 10;
	     temp = temp / 10;
	     }
	  
	     // Multiply x n times
	     // (x^n = x*x*x....n times)
	     for (int i = 2; i <= n; i++)
	     res_size = multiply(x, res, res_size);
	  
	    // System.out.print(x + "^" + n + " = ");
	     for (int i = res_size - 1; i >= 0; i--)
	    	 ans += res[i];

     }
     return ans;
 }
    private static int multiply(int x, int res[], int res_size) {
	 
	    // Initialize carry
	    int carry = 0;
	 
	    // One by one multiply n with
	    // individual digits of res[]
	    for (int i = 0; i < res_size; i++) {
	    int prod = res[i] * x + carry;
	 
	    // Store last digit of
	    // 'prod' in res[]
	    res[i] = prod % 10;
	 
	    // Put rest in carry
	    carry = prod / 10;
	    }
	 
	    // Put carry in res and
	    // increase result size
	    while (carry > 0) {
	    res[res_size] = carry % 10;
	    carry = carry / 10;
	    res_size++;
	    }
	    return res_size;
	}
    


    // Returns this * 2
    public BinaryNumber multBy2() {
        BinaryNumber output = new BinaryNumber(this);
        output.bits.shiftLeft();
        output.bits.reduce();
        return output;
    }

    // Returens this / 2;
    public BinaryNumber divBy2() {
        BinaryNumber output = new BinaryNumber(this);
        if (!equals(ZERO)) {
            if (signum() == -1) {
                output.negate();
                output.bits.shiftRight();
                output.negate();
            } else output.bits.shiftRight();
        }
        return output;
    }

}
