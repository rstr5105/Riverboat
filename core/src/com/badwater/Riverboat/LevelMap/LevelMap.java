package com.badwater.Riverboat.LevelMap;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class LevelMap {
	
	private TiledMap tiled;
	private Texture texture;
	private TextureAtlas atlas;
	private static Random random = new Random();
	
	public LevelMap(){
		
		
		tiled = new TiledMap();
		MapLayers layers = tiled.getLayers();
		
		atlas = new TextureAtlas("images/tiles/Tiles.pack");
		
		TiledMapTileLayer terrain = new TiledMapTileLayer(32, 1024, 64, 64);
		int centerX = terrain.getWidth() /2;
		int riverCenterX = centerX;
		int straightCount = 0;
		int sameSizeCount = 0;
		int xMod = 2;
		for (int y = 0 - 1; y <= terrain.getHeight() - 1; y++){
			if( sameSizeCount > 5){
				float chance = .45f;
				float rn = random.nextFloat();
				if (rn < chance){
					xMod = random.nextInt((4 - 2 ) + 1) + 2;
					sameSizeCount = 0;
				}
				else{
					sameSizeCount++;
				}
			}
			if(straightCount > 3){
				if(riverCenterX == centerX){
					float chance = .45f;
					float rn = random.nextFloat();
					if (rn < chance){
						int rand = random.nextInt((1 - (-1)) + 1 ) + (-1);
						riverCenterX = rand + riverCenterX;
						straightCount = 0;
					}
					else{
						riverCenterX = centerX;
						straightCount = 0;
					}
				}
			}
				for (int x = 0; x < terrain.getWidth(); x++){
					Cell cell = new Cell();
					if(x < riverCenterX - xMod - 1
						|| x > riverCenterX + xMod+1){
							//dynamic generation
					}
					else if(x == riverCenterX - xMod - 1
							|| x == riverCenterX + xMod + 1){
								cell.setTile(new StaticTiledMapTile(atlas.findRegion("sand")));
					}
					else{
						cell.setTile(new StaticTiledMapTile(atlas.findRegion("water")));
					}
				terrain.setCell(x, y, cell);
				}
			straightCount +=1;
			sameSizeCount +=1;
		}
		layers.add(terrain); //hopefully get this fucking thing to render.
	}
	
	public TiledMap getMap(){
		return tiled;
	}
}
