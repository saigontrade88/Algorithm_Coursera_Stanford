/**
 * 
 */
package couse1.week4;

import java.util.Comparator;

/**
 * @author Dr.Rosen at prosen@usf.edu.
 *
 */
public class GraphVertex implements Comparable {
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
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return this.id.compareTo(((GraphVertex)arg0).id);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.compareTo(obj) == 0;
	}

	

}
