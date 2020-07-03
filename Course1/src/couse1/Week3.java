package couse1;

import java.io.*;

import java.lang.Math;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.*; 

public class Week3 {
	
	long totalCount1 = 0;
	long totalCount2 = 0;
	
	public int medianOfThree(ArrayList<Integer> myArray, int startIndex, int middleIndex, int endIndex) {
		
		int medOfThree = 0;
		
		//Identify which of these three elements: first, middle and last is the median 
		// (i.e., the one whose value is in between the other two), and use this as your pivot
		
		//temp could be of size 2????
		int[] temp = new int[] {myArray.get(startIndex), myArray.get(middleIndex), myArray.get(endIndex)};
		
		Arrays.sort(temp);
		
		return myArray.indexOf(temp[1]);
	}
	
	public int partitionMedian(ArrayList<Integer> myArray, int startIndex, int endIndex) {
		
		String inputArray = "Input array: [";
		
		for(int k= startIndex; k <= endIndex; k++){
			
			inputArray += myArray.get(k);
			if(k == endIndex) {
				inputArray += "]";
			}
			else {
				inputArray += ", ";
			}
			
		}
		
		System.out.println(inputArray);
		
		int middle = (int) Math.floor((startIndex + endIndex)/2.0);
		
		int medOfThree = medianOfThree(myArray, startIndex, middle, endIndex);
		
		//Identify which of these three elements: first, middle and last is the median 
		// (i.e., the one whose value is in between the other two), and use this as your pivot
	
		
		//medOfThree = how ab
		
		System.out.println("first: " + myArray.get(startIndex) + " last: " + myArray.get(endIndex) + " middle: " + myArray.get(middle) + " median: " + myArray.get(medOfThree) + " count = " + (endIndex - startIndex));
		
		//exchange A[startIndex] and A[median of three]
		Collections.swap(myArray, startIndex, medOfThree);
		
		return partitionFirstElement(myArray, startIndex, endIndex);
	}
	
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
	
	public void quicksortMedianElement(ArrayList<Integer> myArray, int startIndex, int endIndex) {

		//System.out.printf("\tQuicksort, start = %d, end = %d", startIndex, endIndex);

		if (startIndex < endIndex) {
			
	
			
			int pivotIndex = partitionMedian(myArray, startIndex, endIndex);
			quicksortMedianElement(myArray, startIndex, pivotIndex - 1);
			quicksortMedianElement(myArray, pivotIndex + 1, endIndex);

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
		Week3 mySol3 = new Week3();
		
		
		ArrayList<Integer> cloned1 = new ArrayList<Integer>(myArray);
		ArrayList<Integer> cloned2 = new ArrayList<Integer>(myArray);
		ArrayList<Integer> cloned3 = new ArrayList<Integer>(myArray);
		
//		for(int k=0; k < cloned1.size(); k++){
//			System.out.println(cloned1.get(k));
//		}
//		
		
		//System.out.printf("inverse count = %d", SortAndCount(myArray, 0, myArray.size() - 1));
		//mySol.quicksortFirstElement(cloned1, 0, cloned1.size() - 1);
		//mySol.quicksortLastElement(cloned2, 0, cloned2.size() - 1);
		mySol3.quicksortMedianElement(cloned3, 0, cloned3.size() - 1);
		
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

		//System.out.print(mySol.totalCount1  + "\n");
		//System.out.print(mySol.totalCount2  + "\n");
		System.out.print(mySol3.totalCount1 + "\n");
		
		//System.out.println(6);
		
	} catch (Exception e){//Catch exception if any
		System.err.println("Error: " + e.toString());
	}
		

	}

}
