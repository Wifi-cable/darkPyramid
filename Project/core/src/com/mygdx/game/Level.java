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

import java.util.ArrayList;
import java.util.List;

public class Level {
	private TiledMap tiledMap;
	private TiledMapRenderer tiledmaprenderer;
	private TiledMapTileLayer collisionLayer;
	private ArrayList<Rectangle> walls;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Rectangle> enemyRectangles = new ArrayList<Rectangle>();

	private Player player;
	private float timeLimit;
	private int health;
	private Level thisLevel;

	private boolean won = false;
	private boolean lost = false;

	public Level(int levelNumber) {
		switch (levelNumber) {
		case 1: {
			tiledMap = new TmxMapLoader().load("Maps/cuteLevel3.tmx");
		}
			break;
		case 2: {
			tiledMap = new TmxMapLoader().load("prototype2.tmx");

		}
			break;
		default:
		}
		tiledmaprenderer = new OrthogonalTiledMapRenderer(tiledMap);
		collisionLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);

		// timeLimit may be different for each level ?
		timeLimit = 180;

		setWalls();

		thisLevel = this;
		thisLevel.setHealthOfPlayer(3);
		setEntities(levelNumber);
	}

	public void setEntities(int levelNumber) {
		player = new Player(thisLevel);
		if(levelNumber == 1) {
//
			enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 10, 0, 10, 3));
			for(Enemy enemy : enemies) {
			enemyRectangles.add(enemy.getRectangle());
			}
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

	public List<Rectangle> getEnemyRectangles() {
		return this.enemyRectangles;
	}

	public boolean playerHasWon() {
		return won;
	}

	public boolean playerHasFailed() {
		return lost;
	}

	// timelimit in seconds
	public float getTimeLimit() {
		return timeLimit;
	}

	// TODO should return current health of player/ number of hearts that should be
	// displayed
	public int getHealthOfPlayer() {
		return this.health;
	}

	public void setHealthOfPlayer(int newHealth) {
		this.health = newHealth;
		if(this.health == 0) {
			lost = true;
		}
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

	public float getTileSize() {
		return collisionLayer.getTileWidth();
	}

}
