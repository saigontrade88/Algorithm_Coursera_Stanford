package couse1.week4;

import java.util.*;




public class Graph {

	private final int V;
	private int E;
	
	//private ArrayList<GraphVertex> setA;
	//private ArrayList<GraphVertex> setB;
	private int[] id;
	private int[] sz; // count number of objects in the tree rooted at vertex i_th
	private int countGroups;
	
	ArrayList<GraphVertex> verts;
	ArrayList<GraphEdge>   edges;
	private HashMap<String, ArrayList<GraphVertex>> adj;
	
	//private ArrayList<GraphEdge> selectedEdges = null;
	//private boolean existed = false;
	GraphEdge selected = null;

	public Graph(ArrayList<GraphVertex> verts, ArrayList<GraphEdge> edges,
			HashMap<String, ArrayList<GraphVertex>> adj) {
		this.verts = verts;
		this.edges = edges;
		this.adj = adj;
		//selectedEdges = null;
		this.V = verts.size();
		this.E = edges.size();
		
		//setA = new ArrayList<GraphVertex>();
		//setB = new ArrayList<GraphVertex>();
		
		id = new int[this.getV() + 1];
		sz = new int[this.getV() + 1];
		
		for (int i = 1; i <= this.getV(); i++) {
			id[i] = Integer.parseInt(verts.get(i - 1).getStringID());
			sz[i] = 1; //initialize the size array. At beginning, each tree has only one member, the root itself.
		}
//		for (int i = 1; i <= this.getV(); i++)
//			System.out.println("i= " + i + "," + "id[i]= " + id[i]);
		
		//Assume each vertex is its own group 
		countGroups = verts.size();
		
	}

	public Graph(ArrayList<GraphVertex> verts, ArrayList<GraphEdge> edges) {
		this.verts = verts;
		this.edges = edges;
		this.adj = null;
		//selectedEdges = null;
		this.V = verts.size();
		this.E = edges.size();
		//setA = new ArrayList<GraphVertex>();
		//setB = new ArrayList<GraphVertex>();
		countGroups = verts.size();
	}

	/**
	 * Returns the number of edges in this graph.
	 *
	 * @return the number of edges in this graph
	 */
	public int getE() {
		return E;
	}


	/**
	 * Returns the number of vertices in this graph.
	 *
	 * @return the number of vertices in this graph
	 */
	public int getV() {
		return V;
	}


	/**
	 * Returns the vertices adjacent to vertex {@code v}.
	 *
	 * @param  v the vertex
	 * @return the vertices adjacent to vertex {@code v}, as an iterable
	 * 
	 */
	public ArrayList<GraphVertex> getAdjacentList(GraphVertex v) {
		//validateVertex(v);
		return adj.get(v.getStringID());
	}

