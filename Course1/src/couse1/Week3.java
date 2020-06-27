package couse1;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Week3 {
	
	long totalCount1 = 0;
	long totalCount2 = 0;
	
	public int partitionFirstElement(ArrayList<Integer> myArray, int startIndex, int endIndex) {
		
		totalCount1 += endIndex - startIndex;
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
	public int partitionLastElement(ArrayList<Integer> myArray, int startIndex, int endIndex) {

		totalCount2 += endIndex - startIndex;
		int pivotVal = myArray.get(endIndex);//select the pivot - the last/final element
		//System.out.printf("\tPartion, start = %d, end = %d, pivot = %d, total count = %d\t", startIndex, endIndex, pivotVal, totalCount2);
		int pivotCorrectIndex = startIndex - 1;
		for (int j = startIndex; j < endIndex; j++) {
			if(myArray.get(j) <= pivotVal) {
				//System.out.printf("Inside comparison, j = %d", j);		

				pivotCorrectIndex ++;
				
				//exchange A[pivotCorrectIndex] and A[j]
				Collections.swap(myArray, pivotCorrectIndex, j);


			}
		}

		//exchange A[pivotCorrectIndex - 1] and A[startIndex]
		Collections.swap(myArray, endIndex, pivotCorrectIndex + 1);

		//System.out.printf("\tPartion, start = %d, end = %d, pivot = %d\t, after the partition\n", startIndex, endIndex, pivotVal);

		//		for(int k=0; k < myArray.size(); k++){
		//			System.out.println(myArray.get(k));
		//		}

		return pivotCorrectIndex + 1;
	}

	public void quicksortFirstElement(ArrayList<Integer> myArray, int startIndex, int endIndex) {
			
			//System.out.printf("\tQuicksort, start = %d, end = %d", startIndex, endIndex);
			
			if (startIndex < endIndex) {
				int pivotIndex = partitionFirstElement(myArray, startIndex, endIndex);
				quicksortFirstElement(myArray, startIndex, pivotIndex - 1);
				quicksortFirstElement(myArray, pivotIndex + 1, endIndex);
				
			}
	}
	
	public void quicksortLastElement(ArrayList<Integer> myArray, int startIndex, int endIndex) {
		
		//System.out.printf("\tQuicksort, start = %d, end = %d", startIndex, endIndex);
		
		if (startIndex < endIndex) {
			int pivotIndex = partitionLastElement(myArray, startIndex, endIndex);
			quicksortLastElement(myArray, startIndex, pivotIndex - 1);
			quicksortLastElement(myArray, pivotIndex + 1, endIndex);
			
		}
}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Open the file that is the first command line parameter
	try {
//		args[0] = "Input/input_beaunus_1_4.txt";
		
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
		
		ArrayList<Integer> cloned1 = new ArrayList<Integer>(myArray);
		ArrayList<Integer> cloned2 = new ArrayList<Integer>(myArray);
		
//		for(int k=0; k < cloned1.size(); k++){
//			System.out.println(cloned1.get(k));
//		}
//		
		
		//System.out.printf("inverse count = %d", SortAndCount(myArray, 0, myArray.size() - 1));
		mySol.quicksortFirstElement(cloned1, 0, cloned1.size() - 1);
		mySol.quicksortLastElement(cloned2, 0, cloned2.size() - 1);;
		
//		System.out.println("\n***************************");
//		
//		for(int k=0; k < myArray.size(); k++){
//			System.out.println(myArray.get(k));
//		}
//
//		System.out.println("\n*********************");
//		for(int k=0; k < cloned1.size(); k++){
//			System.out.println(cloned1.get(k));
//		}

		System.out.println(mySol.totalCount1);
		System.out.println(mySol.totalCount2);
		System.out.println(6);
		
		//System.out.println(6);
		
	} catch (Exception e){//Catch exception if any
		System.err.println("Error: " + e.toString());
	}
		

	}

}
