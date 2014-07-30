package com.badwater.Riverboat.CoordsAndNodes;


public class Node extends Coord {
	
	public enum nodeType{
		RIVER_NODE,
		PATHFINDER_NODE;
	}
	
	private nodeType nodeType;
	
	public Node(Coord c){
			super(c.getX(), c.getY());
			
			// TODO Auto-generated constructor stub
	}
	
	nodeType nodeType(){
		
		return this.nodeType;
	}
	
	public void setNodeType(nodeType nodeType){
		this.nodeType = nodeType;
	}
	
	
}
