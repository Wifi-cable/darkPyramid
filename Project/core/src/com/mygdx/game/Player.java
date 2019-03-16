package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import static com.mygdx.game.Player.Directions.*;

public class Player {


	private Texture spritessheet;
	private TextureRegion textureRegion;
	private TextureRegion[] texturRegWalkUp, texturRegWalkLeft, texturRegWalkRight, texturRegWalkDown;
	private Directions walkDirection = SOUTH;
	private Animation<Sprite> animation, aniWalkUp, aniWalkDown, aniWalkRight, aniWalkLeft;
	private Vector2 velocity = new Vector2();
	private float speed = 200;
	private Level currentLevel;
	private boolean hasFailed;
	private boolean hasWon;
	private float elapsedTime = 0;
	private int currentHealth;
	private long invincibleTime = 0;
	Rectangle playerRectangle;


	public Player(Level level, Texture spriteSheet, int startTileX, int startTileY) {
		currentLevel = level;
		spritessheet = spriteSheet;
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

		float tileSize = level.getTileSize();
		float firstX = level.getFirstTile().getOffsetX();
		float firstY = level.getFirstTile().getOffsetY() + (level.getTileAmountY() - 1f) * tileSize;

		//positioning and centering the sprite/ rectangle
		float startX = firstX + startTileX * tileSize + (tileSize - spriteWidth) / 2;
		float startY = firstY - startTileY * tileSize - (tileSize - spriteHeight) / 2;

		playerRectangle = new Rectangle(startX, startY, currentLevel.getTileSize() - 10,
				currentLevel.getTileSize() - 10);

		aniWalkDown = new Animation(0.3f, texturRegWalkDown);
		aniWalkUp = new Animation(0.3f, texturRegWalkUp);
		aniWalkLeft = new Animation(0.3f, texturRegWalkLeft);
		aniWalkRight = new Animation(0.3f, texturRegWalkRight);
		animation = new Animation(0.3f, texturRegWalkRight);

		currentHealth = currentLevel.getHealthOfPlayer();
	}

	public void initTextures() {

	}

	public void draw(Batch spritebatch) {
		elapsedTime += Gdx.graphics.getDeltaTime();

		spritebatch.draw(animation.getKeyFrame(elapsedTime, true), playerRectangle.getX(), playerRectangle.getY());

	}

	private boolean hasCollidedWith(List<Rectangle> objects) {
		for (Rectangle r : objects) {
			if (playerRectangle.overlaps(r))
				return true;
		}
		return false;
	}

	// @Asel
	// Delta time helps with a constant game speed on different frame rates
	// if we didnt handle it in any way, the game speed would be
	// influenced by the frame rate
	public void update() {

		float delta = Gdx.graphics.getDeltaTime();
		velocity.x = 0;
		velocity.y = 0;
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			velocity.x = speed;
			walkDirection = EAST;
			animation = aniWalkRight;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

			velocity.x = -speed;
			walkDirection = WEST;
			animation = aniWalkLeft;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			velocity.y = speed;
			walkDirection = NORTH;
			animation = aniWalkUp;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			velocity.y = -speed;
			walkDirection = SOUTH;
			animation = aniWalkDown;
		}

		if (!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
			switch (walkDirection) {
			case NORTH:
				animation = new Animation(0.3f, texturRegWalkUp[1]);
				break;
			case SOUTH:
				animation = new Animation(0.3f, texturRegWalkDown[1]);
				break;
			case WEST:
				animation = new Animation(0.3f, texturRegWalkLeft[1]);
				break;
			case EAST:
				animation = new Animation(0.3f, texturRegWalkRight[1]);
				break;
			}
		}
		float oldX = playerRectangle.getX();
		float oldY = playerRectangle.getY();

		// maybe setting X and Y at the same time? then we only have to check 2 times
		// not 4

		// setting X
		playerRectangle.setX(playerRectangle.getX() + velocity.x * delta);
		// checking X on Walls
		if (hasCollidedWith(currentLevel.getWalls())) {
			playerRectangle.setX(oldX);
		}

		// setting Y
		playerRectangle.setY(playerRectangle.getY() + velocity.y * delta);
		// checking Y on walls
		if (hasCollidedWith(currentLevel.getWalls())) {
			playerRectangle.setY(oldY);
		}

		
		// still invincible
		if ((System.currentTimeMillis() - invincibleTime) / 1000 > 3 || invincibleTime == 0) {
			// not invincible
			if (hasCollidedWith(currentLevel.getEnemyRectangles())) {
				playerRectangle.setX(oldX);
				playerRectangle.setX(oldY);
				currentLevel.setHealthOfPlayer(--currentHealth);
				invincibleTime = System.currentTimeMillis();
			}
		}
	}

	public boolean hasWon() {
		return hasWon;
	}

	public boolean hasFailed() {
		return hasFailed;
	}

	public enum Directions {
		NORTH, SOUTH, WEST, EAST,

	}

	public Rectangle getRectangle() {
		return playerRectangle;
	}
}