	/**
	 * Returns the degree of vertex {@code v}.
	 *
	 * @param  v the vertex
	 * @return the degree of vertex {@code v}
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public int degree(GraphVertex v) {
		//validateVertex(v);
		return this.getAdjacentList(v).size();
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
//	void union(GraphVertex p, GraphVertex q)
//	{
//		int pid = id[Integer.parseInt(p.getStringID())];
//		int qid = id[Integer.parseInt(q.getStringID())];
//		for (int i = 1; i < id.length; i++)
//			if (id[i] == pid) id[i] = qid;
//	}
	
	// Test the twoPassRoot method
	static int twoPassRoot_Test(int node_i_index, int[] myId) {
		ArrayList<Integer> visited = new ArrayList<Integer>();
		while(node_i_index != myId[node_i_index]) // when the given node is not the root node 
		{	
			visited.add(node_i_index);
			node_i_index = myId[node_i_index]; //chase parent pointers until reach root
			//visited.add(node_i_index);
		}
		// for all visited node, set its id to point to the root node
		for(Integer v: visited) {
			//if(id[v] != node_i_index)
				myId[v] = node_i_index;
		}
		return node_i_index;
	}
	
	// Time complexity: O(logV), logV is the maximum height of any tree
	private int twoPassRoot(int node_i_index) {
		ArrayList<Integer> visited = new ArrayList<Integer>();
		while(node_i_index != this.id[node_i_index]) // when the given node is not the root node 
		{	
			visited.add(node_i_index);
			node_i_index = id[node_i_index]; //chase parent pointers until reach root
			//visited.add(node_i_index);
		}
		// for all visited node, set its id to point to the root node
		for(Integer v: visited) {
			//if(id[v] != node_i_index)
				id[v] = node_i_index;
				
		}
		return node_i_index;
	}
	// Time complexity: O(1)
	private boolean connected(GraphVertex p, GraphVertex q) {
		int i = id[Integer.parseInt(p.getStringID())];
		int j = id[Integer.parseInt(q.getStringID())];
		return i == j;
	}
	
	// V - 2 calls to union method. Each call takes V times so in total O(V^2). Too slow not scale.
	// Expect one call to union method only takes lgV
	private void union(GraphVertex p, GraphVertex q)
	{
		int p_root_id = twoPassRoot(Integer.parseInt(p.getStringID())); // takes at most logV
		int q_root_id = twoPassRoot(Integer.parseInt(q.getStringID()));
		
		if(p_root_id == q_root_id) return;
		
		if(this.sz[p_root_id] < this.sz[q_root_id]) {
			id[p_root_id] = q_root_id;
			sz[q_root_id] += sz[p_root_id];
		}
		else {
			id[q_root_id] = p_root_id;
			sz[p_root_id] += sz[q_root_id];
		}
		
		// at most 2*V array accesses
		for (int i = 1; i < id.length; i++)
			if (id[i] == p_root_id) id[i] = q_root_id;
	}
	
	// Time complexity: O(M) including random access O(1) and rearrange the array O(M)
	public void removeEdge(GraphEdge u, ArrayList<GraphEdge> currEdge) {
		currEdge.remove(u);
		//currEdge.remove(edgeToRemove);
	}
	
	long kargerMinCut() {

		long numMinCut = 0;

		//make a deep copy of the edge list, vertex list and array list of adjacency lists
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
		
		//Is it in the same order as the original?
		HashMap<String, ArrayList<GraphVertex>> currAdj = new HashMap<String, ArrayList<GraphVertex>>();

		for (Map.Entry<String, ArrayList<GraphVertex>> entry : adj.entrySet()) {
			currAdj.put(entry.getKey(), new ArrayList<>(entry.getValue()));
		}
		
		
		ArrayList<GraphVertex> setA = new ArrayList<GraphVertex>();
		ArrayList<GraphVertex> setB = new ArrayList<GraphVertex>();
		
		
		//Debug purpose
		//String twoPartitions = "";
		
		//System.out.println("Before the Karger's algo");

		//this.printGraph(currAdj);

		//write your random edge selector, selected edge
		while(countGroups > 2) {
			if(currEdges.size() > 0) {

				this.selectEdge(currEdges, currAdj);

				//if u and v are disconnected, merge (or “contract” ) u and v into a single vertex
				// This if block is called (V - 2) times
				if(!this.connected(selected.v0, selected.v1)) {

					// Time complexy O(V)
					this.union(selected.v0, selected.v1);


					//Update the group count
					countGroups--;
				}
				else { // two vertices share the same root
					
				}
				//Remove the selected edge from the current edge list for randomly selecting the unscanned edges
				// Time complexity O(M)
				//this.removeEdge(selected, currEdges);

				//Debug 
//				for(int i = 1; i < id.length; i++) {
//
//					if(i == 1)
//						twoPartitions = "[(" + id[1];
//					else {
//						twoPartitions +=  "," + id[i];
//						if(i == id.length - 1)
//							twoPartitions += ")]";
//					}
//
//				}
//
//				System.out.println("\ncuts are " + twoPartitions);
			}

		}
		
		//Form two components - O(V)
		for(int i = 1; i < id.length; i++) {
			
			if(id[i] == id[1]) // same root? 
			{
				setA.add(currVerts.get(i - 1));
			}
			else {
				setB.add(currVerts.get(i - 1));
			}
				
		}
		
		//cuts are [(1,7), (4,5)]
		
// Debug purposes		
//		twoPartitions = "";
//		
//		for(int i = 0; i < setA.size(); i++){
//			if(i == 0)
//				twoPartitions += "[(";
//				
//			twoPartitions += "," + setA.get(i).getStringID() ;
//			
//			if(i == setA.size() - 1)
//				twoPartitions += ")";
//		} 
//		
//		for(int i = 0; i < setB.size(); i++){
//			if(i == 0)
//				twoPartitions += ", (";
//				
//			twoPartitions += "," + setB.get(i).getStringID();
//			
//			if(i == setB.size() - 1)
//				twoPartitions += ")]";
//		} 
//			 		
//		System.out.println(); 	
		
		
		
		
		
		//when finish the contraction, only 2 group of vertices are left only
		// loop through these two separate components to find the edges belong to different components
		for(GraphVertex u: setB) {
			if(currAdj.containsKey(u.getStringID())){
				ArrayList<GraphVertex> neighborList = currAdj.get(u.getStringID());
				for(GraphVertex v: setA) {
					if(neighborList.contains(v)) {
						numMinCut++;
					}
				}
			}
		}
		
		//System.out.println("\ncuts are " + twoPartitions + ", numberMinCut= " + numMinCut);
	
		//reset for the next loop
		selected = null;
		//selectedEdges = null;
		currAdj = null;
		currEdges = null;
		currVerts = null;
		
		
		countGroups = verts.size();
		
		for (int i = 1; i <= this.getV(); i++) {
			id[i] = Integer.parseInt(verts.get(i - 1).getStringID());
			sz[i] = 1;
		}

		//the number of edges linking these vertices is the minimum number of crossing edges in the min cut
		return numMinCut;
	}

	//Randomly select an edge and update the selectedEdges list
	//MUST DO: parameter EdgeList
	// Should include the adjacency list here?
	// Complexity : constant time
	void selectEdge(ArrayList<GraphEdge>currEdges, HashMap<String, ArrayList<GraphVertex>> adj) {

		Random rand = new Random();
		GraphEdge temp = null;
		//Randomly select an edge from the current edge list

		int r =  rand.nextInt(currEdges.size());
		//avoid modifying the edge list
		// constant time
		temp = currEdges.get(r);
		//the adjacency list is updated after each loop so only selecting the remaining edges



//		if(selectedEdges == null) {
//			selectedEdges = new ArrayList<GraphEdge>();
//			//existed = false;
//		}
//
//		//add the selected edge to the list
//		selectedEdges.add(temp);
		selected = temp;

		//System.out.println("Selected edge = " + selected.toString());
		//System.out.println("My selected edge's size is " + selectedEdges.size());

		//no else here 

	}

	/**
	 * Unit tests the {@code Graph} data type.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		
		int[] input = {0, 0, 0, 1, 1, 1, 3, 3, 6, 6, 8, 9, 9};
		
		twoPassRoot_Test(9, input);
		
		String result = "[";
		for(int i = 0; i < input.length; i++) {
			result += input[i] + ",";
			if(i == input.length - 1)
				result += "]";
		}
		
		System.out.println(result);
	}
}
    
    


