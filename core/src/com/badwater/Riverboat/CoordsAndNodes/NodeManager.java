package com.badwater.Riverboat.CoordsAndNodes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import com.badwater.Riverboat.CoordsAndNodes.Node.nodeType;
import com.badwater.Riverboat.Helpers.Helpers;

public class NodeManager {

	private ArrayList<Node> nodeList = new ArrayList<Node>();

	public NodeManager() {

		// TODO Auto-generated constructor stub
	}

	private ArrayList<Coord> pickNodes(int sizeW, int sizeH) {
		// pick new node that moves closer to the end node in either direction.
		// int totalDistance, int distance2, int mod = Integer.signum(distance2
		// - distance1);
		ArrayList<Coord> coordList = new ArrayList<Coord>();
		for (int i = 0; i < 25; i++){
			coordList.add(new Coord(Helpers.getRandomRange(sizeW, 0), Helpers.getRandomRange(sizeH, 0)));
		}
		return coordList;
	}

	public ArrayList<Coord> createPath(Coord start, Coord end, int sizeW, int sizeH) {
		ArrayList<Coord> Nodes = pickNodes(sizeW, sizeH);
		ArrayList<Coord> path = new ArrayList<Coord>();
		Nodes.add(0, start);
		Nodes.add(end);
		for(Coord c1 : Nodes){
			for(Coord c2 : Nodes){
				if(c1.getX() == c2.getX() && c1.getY() == c2.getY()){
					//same node. ignore.
					break;
				}
				else{
					
				}
				
			}
			
		}
		path.add(end);	
		for (Coord c : path){
			System.out.println("X: " + c.getX() +"\nY: " + c.getY());
		}
		return path;
	}

	private int measureNodes(Coord start, Coord end) {
		int distance = 0;

		int x1 = start.getX();
		int x2 = end.getX();
		int y1 = start.getY();
		int y2 = end.getY();

		int a = x2 - x1;
		int b = y2 - y1;


		distance = (int) Math.sqrt((a * a) + (b * b));
		return distance;
	}
}
