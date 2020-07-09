package couse1.week4;

import java.util.Comparator;

public class GraphEdge {
	
	GraphVertex v0,v1;

	public GraphEdge( GraphVertex _v0, GraphVertex _v1){
		v0 = _v0;
		v1 = _v1;

	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		GraphEdge temp = (GraphEdge) obj;
		return this.v0.equals(temp.v0) && this.v1.equals(temp.v1);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + v0.getStringID() + "," + v1.getStringID() + ")";
	}
  
}
