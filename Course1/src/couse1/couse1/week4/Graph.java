package couse1.week4;

import java.util.*;



public class Graph {
	
	ArrayList<GraphVertex> verts;
	ArrayList<GraphEdge>   edges;
	private HashMap<String, ArrayList<GraphVertex>> adj;
	
	private ArrayList<GraphEdge> selectedEdges = null;
	private boolean existed = false;
	GraphEdge selected = null;
	
	public Graph(ArrayList<GraphVertex> verts, ArrayList<GraphEdge> edges,
			HashMap<String, ArrayList<GraphVertex>> adj) {
		this.verts = verts;
		this.edges = edges;
		this.adj = adj;
		selectedEdges = null;
	}
	
	public Graph(ArrayList<GraphVertex> verts, ArrayList<GraphEdge> edges) {
		this.verts = verts;
		this.edges = edges;
		this.adj = null;
		selectedEdges = null;
	}

	// A utility function to add an edge in an 
    // undirected graph
    void addEdge(GraphEdge u) 
    { 
    	
    	//update the edge list
    	edges.add(new GraphEdge(u.v0, u.v1));
    			
    	//Get its adjacency list
		ArrayList<GraphVertex> neighborList = adj.get(u.v0.id);
		//Add the neighboring vertex
		neighborList.add(u.v1);
		//update the adjacency list
		adj.put(u.v0.id, neighborList);
        
        
		neighborList = adj.get(u.v1.id);
        
		//Add the neighboring vertex
		neighborList.add(u.v0);
		//update the adjacency list
		adj.put(u.v1.id, neighborList); 
		
		
    }
    
    // A utility function to add a vertex in an 
    // undirected graph
    void addVertex(GraphVertex v, ArrayList<GraphVertex> neighborList) {
    	//update the vertex list
    	verts.add(new GraphVertex(v.id, 0, 0));
    	//update the adjacency list
    	adj.put(v.id, neighborList);
    }
    
    
	// A utility function to print the adjacency list 
    // representation of graph 
    void printGraph() { 
    	for (String Id : adj.keySet()) {
    		ArrayList<GraphVertex> neighborList = adj.get(Id);
            System.out.println("\nAdjacency list of vertex " + Id); 
            System.out.print("head"); 
            for(int i = 0; i < neighborList.size(); i++){ 
                System.out.print(" -> "+ neighborList.get(i).id); 
            } 
            System.out.println(); 
        } 
    }
    
    static void printGraph(HashMap<String, ArrayList<GraphVertex>> currAdj) { 
    	for (String Id : currAdj.keySet()) {
    		ArrayList<GraphVertex> neighborList = currAdj.get(Id);
            System.out.println("\nAdjacency list of vertex " + Id); 
            System.out.print("head"); 
            for(int i = 0; i < neighborList.size(); i++){ 
                System.out.print(" -> "+ neighborList.get(i).id); 
            } 
            System.out.println(); 
        } 
    }
    
//    void repetitiveKargerMinCut(int numRep) {
//    	for(int i = 0; i < numRep; i++) {
//    		this.numMinCut = kargerMinCut();
//    	}
//    }
    
