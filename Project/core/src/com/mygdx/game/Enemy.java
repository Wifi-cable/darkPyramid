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

	private float speed = 50;
	private Texture spritessheet;
	private TextureRegion textureRegion;
	private TextureRegion[] texturRegWalkUp, texturRegWalkLeft, texturRegWalkRight, texturRegWalkDown;
	private Directions walkDirection = SOUTH;
	private Animation<Sprite> animation, aniWalkUp, aniWalkDown, aniWalkRight, aniWalkLeft;
	private float elapsedTime = 0;


	private float startX;
	private float startY;
	private float endX;
	private float endY;


	public Enemy(Level level, Texture spriteSheet, int startTileX, int startTileY, int endTileX, int endTileY) {
		TextureRegion[][] tmpFrames = TextureRegion.split(spriteSheet, spriteSheet.getWidth()/3, spriteSheet.getHeight()/4);
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

		
		int spriteWidth = spriteSheet.getWidth() / 3;
		int spriteHeight = spriteSheet.getHeight() / 4;

		aniWalkDown = new Animation(0.3f, texturRegWalkDown);
		aniWalkUp = new Animation(0.3f, texturRegWalkUp);
		aniWalkLeft = new Animation(0.3f, texturRegWalkLeft);
		aniWalkRight = new Animation(0.3f, texturRegWalkRight);
		animation = new Animation(0.3f, texturRegWalkRight);

		float tileSize = level.getTileSize();
		float firstX = level.getFirstTile().getOffsetX();
		float firstY = level.getFirstTile().getOffsetY() + (level.getTileAmountY() - 1f) * tileSize;

		//positioning and centering the sprite/ rectangle
		this.startX = firstX + startTileX * tileSize + (tileSize - spriteWidth) / 2;
		this.startY = firstY - startTileY * tileSize - (tileSize - spriteHeight) / 2;
		this.endX = firstX + endTileX * tileSize + (tileSize - spriteWidth) / 2;
		this.endY = firstY - endTileY * tileSize - (tileSize - spriteHeight) / 2;

		this.enemy = new Rectangle(startX, startY, tileSize, tileSize);
	}

	@Override
	public void draw(Batch spritebatch) {
		elapsedTime += Gdx.graphics.getDeltaTime();

		spritebatch.draw(animation.getKeyFrame(elapsedTime, true), enemy.getX(), enemy.getY());
	}

	public void update() {

		float delta = Gdx.graphics.getDeltaTime();
		if (startX < endX) {
			walkDirection = EAST;
			animation = aniWalkRight;
			if (enemy.getX() < endX) {
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
			if (enemy.getX() > endX) {
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
			if (enemy.getY() < endY) {
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
			if (enemy.getY() > endY) {
				velocity.y = -speed;
			} else {
				velocity.y = speed;
				float newStartY = endY;
				endY = startY;
				startY = newStartY;
			}
		}

//		

		enemy.setPosition(enemy.getX() + velocity.x * delta, enemy.getY() + velocity.y * delta);

	}

	public Rectangle getRectangle() {
		return this.enemy;
	}
}
