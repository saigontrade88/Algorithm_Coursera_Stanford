package couse1.week4;

import java.util.*;



public class Graph {
	
	ArrayList<GraphVertex> verts;
	ArrayList<GraphEdge>   edges;
	private HashMap<String, ArrayList<GraphVertex>> adj;
	
	private ArrayList<GraphEdge> selectedEdges = null;
	private GraphEdge selected = null;
	
	public Graph(ArrayList<GraphVertex> verts, ArrayList<GraphEdge> edges,
			HashMap<String, ArrayList<GraphVertex>> adj) {
		this.verts = verts;
		this.edges = edges;
		this.adj = adj;
		selectedEdges = new ArrayList<GraphEdge>();
	}
	
	public Graph(ArrayList<GraphVertex> verts, ArrayList<GraphEdge> edges) {
		this.verts = verts;
		this.edges = edges;
		this.adj = null;
		selectedEdges = new ArrayList<GraphEdge>();
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
    
//    void repetitiveKargerMinCut(int numRep) {
//    	for(int i = 0; i < numRep; i++) {
//    		this.numMinCut = kargerMinCut();
//    	}
//    }
    
    long kargerMinCut() {
    	
    	long numMinCut = 1000000;
    	
    	//make a copy of the edge list, vertex list
    	ArrayList<GraphEdge> currEdges = new ArrayList<GraphEdge>(edges);
    	ArrayList<GraphVertex> currVerts = new ArrayList<GraphVertex>(verts);
    	
    	//write your random edge selector, selected edge
    	
    	
    	
    	while(currVerts.size() > 2) {
    		
    	}
    	
    	
    	
    	return numMinCut;
    }
    
    //Randomly select an edge
    void selectEdge() {
    	Random rand = new Random();
    	int r =  rand.nextInt(edges.size());
    	GraphEdge temp = edges.get(r);
    	if(selectedEdges != null) {
    		for(GraphEdge edge:selectedEdges) {
    			if(edge.equals(temp)) {
    				selected = temp;
    				break;
    			}
    		}
    	}
    	
    }
    
    

}
