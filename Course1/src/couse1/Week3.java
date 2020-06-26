package couse1;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Week3 {
	
	long totalCount = 0;
	
	public int Partition(ArrayList<Integer> myArray, int startIndex, int endIndex) {
		
		totalCount += endIndex - startIndex;
		int pivotVal = myArray.get(startIndex);//select the pivot - the first element
		//System.out.printf("\tPartion, start = %d, end = %d, pivot = %d, total count = %d\t", startIndex, endIndex, pivotVal, totalCount);
		int pivotCorrectIndex = startIndex + 1;
		for (int j = startIndex + 1; j <= endIndex; j++) {
			if(myArray.get(j) < pivotVal) {
				//System.out.println("Inside comparison");
				
				//exchange A[pivotCorrectIndex] and A[j]
				Collections.swap(myArray, pivotCorrectIndex, j);
				
				pivotCorrectIndex ++;
				
				
			}
		}
		
		//exchange A[pivotCorrectIndex - 1] and A[startIndex]
		Collections.swap(myArray, startIndex, pivotCorrectIndex - 1);
		
		//System.out.printf("\tPartion, start = %d, end = %d, pivot = %d\t, after the partition\n", startIndex, endIndex, pivotVal);
		
//		for(int k=0; k < myArray.size(); k++){
//			System.out.println(myArray.get(k));
//		}
		
		return pivotCorrectIndex - 1;
	}
	public void Quicksort(ArrayList<Integer> myArray, int startIndex, int endIndex) {
			
			//System.out.printf("\tQuicksort, start = %d, end = %d", startIndex, endIndex);
			
			if (startIndex < endIndex) {
				int pivotIndex = Partition(myArray, startIndex, endIndex);
				Quicksort(myArray, startIndex, pivotIndex - 1);
				Quicksort(myArray, pivotIndex + 1, endIndex);
				
			}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Open the file that is the first command line parameter
	try {
		//args[0] = "Input/input_beaunus_1_4.txt";
		
		ArrayList<Integer> myArray = new ArrayList<Integer>();
		
		FileInputStream fstream = new FileInputStream(args[0]);
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);

		BufferedReader myInFile = new BufferedReader(new InputStreamReader(in));

		String currentLine;

		while((currentLine = myInFile.readLine()) != null) {
			//System.out.println(currentLine);
			myArray.add(Integer.parseInt(currentLine));
		}
		
		myInFile.close();
		
		//System.out.println("*********************");
		
//		for(int k=0; k < myArray.size(); k++){
//			System.out.println(myArray.get(k));
//		}
		
		Week3 mySol = new Week3();
		
		//System.out.printf("inverse count = %d", SortAndCount(myArray, 0, myArray.size() - 1));
		mySol.Quicksort(myArray, 0, myArray.size() - 1);
		
//		System.out.println("\n*********************");
		
//		for(int k=0; k < myArray.size(); k++){
//			System.out.println(myArray.get(k));
//		}
		System.out.println(mySol.totalCount);
		//System.out.println(8);
		//System.out.println(6);
		
	} catch (Exception e){//Catch exception if any
		System.err.println("Error: " + e.toString());
	}
		

	}

}
