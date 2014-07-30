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
		for (int i = 0; i < 5; i++) {
			Node node = new Node(new Coord(Helpers.getRandomRange(sizeW, 0),
					Helpers.getRandomRange(sizeH, 0)));
			nodeList.add(node);
		}
		return nodeList;
	}

	public ArrayList<Coord> createPath(Node start, Node end, int sizeW,
			int sizeH) {
		ArrayList<Node> Nodes = pickNodes(sizeW, sizeH);
		ArrayList<Node> sortedNodes = new ArrayList<Node>();
		ArrayList<Coord> path = new ArrayList<Coord>();
		Node startN = (Node) start;
		while(!Nodes.isEmpty()){
			sortedNodes.add(startN);
			int distanceLast = Integer.MAX_VALUE - 1;
			Node tmp = new Node();
			for(Node n : Nodes){
				int distance = measureNodes(startN, n);
				System.out.println(distance);
				if (distance < distanceLast){
					distanceLast = distance;
					startN = n;
				}
			}
			Nodes.remove(startN);
		
		}
		System.out.println(sortedNodes.size());
		
		for(Node n : sortedNodes){
			for(Node n1 : sortedNodes){
				int xMod = Integer.signum(n1.getX() - n.getX());
				int yMod = Integer.signum(n1.getY() - n.getY());
				
				int length = (int) measureNodes(n, n1);
				
				for (int i = 0; i < length; i++){
					Coord c = new Coord(n.getX() + xMod * i, n.getY() + yMod * i);
					if (!path.contains(c)){
						path.add(c);
					}
				}
			}
		}
		for(Coord c : path){
			System.out.println(c.getX() + " : " + c.getY() );
		}
		return path;
	}

	private int measureNodes(Node start, Node end) {
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
