package com.badwater.Riverboat.CoordsAndNodes;

public class Coord {
	private int x;
	private int y;
	
	public Coord(){
	}
	
	public Coord(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int newX){
		this.x = newX;
	}
	public void setY(int newY){
		this.y = newY;
	}
}
