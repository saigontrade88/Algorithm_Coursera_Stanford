package couse1.week4;
// This fil is for week 4 program

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Week4 {
	
	/** Returns the tokens that match the regex pattern from the document 
	 * text string.
	 * @param pattern A regular expression string specifying the 
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex 
	 *   pattern
	 */
	protected List<String> getTokens(String text, String pattern){
		
		ArrayList<String> tokens = new ArrayList<String>();
		
		Pattern tokSplitter = Pattern.compile(pattern);
		
		Matcher m = tokSplitter.matcher(text);
		
		while(m.find()){
			tokens.add(m.group());
		}
		
		return tokens;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Open the file that is the first command line parameter
		try {
			//args[0] = "Course1/Input/Wk4_input_random_1_6.txt";
			
			args[0] = "Course1/Input/Wk4_kargerMinCut.txt";
			
			//Karger's parameters = 10000000;
			int numberMinCut = 10000000;

			ArrayList<GraphVertex> verts = new ArrayList<GraphVertex>();
		    ArrayList<GraphEdge>   edges = new ArrayList<GraphEdge>();
		    
		    //Adjacency vertex list
		    // Map vertex to its ArrayList<String>
		    // Look up the ArrayList given the vertex name
		    // Return the ArrayList for the given vertex
		    // Summarize the info from the GraphEdge ArrayList
		    
		    HashMap<String, ArrayList<GraphVertex>> adj = null;
		    
		    // TODO: PUT CODE IN TO LOAD THE VERTICES
		    
		    FileInputStream fstream = new FileInputStream(args[0]);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);

			BufferedReader myInFile = new BufferedReader(new InputStreamReader(in));

			String currentLine;
						
			Week4 sol = new Week4();
			
			List<String> myIntegerList = null;
			
			while((currentLine = myInFile.readLine()) != null) {
				//System.out.println(currentLine);
				
				myIntegerList = sol.getTokens(currentLine, "[0-9]+");
				
				String id;
				System.out.println("\nPrint myIntegerList"); 
				
				for(String s:myIntegerList) {
					System.out.println(s);
				}
				
				GraphVertex mySrcVert = new GraphVertex(myIntegerList.get(0), 0, 0) ;
				
				verts.add(mySrcVert);
				
				System.out.println("My source vertex is " + mySrcVert.getStringID()); 
				
				GraphVertex myDestVert = null;
				
				System.out.println("myIntegerList size =  " + myIntegerList.size() + "\n");
				
				for (int i = 1; i < myIntegerList.size(); i++) {
					
					id = myIntegerList.get(i);
					//Build the GraphVertex array list
					
					myDestVert =  new GraphVertex(id, 0, 0);
					
					//System.out.println(s);

					// TODO: PUT CODE IN TO LOAD THE EDGES

					System.out.println(myDestVert.getStringID());
					
					//Add to the edge list
					edges.add(new GraphEdge(mySrcVert, myDestVert));
									
				}
	
			}
		   	    
		    System.out.println("Number of vertices " + verts.size() + "\n");
		    
		    for(GraphVertex v: verts){
		        System.out.println(v.id); 
		      }
		     
		    System.out.println("Number of edges " + edges.size() + "\n");
		    

			myInFile.close();

			//Initialize the adjacency vertex list
			

		    adj = new HashMap<String, ArrayList<GraphVertex>>();
		    
		    for ( GraphVertex v : verts ) {
		      ArrayList<GraphVertex> neighborList = new ArrayList<GraphVertex>();
		      adj.put(v.id, neighborList);
		    }
		    
		    //Populate the adjacency list
		    //For each edge
		    for ( GraphEdge u : edges) {
		    	//If the source vertex is existing
		    	if (adj.containsKey(u.v0.id)) {
		    		//Get its adjacency list
		    		ArrayList<GraphVertex> neighborList = adj.get(u.v0.id);
		    		//Add the neighboring vertex
		    		neighborList.add(u.v1);
		    		//update the Hashmap
		    		adj.put(u.v0.id, neighborList);
		    	} else {
		    		System.out.println("New vertex. Double check the input data");
		    	}
		    }

		    System.out.println("The adjacency list");
		    
		    Graph myGraph = new Graph(verts, edges, adj);
		    
		    myGraph.printGraph();

			//System.out.println(6);

		} catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.toString());
		}


	}
}