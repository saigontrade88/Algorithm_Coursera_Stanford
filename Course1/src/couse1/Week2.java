package couse1;
import java.io.*;

public class Week2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Open the file that is the first 
		  // command line parameter
		  FileInputStream fstream = new FileInputStream(args[0]);
		  // Get the object of DataInputStream
		  DataInputStream in = new DataInputStream(fstream);
		  
		BufferedReader myInFile = new BufferedReader(new InputStreamReader(in));
		
		String currentLine;
		while((currentLine = myInFile.readLine()) != null) {
			System.out.println(currentLine);
		}
		myInFile.close();

	}

}
