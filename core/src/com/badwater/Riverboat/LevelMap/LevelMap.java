package com.badwater.Riverboat.LevelMap;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badwater.Riverboat.Helpers.Helpers;

public class LevelMap {

	private TiledMap tiled;
	private Texture texture;
	private TextureAtlas atlas;
	private static Random random = new Random();

	public LevelMap() {

		tiled = new TiledMap();
		MapLayers layers = tiled.getLayers();

		atlas = new TextureAtlas("images/tiles/Tiles.pack");

		TiledMapTileLayer terrain = new TiledMapTileLayer(32, 256, 64, 64);
		// get the center of the terrain map layer
		int centerX = terrain.getWidth() / 2;
		// initially set the center of the river to the center of the map layer.
		int riverCenterX = centerX;
		// count how many times the river has simply gone straight without
		// shifting left or right.
		int straightCount = 0;
		// count how many times the river has not changed size.
		int sameSizeCount = 0;
		// I don't remember why I initialized this to two, but I assume that at
		// 3:00 this morning, I had a reason.
		int xMod = 1;

		// start placing tiles into the world map, origin is bottom left, which
		// is fucking ridiculous if you ask me.
		for (int y = 0; y <= terrain.getHeight() - 1; y++) {
			// This Is Bravo-Sierra
			// okay, here we're counting to see whether the river has stayed the
			// same size for more that five iterations.
			// if so, we (essentially) flip a coin to see whether or not to
			// change size.
			// if we do change size, we reset sameSizeCount to 0 so that we can
			// start counting again.
			// this episode brought to you by the letter High.
			if (straightCount > 3) {
				if (Helpers.coinFlip(.45f)) {
					if ((riverCenterX <= centerX + 3)
							&& (riverCenterX >= centerX - 3)) {
						// okay, now add rand to the center of the river.
						// If its +1 we shift right, if it's 0 we stay, and if
						// it's -1 we move left.
						int rand = Helpers.getRandomRange(1, -1);
						riverCenterX = rand + riverCenterX;
					}
					else if (riverCenterX <= centerX - 5) {
						riverCenterX++;
					}
					else if (riverCenterX >= centerX + 5) {
						riverCenterX--;
					}
					straightCount = 0;
				}
				if (sameSizeCount > 5) {
					if (Helpers.coinFlip(.45f)) {
						xMod = Helpers.getRandomRange(2, 1);
						sameSizeCount = 0;
						straightCount --;
					}	
				}
				else {
					sameSizeCount++;
				}
			}
			else {
				straightCount++;
			}
			for (int x = 0; x < terrain.getWidth(); x++) {
				Cell cell = new Cell();
				if (x < riverCenterX - xMod - 1 || x > riverCenterX + xMod + 1) {
					// dynamic generation
				}
				// if we're a shore, make us a fucking shore already!
				else if (x == riverCenterX - xMod - 1
						|| x == riverCenterX + xMod + 1) {
					cell.setTile(new StaticTiledMapTile(atlas
							.findRegion("sand")));
				}
				// this is the river, so we need to make it rivery-like
				else {
					cell.setTile(new StaticTiledMapTile(atlas
							.findRegion("water")));
				}
				terrain.setCell(x, y, cell);
			}
		}
		layers.add(terrain); // hopefully get this fucking thing to render.
	}

	public TiledMap getMap() {
		return tiled;
	}
}
