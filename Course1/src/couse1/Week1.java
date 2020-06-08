package couse1;
import java.io.*;

public class Week1 {

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
		
		
	}

}
