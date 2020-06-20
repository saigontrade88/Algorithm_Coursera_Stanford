package couse1;
import java.io.*;
import java.lang.Math.*;
import java.math.*;

public class Week1 {
	
	public static int pow(int a, int b) {
		int result = 1;
		for (int i = 1; i <= b; i++)
			result *= a;
		return result;
	}
	
	public static BigInteger Karatsuba_Multiplication(BigInteger x, BigInteger y, int n) {
		
		if(n <= 2)
			return x.multiply(y);
		
		BigInteger mySplit = BigInteger.valueOf(10).pow(n/2);
		
		BigInteger a = x.divide(mySplit);
		
		BigInteger b = x.subtract(mySplit.multiply(a));
		
		BigInteger c = y.divide(mySplit);
		
		BigInteger d = y.subtract(mySplit.multiply(c));
		
		BigInteger ac = Karatsuba_Multiplication(a, c, n/2);
		BigInteger bd = Karatsuba_Multiplication(b, d, n/2);
		BigInteger term3 = Karatsuba_Multiplication(a.add(b), c.add(d), n/2);
		
		BigInteger term4 = term3.subtract(ac).subtract(bd);
		
		
		  System.out.printf("\na= %d", a); System.out.printf("\nb= %d", b);
		  System.out.printf("\nc= %d", c); System.out.printf("\nd= %d", d);
		  
		  System.out.printf("\nac= %d", ac); System.out.printf("\nbd= %d", bd);
		  System.out.printf("\n\\(a+b)(c+d)= %d", term3);
		  System.out.printf("\nterm4= %d", term4);
		 
			
		BigInteger rTerm1 = ac.multiply(BigInteger.valueOf(10).pow(n));
		BigInteger rTerm2 = term4.multiply(mySplit);
		
		return rTerm1.add(rTerm2).add(bd);
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int n = 0, temp = 0;
		//BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedReader myInFile = new BufferedReader(new FileReader(args[0]));
		
		String currentLine = myInFile.readLine();
		
	    n = currentLine.length();
	    
	    //System.out.printf("n = %d\n", n);

	    //System.out.println(currentLine);
		
	    BigInteger a = new BigInteger(currentLine);
		
		currentLine = myInFile.readLine();
		
		//System.out.println(currentLine);
		
		BigInteger b = new BigInteger(currentLine);
		
		myInFile.close();
		
		System.out.printf("a = %d\n", a);
	
		System.out.printf("b = %d\n", b);
		
		System.out.printf("n = %d\n", n);
		
		BigInteger result = Karatsuba_Multiplication(a, b, n);
		
		System.out.printf("\nresult = %d", result);
			
	}

}
