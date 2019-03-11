package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Level {
	private TiledMap tiledMap;
	private TiledMapRenderer tiledmaprenderer;
	private TiledMapTileLayer collisionLayer;
	private ArrayList<Rectangle> walls;
	private Player player;
	private float timeLimit;

	public Level(int levelNumber) {
		switch (levelNumber) {
		case 1: {
			tiledMap = new TmxMapLoader().load("prototype.tmx");
		}
			break;
		case 2: {
			tiledMap = new TmxMapLoader().load("prototype2.tmx");
		}
			break;
		default:
			;
		}
		
		// timeLimit may be different for each level ?
		timeLimit = 180;
			
		tiledmaprenderer = new OrthogonalTiledMapRenderer(tiledMap);
		collisionLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
		setWalls();
		player = new Player(this);
	}
	public void update() {
		player.update();
	}

	public void setView(OrthographicCamera camera) {
		tiledmaprenderer.setView(camera);
	}

	public void render(SpriteBatch batch) {
		batch.end();
		//because TiledMapRenderer does not use Spritebatch
		tiledmaprenderer.render();
		batch.begin();
		player.draw(batch);
	}	
	public List<Rectangle> getWalls() {
		return walls;
	}
	
	
	public boolean playerHasWon() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean playerHasFailed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	// timelimit in seconds
	public float getTimeLimit() {
		return timeLimit;
	}

	// TODO should return current health of player/ number of hearts that should be displayed
	public int getHealthOfPlayer() {
		return 3;
	}
	
	// TODO getInventory() will be needed for what item(s) should be displayed in inventory
	
	// using TileMap Layer to calculate all wall rectangles
	private void setWalls() {
		walls = new ArrayList<Rectangle>();
		for (int col = 0; col < collisionLayer.getWidth(); col++) {
			for (int row = 0; row < collisionLayer.getHeight(); row++) {
				if (collisionLayer.getCell(col, row).getTile().getProperties().containsKey("blocked")) {
					walls.add(new Rectangle(col * collisionLayer.getTileWidth(), collisionLayer.getTileHeight() * (row),
							collisionLayer.getTileWidth(), collisionLayer.getTileHeight()));
				}
			}
		}
	}
}