    //Modify the vertex and edge list
    long kargerMinCut() {
    	
    	long numMinCut = 1000000;
    	
    	//make a copy of the edge list, vertex list
    	ArrayList<GraphEdge> currEdges = new ArrayList<GraphEdge>();
    	
    	for (int a = 0; a < edges.size(); a++)
        {
            currEdges.add(edges.get(a));
        }
    	
    	ArrayList<GraphVertex> currVerts = new ArrayList<GraphVertex>();
    	
    	for (int a = 0; a < verts.size(); a++)
        {
            currVerts.add(verts.get(a));
        }
    	
    	HashMap<String, ArrayList<GraphVertex>> currAdj = new HashMap<String, ArrayList<GraphVertex>>();
    	
    	for (Map.Entry<String, ArrayList<GraphVertex>> entry : adj.entrySet()) {
            currAdj.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
    	
    	
    	
    	System.out.println("Before the Karger's algo");
    	
    	this.printGraph(currAdj);
    	
    	//write your random edge selector, selected edge
    	while(currVerts.size() > 2) {
    		if(currEdges.size() > 0) {
    			
    			this.selectEdge(currEdges, currAdj);
    			
    			//merge (or “contract” ) u and v into a single vertex
    			this.contractAnEdge(selected, currVerts, currEdges, currAdj);
    			
    			//rebuild the edge array list from the current adjacency list
    	    	//find edge[v1, v0]
    	    	ArrayList<GraphEdge> temp = new ArrayList<GraphEdge>();
    			
    	    	//For all vertices in the adjacency list, changing all occurrences of v1 to v0
    	    	for(GraphVertex v:currVerts) {
    	    		ArrayList<GraphVertex> neighborList2 = currAdj.get(v.id);
    	    		for(GraphVertex temp2: neighborList2) {
    	    			temp.add(new GraphEdge(v, temp2));
    	    		}
    	    	}
    			
    			currEdges = temp;
    		}
    		else {
    			break;
    			//exit the function?
    		}
    	}
    	
    	GraphVertex finalVert = null;
    	
    	//when finish the contraction, only 2 vertices are left only
       	for(GraphVertex v: currVerts) {
    		if(v != null) {
    			System.out.println(v.getStringID());
    			finalVert = v;
    			break;
    		}
    	}
    	
    	if(currAdj.containsKey(finalVert.getStringID())){
    		//Get its adjacency list
    		ArrayList<GraphVertex> neighborList = adj.get(finalVert.id);
    		numMinCut = neighborList.size();
    	}
    	
    	//reset for the next loop
    	selected = null;
    	selectedEdges = null;
    	currAdj = null;
    	currEdges = null;
    	currVerts = null;
    	
    	//the number of edges linking these vertices is the minimum number of crossing edges in the min cut
    	return numMinCut;
    }
    
    //Randomly select an edge and update the selectedEdges list
    //MUST DO: parameter EdgeList
    void selectEdge(ArrayList<GraphEdge>currEdges, HashMap<String, ArrayList<GraphVertex>> adj) {
    	
    	Random rand = new Random();
    	GraphEdge temp = null;
    	//Randomly select an edge from the current edge list
    	
    		int r =  rand.nextInt(currEdges.size());
    		//avoid modifying the edge list
        	temp = currEdges.get(r);
        	//the adjacency list is updated after each loop so only selecting the remaining edges
        		
    	
    	   	
    	//MUST DO: check if the source vertex is available in the adjacency list  	   	
    	if(selectedEdges == null) {
    		selectedEdges = new ArrayList<GraphEdge>();
    		//existed = false;
    	}
    	    	  	
    	
    		selectedEdges.add(temp);
    		selected = temp;
    		
    		System.out.println("Selected edge = " + selected.toString());
    		System.out.println("My selected edge's size is " + selectedEdges.size());
    	
    	//no else here 
    	
    }
    
    void contractAnEdge(GraphEdge u, ArrayList<GraphVertex> currVerts, ArrayList<GraphEdge>currEdges, HashMap<String, ArrayList<GraphVertex>> currAdj) {
    	//A for searches the list Vertices[u.v1] for the occurrence of u.v0 and deletes it
    	if(currAdj.containsKey(u.v1.id)) {
    		ArrayList<GraphVertex> neighborListV1 = currAdj.get(u.v1.id);
    		//delete the neighboring vertex
    		neighborListV1.remove(u.v0);
    		//verts.remove(u.v0);

    		//update the Hashmap
    		currAdj.put(u.v1.id, neighborListV1);

    		//Add the list Vertices[v1] to the list Vertices[v0]
    		ArrayList<GraphVertex> neighborListV0 = currAdj.get(u.v0.id);
    		neighborListV0.addAll(neighborListV1);
    		neighborListV0.remove(u.v1);

    		//update the Hashmap
    		currAdj.put(u.v0.id, neighborListV0);

    		//delete the Vertices[v1]
    		currAdj.remove(u.v1.getStringID());



    		currVerts.remove(u.v1);

    		//then delete the edge [v0, v1].
    		//currEdges.remove(u);
    		
    		
    	} 		 		
    	
    	//For all vertices in the adjacency list, changing all occurrences of v1 to v0
    	for(GraphVertex v:currVerts) {
    		ArrayList<GraphVertex> neighborList = currAdj.get(v.id);
    		for(GraphVertex temp: neighborList) {
    			if(temp.getStringID().equals(u.v1.getStringID())) {
    				//replace vertex v1 with v0
    				neighborList.set(neighborList.indexOf(temp), u.v0);
    				//update the Hashmap accordingly
    				currAdj.put(v.id, neighborList);
    			}	
    		}


    	}
    	
    	
    	
    	//remove self loops
    	ArrayList<GraphVertex> neighborList = currAdj.get(u.v0.id);
    	//ArrayList<GraphVertex> selfloop = new GraphVertex();
    	ArrayList<Integer> selfloop = new ArrayList<Integer>();
    	int i = neighborList.size() - 1;
    	
    	for(; i >= 0;i--) {
    		System.out.println("head " + u.v0.getStringID() + "-> " + neighborList.get(i).getStringID());
    		if(neighborList.get(i).getStringID().equals(u.v0.getStringID())) {
    			//remove the self loop
    			//neighborList.remove(temp);
    			selfloop.add(i);
    			System.out.println("Self loop is at = " + i);
    			//update the Hashmap accordingly
    			//currAdj.put(u.v0.id, neighborList);
    		}
    	}
    	
    	for(int j = 0; j < selfloop.size(); j++)
    		neighborList.remove((int) selfloop.get(j));
    	
    	currAdj.put(u.v0.id, neighborList);
    	
    	printGraph(currAdj);
    	
    	
    }
    	
    }
    
    


