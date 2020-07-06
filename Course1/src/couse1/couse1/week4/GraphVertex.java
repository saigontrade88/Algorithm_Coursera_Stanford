/**
 * 
 */
package couse1.week4;

import java.util.Comparator;

/**
 * @author Dr.Rosen at prosen@usf.edu.
 *
 */
public class GraphVertex implements Comparator<GraphVertex> {
	String id;
	
	/****Dijkstras*****/
	int dId;
	
	//degree
	float cost;
	/****Dijkstras*****/

	public GraphVertex() {}
	
	public GraphVertex( String _id, int _group, float _x, float _y, int _dId) {
		id = _id;

		dId = _dId;
	}
	
	public GraphVertex(String _id, int _dId, float _cost){
		id = _id;
		dId = _dId;
		cost = _cost;
	}

	public GraphVertex(int _dId, float _cost){

		dId = _dId;
		cost = _cost;
	}

	public String getStringID() { 
		return id;
	}

	public int getNumbID(){
		return dId;
	}

	public int getCost(){
		return (int) cost;
	}

	@Override
	public int compare(GraphVertex node1, GraphVertex node2) {

		if(node1.cost < node2.cost) return -1;
		if(node1.cost > node2.cost) return 1;
		return 0;

	}

}
