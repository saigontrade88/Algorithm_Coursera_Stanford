package couse1.week4;
// This fil is for week 4 program

import java.io.*;
import java.util.*;

import couse1.Week3;

public class Week4 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Open the file that is the first command line parameter
		try {
			args[0] = "Course1/Input/Wk4_input_random_1_6.txt";

			ArrayList<GraphVertex> verts = new ArrayList<GraphVertex>();
		    ArrayList<GraphEdge>   edges = new ArrayList<GraphEdge>();
		    
		  //Adjacency vertex list
		    // Map vertex to its ArrayList<String>
		    // Look up the ArrayList given the vertex name
		    // Return the ArrayList for the given vertex
		    // Summarize the info from the GraphEdge ArrayList
		    
		    HashMap<Integer, ArrayList<GraphVertex>> adj = null;
		    
		    // TODO: PUT CODE IN TO LOAD THE VERTICES
		    
		    
		    System.out.println("Number of vertices" + verts.size() + "\n");
		    
		    // TODO: PUT CODE IN TO LOAD THE EDGES
		    GraphVertex mySrcVert = null;
		    GraphVertex myDestVert = null;
		    
		    System.out.println("Number of edges " + edges.size() + "\n");
		    

			FileInputStream fstream = new FileInputStream(args[0]);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);

			BufferedReader myInFile = new BufferedReader(new InputStreamReader(in));

			String currentLine;

			while((currentLine = myInFile.readLine()) != null) {
				//System.out.println(currentLine);
				//myArray.add(Integer.parseInt(currentLine));
			}

			myInFile.close();

			//Initialize the adjacency vertex list
			
			//Populate the adjacency list

			//System.out.println(6);

		} catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.toString());
		}


	}
}