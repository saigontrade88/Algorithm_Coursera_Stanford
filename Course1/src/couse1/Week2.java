package couse1;
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;

public class Week2 {
	
	public static BigInteger CountSplitInv(ArrayList<Integer> myArray, int startIndex, int midIndex, int endIndex) {
		//System.out.printf("\tCountSplitInv, start = %d, mid = %d, end = %d\t", startIndex, midIndex, endIndex);
		BigInteger invCount = BigInteger.ZERO;
		int n1 = midIndex - startIndex + 1;
		int n2 = endIndex -  midIndex;
		ArrayList<Integer> left, right;
		left = new ArrayList<Integer>();
		right = new ArrayList<Integer>();
		//create the left and right half arrays
		for(int i = 1; i <= n1; i++)
			left.add(myArray.get(startIndex + i - 1));
		for(int j = 1; j <= n2; j++)
			right.add(myArray.get(midIndex + j));
			
		left.add(999999);
		right.add(999999);
		
		int i = 0, j = 0;
		//sort, combine and count the inversion
		for(int k = startIndex; k <= endIndex; k++) {
			if(left.get(i) <= right.get(j)) {
				myArray.set(k,left.get(i));
				i++;
				//increase the count
				//invCount += left.size() - i;
			}
			else {
				myArray.set(k,right.get(j));
				j++;
				//invCount += left.size() - i - 1;
				BigInteger remains = BigInteger.valueOf(left.size() - i - 1);
				invCount = invCount.add(remains);
				//System.out.printf("k = %d, invCount = %d\n", k, invCount);
			}
		}
		return invCount;
	}
	
	public static BigInteger SortAndCount(ArrayList<Integer> myArray, int startIndex, int endIndex) {
		//System.out.printf("\tSortAndCount, start = %d, end = %d", startIndex, endIndex);
		BigInteger totalCount = BigInteger.ZERO, firstCount = BigInteger.ZERO, secondCount = BigInteger.ZERO, thirdCount = BigInteger.ZERO;
		
		if (startIndex < endIndex) {
			int mid = (startIndex + endIndex)/2;
			firstCount = SortAndCount(myArray, startIndex, mid);
			secondCount = SortAndCount(myArray, mid + 1, endIndex);
			thirdCount = CountSplitInv(myArray, startIndex, mid, endIndex);
		}
		totalCount = firstCount.add(secondCount).add(thirdCount);
		//System.out.printf("\tSortAndCount, firstCount = %d, secondC = %d, thirdC = %d\n", firstCount, secondCount, thirdCount);
		return totalCount;
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
		
//		for(int k=0; k < myArray.size(); k++){
//			System.out.println(myArray.get(k));
//		}
		
		//System.out.printf("inverse count = %d", SortAndCount(myArray, 0, myArray.size() - 1));
		System.out.print(SortAndCount(myArray, 0, myArray.size() - 1));
	}catch (Exception e){//Catch exception if any
		System.err.println("Error: " + e.getMessage());
	}
		

	}


}
