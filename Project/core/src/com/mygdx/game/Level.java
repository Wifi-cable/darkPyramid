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
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
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

//	private float firstTileX;
//	private float firstTileY;
	private TiledMapTile firstTile;

	private boolean won = false;
	private boolean lost = false;
	private Rectangle exitRectangle;
	private Sprite exitSprite;


	public Level(int levelNumber) {
		switch (levelNumber) {
		case 1: {
			tiledMap = new TmxMapLoader().load("Maps/final-Level1.tmx");


		}
			break;
		case 2: {

			tiledMap = new TmxMapLoader().load("Maps/final-Level3.tmx");
		}

		break;
		case 3:{
			tiledMap = new TmxMapLoader().load("Maps/final-Level2.tmx");

		}
			break;
		case 4:{tiledMap = new TmxMapLoader().load("Maps/final-Level4.tmx");

		}
		break;
		case 5:{
			tiledMap = new TmxMapLoader().load("Maps/final-Level5.tmx");
		}

			break;
		default:
		}
		tiledmaprenderer = new OrthogonalTiledMapRenderer(tiledMap);
		collisionLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
		firstTile = collisionLayer.getCell(0, 0).getTile();

		// timeLimit may be different for each level ?
		timeLimit = 180;

		setWalls();

		thisLevel = this;
		thisLevel.setHealthOfPlayer(3);
		setEntities(levelNumber);
	}

	public void setEntities(int levelNumber) {
		float tileSize = thisLevel.getTileSize();

		float startX = thisLevel.getFirstTile().getOffsetX();
		float startY = thisLevel.getFirstTile().getOffsetY() + (thisLevel.getTileAmountY() -1f) * tileSize;
		exitRectangle = new Rectangle(startX+20*tileSize, startY-15*tileSize, tileSize, tileSize);
 		exitSprite = new Sprite(new Texture("Tilesets/ColorTiles/trapdoor2.png"));
 		exitSprite.setPosition(exitRectangle.getX(), exitRectangle.getY());
	 switch (levelNumber) {
	 	case 1:{

	 	//	exitItem = new Item(new Texture("trapdoor.png"), 20, 15 );
			player = new Player(thisLevel, new Texture("SpriteSheets/viola.png"), 1, 1);

			enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 11, 1, 11, 6));
			enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 5, 7, 12, 7));
			enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 11, 10, 6, 10));
			enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 4, 15, 11, 15));
			enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 17, 5, 17, 11));
			enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 19, 1, 19, 8));
			for (Enemy enemy : enemies) {
				enemyRectangles.add(enemy.getRectangle());
			}
	 	}
	 	break;

	 	case 2:{
			player = new Player(thisLevel, new Texture("SpriteSheets/viola.png"), 1, 1);

	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 19, 1, 3, 1));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 17, 4, 12, 4));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 3, 4, 10, 4));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 14, 6, 2, 6));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 4, 9, 12, 9));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 1, 11, 15, 11));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 11, 15, 19, 15));
			for (Enemy enemy : enemies) {
				enemyRectangles.add(enemy.getRectangle());
			}
	 	}
	 	break;

	 	case 3:{	// there are two levels named level2 same level different spelling
			player = new Player(thisLevel, new Texture("SpriteSheets/viola.png"), 1, 1);

	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 1, 12, 1, 2));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 1, 14, 1, 4));
	 		//enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 4, 9, 4, 13));	//this mummy makes it too hard
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 4, 1, 4, 7));
	 		//enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 7, 8, 7, 3));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 6, 8, 6, 15));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 9, 13, 9, 9));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 11, 9, 11, 15));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 14, 12, 14, 9));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 16, 7, 16, 5));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 8, 1, 17, 1));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 15, 15, 19, 15));

			for (Enemy enemy : enemies) {
				enemyRectangles.add(enemy.getRectangle());
			}
	 	}
	 	break;

	 	case 4:{// not sure why (1,2) puts hero on gameboard, but works
			player = new Player(thisLevel, new Texture("SpriteSheets/viola.png"), 1, 2);

	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 1, 2, 1, 9));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 4, 1, 4, 11));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 7, 12, 7, 6));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 12, 10, 15, 10));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 9, 2, 9, 7));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 1, 2, 1, 9));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 13, 1, 20, 1));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 14, 6, 20, 6));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 20, 4, 20, 13));

			for (Enemy enemy : enemies) {
				enemyRectangles.add(enemy.getRectangle());
			}
	 	}
	 	break;
	 	case 5:{// not sure why (1,3) puts hero on gameboard, but works
			player = new Player(thisLevel, new Texture("SpriteSheets/viola.png"), 1, 3);

	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 2, 1, 8, 1));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 10, 1, 10, 7));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 12, 5, 7, 5));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 10, 1, 10, 7));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 3, 6, 3, 13));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 3, 5, 3, 12));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 5, 12, 12, 12));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 5, 6, 5, 10));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 17, 13, 13, 13));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 8, 9, 12, 9));	//maybe too hard
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 20, 1, 12, 1));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 20, 7, 16, 7));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 14, 3, 14, 7));//maybe too hard
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 17, 15, 19, 15));
	 		enemies.add(new Enemy(thisLevel, new Texture("SpriteSheets/Mummy.png"), 14, 9, 20, 9));


			for (Enemy enemy : enemies) {
				enemyRectangles.add(enemy.getRectangle());
			}

	 	}
	 	break;


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
		exitSprite.draw(batch);
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


	public void setGameWon() {
		this.won = true;
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
		if (this.health == 0) {
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
	public Rectangle getExitRectangle() {
		return this.exitRectangle;
	}

	public TiledMapTile getFirstTile() {
		return firstTile;
	}

//	public int getTileAmountX() {
//		return collisionLayer.getWidth();
//	}

	public float getTileAmountY() {
		return collisionLayer.getHeight();
	}

	public float getTileSize() {
		return collisionLayer.getTileWidth();
	}

}
