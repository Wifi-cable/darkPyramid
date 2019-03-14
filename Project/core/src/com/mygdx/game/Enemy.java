package com.mygdx.game;

import static com.mygdx.game.Player.Directions.EAST;
import static com.mygdx.game.Player.Directions.NORTH;
import static com.mygdx.game.Player.Directions.SOUTH;
import static com.mygdx.game.Player.Directions.WEST;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.mygdx.game.Player.Directions;

public class Enemy extends Sprite {

	private Vector2 velocity = new Vector2();
	private Rectangle enemy;
//	private ArrayList<Rectangle> enemyRectangles = new ArrayList<Rectangle>();
	
	private float speed = 50;
	private Sprite sprite;
	private Texture spritessheet;
	private TextureRegion textureRegion;
	private TextureRegion[] texturRegWalkUp, texturRegWalkLeft, texturRegWalkRight, texturRegWalkDown;
	private Directions walkDirection = SOUTH;
	private Animation<Sprite> animation, aniWalkUp, aniWalkDown, aniWalkRight, aniWalkLeft;
	private float elapsedTime = 0;

	// so many numbers and first position not clear because of header
	private int firstX = 0;
	private int firstY = Gdx.graphics.getHeight() - 80;
	private float startX;
	private float startY;
	private float endX;
	private float endY;


//	private boolean xDifference;
//	private boolean yDifference;

	public Enemy(Level level, Texture spriteSheet, int startTileX, int startTileY, int endTileX, int endTileY) {
		textureRegion = new TextureRegion(spriteSheet, 0, 0, 144, 192);
		TextureRegion[][] tmpFrames = TextureRegion.split(spriteSheet, 48, 48);
		texturRegWalkDown = new TextureRegion[4];
		texturRegWalkLeft = new TextureRegion[4];
		texturRegWalkRight = new TextureRegion[4];
		texturRegWalkUp = new TextureRegion[4];
		int index = 0;

		for (int j = 0; j < 3; j++) {
			texturRegWalkDown[index] = tmpFrames[0][j];
			texturRegWalkLeft[index] = tmpFrames[1][j];
			texturRegWalkRight[index] = tmpFrames[2][j];
			texturRegWalkUp[index] = tmpFrames[3][j];
			index++;
		}
		texturRegWalkDown[index] = tmpFrames[0][1];
		texturRegWalkLeft[index] = tmpFrames[1][1];
		texturRegWalkRight[index] = tmpFrames[2][1];
		texturRegWalkUp[index] = tmpFrames[3][1];

		sprite = new Sprite(texturRegWalkDown[1], 48, 48, 48, 48);

		aniWalkDown = new Animation(0.3f, texturRegWalkDown);
		aniWalkUp = new Animation(0.3f, texturRegWalkUp);
		aniWalkLeft = new Animation(0.3f, texturRegWalkLeft);
		aniWalkRight = new Animation(0.3f, texturRegWalkRight);
		animation = new Animation(0.3f, texturRegWalkRight);

		float tileSize = level.getTileSize();

		this.startX = firstX + startTileX * tileSize + (tileSize - sprite.getWidth()) / 2;
		this.startY = firstY - startTileY * tileSize - (tileSize - sprite.getHeight()) / 2;
		this.endX = firstX + endTileX * tileSize + (tileSize - sprite.getWidth()) / 2;
		this.endY = firstY - endTileY * tileSize - (tileSize - sprite.getHeight()) / 2;

		sprite.setPosition(startX, startY);
		this.enemy = new Rectangle(sprite.getX(), sprite.getY(), tileSize, tileSize);
//		enemyRectangles.add(this.enemy);
	}

	@Override
	public void draw(Batch spritebatch) {
		elapsedTime += Gdx.graphics.getDeltaTime();

		spritebatch.draw(animation.getKeyFrame(elapsedTime, true), sprite.getX(), sprite.getY());
	}

	public void update() {

		float delta = Gdx.graphics.getDeltaTime();
//		System.out.println(sprite.getY());
		if (startX < endX) {
			walkDirection = EAST;
			animation = aniWalkRight;
			if (sprite.getX() < endX) {
				velocity.x = speed;
			} else {
				velocity.x = -speed;
				float newStartX = endX;
				endX = startX;
				startX = newStartX;

			}

		}
		if (startX > endX) {
			walkDirection = WEST;
			animation = aniWalkLeft;
			if (sprite.getX() > endX) {
				velocity.x = -speed;
			} else {
				velocity.x = speed;
				float newStartX = endX;
				endX = startX;
				startX = newStartX;

			}
		}

		if (startY < endY) {
			walkDirection = NORTH;
			animation = aniWalkUp;
			if (sprite.getY() < endY) {
				velocity.y = speed;
			} else {
				velocity.y = -speed;
				float newStartY = endY;
				endY = startY;
				startY = newStartY;
			}
		}
		if (startY > endY) {
			walkDirection = SOUTH;
			animation = aniWalkDown;
			if (sprite.getY() > endY) {
				velocity.y = -speed;
			} else {
				velocity.y = speed;
				float newStartY = endY;
				endY = startY;
				startY = newStartY;
			}
		}

		sprite.setX(sprite.getX() + velocity.x * delta);
		sprite.setY(sprite.getY() + velocity.y * delta);

		enemy.setPosition(sprite.getX(), sprite.getY());

	}

	public Rectangle getRectangle() {
		return this.enemy;
	}
}
