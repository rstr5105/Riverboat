package com.badwater.Riverboat.Level;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Level {

	private TiledMap tiled;
	private TextureAtlas atlas;
	private MapLayers layers;
	public Level() {
		tiled = new TiledMap();
		layers = tiled.getLayers();
	}
	
	

	public TiledMap getMap() {
		return tiled;
	}

	public void createLayer(TiledMapTileLayer layer){
		layers.add(layer);
	}


	public MapLayers getLevelLayers() {
		// TODO Auto-generated method stub
		return null;
	}
}
