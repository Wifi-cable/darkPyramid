package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
	private ArrayList<Enemy> enemies= new ArrayList<Enemy>();

	private Player player;
	private float timeLimit;

	private Level thisLevel;

	public Level(int levelNumber) {
		
		switch (levelNumber) {
		case 1: {
			tiledMap = new TmxMapLoader().load("cuteLevel3.tmx");
		}
			break;
		case 2: {
			tiledMap = new TmxMapLoader().load("prototype2.tmx");


		}
			break;
		default:
			;
		}


		tiledmaprenderer = new OrthogonalTiledMapRenderer(tiledMap);
		collisionLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
		
		// timeLimit may be different for each level ?
		timeLimit = 180;


		setWalls();

		thisLevel = this;
		setEntities(levelNumber);
	}


	public void setEntities(int levelNumber) {
		player = new Player(thisLevel);
		if(levelNumber == 1) {
			Pixmap enemy1 = new Pixmap(30, 30, Pixmap.Format.RGBA8888);
			enemy1.setColor(Color.BLUE);
			enemy1.fill();
			enemies.add(new Enemy(thisLevel, new Texture(enemy1), 10, 0, 10, 3));
		}
	}

	public void update() {
		player.update();
		if (enemies.size() != 0) {
			for (Enemy enemy : enemies) {
				enemy.update();
			}
		}
	}

	public void setView(OrthographicCamera camera) {
		tiledmaprenderer.setView(camera);
	}

	public void render(SpriteBatch batch) {
		batch.end();
		// because TiledMapRenderer does not use Spritebatch
		tiledmaprenderer.render();
		batch.begin();
		player.draw(batch);
//		if (enemies.size() != 0) {
			for (Enemy enemy : enemies) {
				enemy.draw(batch);
			}
//		}

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

	// TODO should return current health of player/ number of hearts that should be
	// displayed
	public int getHealthOfPlayer() {
		return 3;
	}

	// TODO getInventory() will be needed for what item(s) should be displayed in
	// inventory

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

	public Rectangle getPlayerRectangle() {
		return player.getRectangle();
	}

	public int getTileSize() {
		return (int)collisionLayer.getTileWidth();
	}
}
