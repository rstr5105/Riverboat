package LevelGenerator;

//Java Imports

//gdx imports.
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
//local imports
import com.badwater.Riverboat.CoordsAndNodes.Coord;
import com.badwater.Riverboat.CoordsAndNodes.Node;
import com.badwater.Riverboat.CoordsAndNodes.Node.nodeType;
import com.badwater.Riverboat.CoordsAndNodes.NodeManager;
import com.badwater.Riverboat.Helpers.Helpers;
import com.badwater.Riverboat.Level.*;

public class LevelGenerator {
	// constant size ints.
	private static final int SIZE_H = 100; // Vertical size in tiles;
	private static final int SIZE_W = 100; // Horizontal size in tiles;

	private TextureAtlas atlas = new TextureAtlas("images/tiles/Tiles.pack");
	private Level level;
	private Coord genCenter = new Coord(0, 0);

	TiledMapTileLayer riverLayer = new TiledMapTileLayer(SIZE_W, SIZE_H, 64, 64);
	private NodeManager nodeManager;
	

	public LevelGenerator() {
		level = new Level();
		genCenter.setX(SIZE_W / 2);
		createRiverAndTerrainLayer();
	}

	private void createRiverAndTerrainLayer() {
		// draw our starting point
		int radius = 3;
		nodeManager = new NodeManager();
		Coord start = new Coord(Helpers.getRandomRange(SIZE_W, 0) , 0);
		Coord end = new Coord(Helpers.getRandomRange(SIZE_W, 0), SIZE_H);
		
		drawRiverAtPoint(genCenter, radius);
		ArrayList<Coord> path = nodeManager.createPath(start, end, SIZE_W, SIZE_H);
		drawRiver(path);
		drawShores();
	}


	private void drawRiver(ArrayList<Coord> path) {
		// draw a river in a mostly up direction!
		for (Coord c: path){
			drawRiverAtPoint(c, 3);
		}
	}

	private void drawShores() {
		Cell cell = new Cell();
		cell.setTile(new StaticTiledMapTile(atlas.findRegion("sand")));
		for (int y = 0; y < SIZE_H; y++) {
			for (int x = 0; x < SIZE_W - 1; x++) {
				if (riverLayer.getCell(x + 1, y) != null) {
					riverLayer.setCell(x, y, cell);
					break;
				}
			}
			for (int x = SIZE_W; x > 0; x--) {
				if (riverLayer.getCell(x - 1, y) != null) {
					riverLayer.setCell(x, y, cell);
					break;
				}
			}
		}
		for (int x = 0; x < SIZE_W; x++) {
			for (int y = 0; y < SIZE_H; y++) {
				if (riverLayer.getCell(x, y + 1) != null) {
					riverLayer.setCell(x, y, cell);
					break;
				}
			}
			for (int y = SIZE_H; y > 0; y--) {
				if (riverLayer.getCell(x, y - 1) != null) {
					riverLayer.setCell(x, y, cell);
					break;
				}
			}
		}
	}

	private void goNorth(Coord c) {
		// move the brush up 1
		c.setY(c.getY() + 1);
	}

	private void goNorthEast(Coord c) {
		// move the brush up 1 and right 1
		goNorth(c);
		goEast(c);
	}

	private void goNorthWest(Coord c) {
		// move the brush up 1 and left 1
		goNorth(c);
		goWest(c);
	}

	private void goEast(Coord c) {
		// move the brush right 1
		c.setX(c.getX() + 1);
	}

	private void goWest(Coord c) {
		// move the brush left 1
		c.setX(c.getX() - 1);
	}

	private void drawRiverAtPoint(Coord genCenter, int radius) {

		Cell cell = new Cell();
		cell.setTile(new StaticTiledMapTile(atlas.findRegion("water")));
		int r2 = radius * radius;
		while (r2 >= 0) {
			riverLayer.setCell(genCenter.getX(), genCenter.getY() + radius,
					cell);
			riverLayer.setCell(genCenter.getX(), genCenter.getY() - radius,
					cell);
			riverLayer.setCell(genCenter.getX() + radius, genCenter.getY(),
					cell);
			riverLayer.setCell(genCenter.getX() - radius, genCenter.getY(),
					cell);
			int x = 0;
			int y = (int) (Math.sqrt(r2 - 1) + 0.5);
			while (x < y) {
				riverLayer.setCell(genCenter.getX() + x, genCenter.getY() + y,
						cell);
				riverLayer.setCell(genCenter.getX() + x, genCenter.getY() - y,
						cell);
				riverLayer.setCell(genCenter.getX() - x, genCenter.getY() + y,
						cell);
				riverLayer.setCell(genCenter.getX() - x, genCenter.getY() - y,
						cell);
				riverLayer.setCell(genCenter.getX() + y, genCenter.getY() + x,
						cell);
				riverLayer.setCell(genCenter.getX() + y, genCenter.getY() - x,
						cell);
				riverLayer.setCell(genCenter.getX() - y, genCenter.getY() + x,
						cell);
				riverLayer.setCell(genCenter.getX() - y, genCenter.getY() - x,
						cell);
				x += 1;
				y = (int) (Math.sqrt(r2 - x * x) + 0.5);
			}
			if (x == y) {
				riverLayer.setCell(genCenter.getX() + x, genCenter.getY() + y,
						cell);
				riverLayer.setCell(genCenter.getX() + x, genCenter.getY() - y,
						cell);
				riverLayer.setCell(genCenter.getX() - x, genCenter.getY() + y,
						cell);
				riverLayer.setCell(genCenter.getX() - x, genCenter.getY() - y,
						cell);
			}
			r2--;
		}
		level.createLayer(riverLayer);
	}

	public Level getLevel() {
		return level;
	}
}
