package com.badwater.Riverboat;

import LevelGenerator.LevelGenerator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badwater.Riverboat.Level.Level;
import com.badwater.Riverboat.LevelMap.LevelMap;

public class Riverboat extends Game implements GestureListener {
	SpriteBatch batch;
	Texture img;
	private BitmapFont font;
	private Sprite sprite;
	private Screen screen;
	private OrthographicCamera camera;
	private TiledMapRenderer renderer;
	private float h;
	private float w;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		h = Gdx.graphics.getHeight();
		w = Gdx.graphics.getWidth();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		Level level = new LevelGenerator().getLevel();
		TiledMap levelMap = level.getMap();
		font = new BitmapFont();
		renderer = new OrthogonalTiledMapRenderer(levelMap);
		font.setColor(Color.RED);
		img = new Texture(Gdx.files.internal("badwaterlogo.png"));
		img.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		sprite = new Sprite(img);
		sprite.setOrigin(0, 0);
		sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);
		
		System.out.println(Gdx.graphics.getWidth());
		System.out.println(Gdx.graphics.getHeight());
		Gdx.input.setInputProcessor(new GestureDetector(this));
		
	}
	
	public void update(){
		
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		renderer.setView(camera);
		renderer.render();
	}
	
	public void dispose(){
		batch.dispose();
		img.dispose();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		camera.translate(-deltaX, deltaY);
		
		camera.update();
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}
}
