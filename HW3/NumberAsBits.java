
public class NumberAsBits {

    
    private Bit[] bits;

    public NumberAsBits(Bit[] bits) {
        //Task 3.4
    	if(bits == null)
    		throw new IllegalArgumentException("bits array is null.");
    	this.bits = new Bit[bits.length];
    	for (int i=0;i<bits.length;i=i+1)
    	{
    		if(bits[i] == null)
    			throw new IllegalArgumentException("bits["+i+"] is null.");
    		else
    			this.bits[i] = bits[i];
    	}
    }

    public String toString() { 
        String ans ="";
        //Task 3.5
        String toDecimal = "0";
        //boolean isValid = false;
        for(int power = 0, index = this.bits.length-1; index >= 0; index = index - 1,power = power + 1 )
        {
        	if(bits[index].toInt() == 1)
        	{
        		String toDec = power(2, power);
        		if(toDec != "0")
        			toDecimal = findSum(toDecimal,toDec);
        	}
        }
        if(this.bits.length-1 >= 0)
        	ans = toDecimal;
        return ans;
    }
    
 // Function for finding sum of larger numbers   source: https://www.geeksforgeeks.org/sum-two-large-numbers/
    public static String findSum(String str1, String str2)  
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
 public static String power(int x, int n) {
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
 public static int multiply(int x, int res[], int res_size) {
	 
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
    
    
    
    
    
    public String base2() {
        String ans ="";
        //Task 3.6
        for(int index = 0; index < this.bits.length; index = index + 1 )
        {
        	ans = ans + bits[index].toString();
        }
        if(this.bits.length > 1 && bits[0].toInt() == 0)
        	ans = ans.substring(1);
        return ans;
    }
    
}


