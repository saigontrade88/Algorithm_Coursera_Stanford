package couse1;
import java.io.*;
import java.lang.Math.*;

public class Week1 {
	
	public static int pow(int a, int b) {
		int result = 1;
		for (int i = 1; i <= b; i++)
			result *= a;
		return result;
	}
	
	public static int Karatsuba_Multiplication(int x, int y, int n) {
		
		if(n == 2)
			return x*y;
		
		int a = x / pow(10, n/2);
		int b = x % pow(10, n/2) ;
		int c = y / pow(10, n/2);
		int d = y % pow(10, n/2);
		
		int ac = Karatsuba_Multiplication(a, c, n/2);
		int bd = Karatsuba_Multiplication(b, d, n/2);
		int term3 = Karatsuba_Multiplication(a+b, c+d, n/2);
		
		int term4 = term3 - ac - bd;
		
		System.out.printf("\na= %d", a); 
		System.out.printf("\nb= %d", b); 
		System.out.printf("\nc= %d", c); 
		System.out.printf("\nd= %d", d);
		
		System.out.printf("\nac= %d", ac);
		System.out.printf("\nbd= %d", bd);
		System.out.printf("\n\\(a+b)(c+d)= %d", term3);
		System.out.printf("\nterm4= %d", term4);
			
		return pow(10, n) * ac + pow(10,n/2)*(term4) + bd;
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int n = 0, temp = 0;
		BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter the size of your array: ");
		n = Integer.parseInt(consoleInput.readLine());
		int[] myArray = new int[n];
		
		for(int i = 0; i < n; i++) {
			System.out.printf("Please enter the %d element of your array: \n", (i + 1));
			temp = Integer.parseInt(consoleInput.readLine());
			myArray[i] =  temp;
			temp = 9999;
		}
		
		
		System.out.printf("Your array has %d elements\n", myArray.length);
		System.out.println("Your array has the following elements:"); 

		for (int j = 0; j < myArray.length; j++) {
			System.out.printf("\nj = %d, myArray[%d] = %d", j + 1, j + 1, myArray[j]); 
		}
		
		System.out.printf("\nThe product of your numbers = %d", Karatsuba_Multiplication(myArray[0], myArray[1], 4));
		
		
	}

}
