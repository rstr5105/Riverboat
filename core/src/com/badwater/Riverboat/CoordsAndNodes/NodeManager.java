package com.badwater.Riverboat.CoordsAndNodes;

import java.util.ArrayList;
import java.util.List;

import com.badwater.Riverboat.Helpers.Helpers;

public class NodeManager {

	public NodeManager() {

		// TODO Auto-generated constructor stub
	}

	private ArrayList<Node> pickNodes(int sizeW, int sizeH) {

		ArrayList<Node> nodeList = new ArrayList<Node>();
		for (int i = 0; i < 25; i++) {
			Node node = new Node(new Coord(Helpers.getRandomRange(sizeW, 0), Helpers
					.getRandomRange(sizeH, 0)));
			nodeList.add(node);
		}
		return nodeList;
	}

	public ArrayList<Node> createPath(Coord start, Coord end, int sizeW,
			int sizeH) {
		ArrayList<Node> Nodes = pickNodes(sizeW, sizeH);
		ArrayList<Node> sortedNodes = new ArrayList<Node>();
		ArrayList<Node> path = new ArrayList<Node>();
		
		for(Node current : Nodes){
			Node shortestNode;
			double distance = (double) Integer.MAX_VALUE;
			if(!current.hasNext()){
				for(Node next : Nodes){
					if (current.equals(next)){
						boolean blah = current.equals(next);
						//same node, ignore.
							break;
					}
					else{
						double d2 = measureNodes(current, next);
						if (d2 < distance){
							distance = d2;
							shortestNode = next;
							current.setNext(shortestNode);
						}
					}
				}
			}
			else if(current.hasNext()){
				for (Node next : Nodes){
					if(next.equals(current.getNext())){
						//same node, ignore.
						break;
					}
					else{
						double d2 = measureNodes(current.getNext(), next);
						if (d2 < distance){
							distance = d2;
							shortestNode = next;
							current.getNext().setNext(shortestNode);
							
						}
					}
				}
			sortedNodes.add(current);
			}
		}
		for (Node n : sortedNodes){
			if(n.hasNext()){
				System.out.println("X: " + n.getX() + " Y: " + n.getY() 
						+  "\nNext X: " + n.getNext().getX() + "NextY: " + n.getNext().getY()
						+ "Distance: " + measureNodes(n, n.getNext()));
			}
		}
		return null;
	}

	private double measureNodes(Node start, Node end) {
		double distance = 0;

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
