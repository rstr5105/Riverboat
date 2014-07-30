package com.badwater.Riverboat.CoordsAndNodes;


public class Node extends Coord {
	
	public enum nodeType{
		RIVER_NODE,
		PATHFINDER_NODE;
	}
	
	private nodeType nodeType;
	private Node next;
	
	public Node(){
		
	}
	
	public Node(Coord c){
			super(c.getX(), c.getY());
			
			// TODO Auto-generated constructor stub
	}
	
	public nodeType nodeType(){
		
		return this.nodeType;
	}
	
	public void setNodeType(nodeType nodeType){
		this.nodeType = nodeType;
	}
	
	public void setNext(Node next){
		this.next = next;
	}
	
	public Node getNext(){
		return next;
	}
	
	public boolean hasNext(){
		if (next != null){
			return true;
		}
		else return false;
	}
	
}
