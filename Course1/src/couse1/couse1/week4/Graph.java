package couse1.week4;

import java.util.*;




public class Graph {

	private final int V;
	private int E;
	
	//private ArrayList<GraphVertex> setA;
	//private ArrayList<GraphVertex> setB;
	private int[] id;
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
		for (int i = 1; i <= this.getV(); i++)
			id[i] = Integer.parseInt(verts.get(i - 1).getStringID());
		
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
	private boolean connected(GraphVertex p, GraphVertex q) {
		int pid = id[Integer.parseInt(p.getStringID())];
		int qid = id[Integer.parseInt(q.getStringID())];
		return pid == qid;
	}
	//Modify the vertex and edge list
	void union(GraphVertex p, GraphVertex q)
	{
		int pid = id[Integer.parseInt(p.getStringID())];
		int qid = id[Integer.parseInt(q.getStringID())];
		for (int i = 1; i < id.length; i++)
			if (id[i] == pid) id[i] = qid;
	}
	
	
	public void removeEdge(GraphEdge u, ArrayList<GraphEdge> currEdge) {
		// Check whether the input vertices both exist
		//GraphVertex end1 = findVtx(end1ID), end2 = findVtx(end2ID);
//		if ((end1 == null) || (end2 == null)) {
//			throw new IllegalArgumentException("The input vertices don't both exist.");
//		}
		// Check whether the edge to remove exists
		GraphEdge edgeToRemove = null;
		for(GraphEdge temp:currEdge) {
			if(temp.v0.equals(u.v1) && temp.v1.equals(u.v0) ) {
				edgeToRemove = temp;
				break;
			}
		}
		if (edgeToRemove == null) {
			throw new IllegalArgumentException("The edge to remove doesn't exist.");
		}

		currEdge.remove(u);
		currEdge.remove(edgeToRemove);
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
		
		//debug purpose
		String twoPartitions = "";

		//System.out.println("Before the Karger's algo");

		//this.printGraph(currAdj);

		//write your random edge selector, selected edge
		while(countGroups > 2) {
			if(currEdges.size() > 0) {

				this.selectEdge(currEdges, currAdj);

				//merge (or “contract” ) u and v into a single vertex
				if(!this.connected(selected.v0, selected.v1)) {
					this.union(selected.v0, selected.v1);

					//Update the group count
					countGroups--;

					//			System.out.println("countGroups= " + countGroups);
					//			for (int i = 0; i <= this.getV(); i++)
					//				System.out.println("i= " + i + "," + "id[i]= " + id[i]);

					//Add [u], and [v] to the first group 
					//			if(!setA.contains(selected.v0))
					//				setA.add(selected.v0);
					//
					//			if(!setA.contains(selected.v1))
					//				setA.add(selected.v1);

					//Remove [u] and [v] in the copied, original adjacency list
					//			if(currAdj.containsKey(selected.v0.getStringID()))
					//				currAdj.remove(selected.v0.getStringID());
					//			if(currAdj.containsKey(selected.v1.getStringID()))
					//				currAdj.remove(selected.v1.getStringID());
				}
				//Remove the selected edge from the current edge list for randomly selecting the unscanned edges
				this.removeEdge(selected, currEdges);

				//Remove [u] and [v] from the copied, original vertex list
				//currVerts.remove(selected.v0.getStringID());
				//currVerts.remove(selected.v1.getStringID());

				for(int i = 1; i < id.length; i++) {

					if(i == 1)
						twoPartitions = "[(" + id[1];
					else {
						twoPartitions +=  "," + id[i];
						if(i == id.length - 1)
							twoPartitions += ")]";
					}

				}

				System.out.println("\ncuts are " + twoPartitions);


			}

		}
		
		//setA.add(currVerts.get(id[1] - 1));
		for(int i = 1; i < id.length; i++) {
			
			if(id[i] == id[1]) {
				setA.add(currVerts.get(i - 1));
			}
			else {
				setB.add(currVerts.get(i - 1));
			}
				
		}
//		for(GraphVertex u: setA) {
//			if(!currVerts.contains(u)) {
//					setB.add(u);
//			}
//		}
		
twoPartitions = "";
		
		for(int i = 0; i < setA.size(); i++){
			if(i == 0)
				twoPartitions += "[(";
				
			twoPartitions += "," + setA.get(i).getStringID() ;
			
			if(i == setA.size() - 1)
				twoPartitions += ")";
		} 
		
		for(int i = 0; i < setB.size(); i++){
			if(i == 0)
				twoPartitions += ", (";
				
			twoPartitions += "," + setB.get(i).getStringID();
			
			if(i == setB.size() - 1)
				twoPartitions += ")]";
		} 
			 		
		System.out.println(); 	
		
		
//		System.out.println("\nlist of vertex in group A");
//			
//		for(int i = 0; i < setA.size(); i++){ 
//			System.out.print(" A-> "+ setA.get(i).getStringID()); 
//		} 
//		System.out.println(); 
//		
//		System.out.println("\nlist of vertex in group B");
//		
//		for(int i = 0; i < setB.size(); i++){ 
//			System.out.print(" B -> "+ setB.get(i).getStringID()); 
//		} 
//		System.out.println(); 
		
		//when finish the contraction, only 2 group of vertices are left only
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

		System.out.println("\ncuts are " + twoPartitions + ", numberMinCut= " + numMinCut);
		
		//reset for the next loop
		selected = null;
		//selectedEdges = null;
		currAdj = null;
		currEdges = null;
		currVerts = null;
		
		
		countGroups = verts.size();
		
		for (int i = 1; i <= this.getV(); i++)
			id[i] = Integer.parseInt(verts.get(i - 1).getStringID());

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

		System.out.println("Selected edge = " + selected.toString());
		//System.out.println("My selected edge's size is " + selectedEdges.size());

		//no else here 

	}

	void contractAnEdge(GraphEdge selectedEdge, ArrayList<GraphVertex> currVerts, ArrayList<GraphEdge>currEdges, HashMap<String, ArrayList<GraphVertex>> currAdj) {
		//A for searches the list Vertices[u.v1] for the occurrence of u.v0 and deletes it
		if(currAdj.containsKey(selectedEdge.v1.id)) {
			//constant time for get and put methods
			ArrayList<GraphVertex> neighborListV1 = currAdj.get(selectedEdge.v1.id);
			//delete the neighboring vertex
			neighborListV1.remove(selectedEdge.v0);
			//verts.remove(u.v0);

			//update the Hashmap
			currAdj.put(selectedEdge.v1.id, neighborListV1);

			//Add the list Vertices[v1] to the list Vertices[v0]
			ArrayList<GraphVertex> neighborListV0 = currAdj.get(selectedEdge.v0.id);

			// Time complexity?
			neighborListV0.addAll(neighborListV1);

			neighborListV0.remove(selectedEdge.v1);

			//update the Hashmap
			currAdj.put(selectedEdge.v0.id, neighborListV0);

			//delete the Vertices[v1]
			currAdj.remove(selectedEdge.v1.getStringID());

			currVerts.remove(selectedEdge.v1);

			//then delete the edge [v0, v1].
			//currEdges.remove(u);

		} 		 		

		//For all vertices in the adjacency list, changing all occurrences of v1 to v0
		// Time complexity V*V
		for(GraphVertex v:currVerts) {
			ArrayList<GraphVertex> neighborList = currAdj.get(v.id);
			
			for(GraphVertex temp: neighborList) {
				if(temp.getStringID().equals(selectedEdge.v1.getStringID())) {
					//replace vertex v1 with v0, indexOf complexity?
					neighborList.set(neighborList.indexOf(temp), selectedEdge.v0);
					//update the Hashmap accordingly
					currAdj.put(v.id, neighborList);
				}	
			}


		}

		//remove self loops in the super node
		ArrayList<GraphVertex> neighborList = currAdj.get(selectedEdge.v0.id);
		//ArrayList<GraphVertex> selfloop = new GraphVertex();
		ArrayList<Integer> selfloop = new ArrayList<Integer>();
		int i = neighborList.size() - 1;

		for(; i >= 0;i--) {
			//System.out.println("head " + selectedEdge.v0.getStringID() + "-> " + neighborList.get(i).getStringID());
			if(neighborList.get(i).getStringID().equals(selectedEdge.v0.getStringID())) {
				//remove the self loop
				//neighborList.remove(temp);
				selfloop.add(i);
				//System.out.println("Self loop is at = " + i);
				//update the Hashmap accordingly
				//currAdj.put(u.v0.id, neighborList);
			}
		}

		for(int j = 0; j < selfloop.size(); j++)
			neighborList.remove((int) selfloop.get(j));

		currAdj.put(selectedEdge.v0.id, neighborList);

		//printGraph(currAdj);
		
		
	}
	/**
	 * Unit tests the {@code Graph} data type.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {

	}
}
    
    


