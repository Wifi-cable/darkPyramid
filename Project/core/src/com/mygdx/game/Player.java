package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTile.BlendMode;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite{
	// @Asel
	// Movement Vector
	private Vector2 velocity = new Vector2();
	private float speed = 100;
	private TiledMapTileLayer collisionLayer;
	private ArrayList<Rectangle> CollisionObjects;

	// @Asel
	// get the sprite and the a Layer of the Map we determined as a
	// 'collisionLayer'
	public Player(Sprite sprite, TiledMapTileLayer collisionMap) {
		super(sprite);
		this.collisionLayer = collisionMap;
		
		CollisionObjects = new ArrayList<Rectangle>();
		for (int col = 0; col < collisionLayer.getWidth(); col++) {
			for (int row = 0; row < collisionLayer.getHeight(); row++) {
				if (collisionLayer.getCell(col, row).getTile().getProperties().containsKey("blocked")) {
					CollisionObjects.add(
							new Rectangle(col * collisionLayer.getTileWidth(), collisionLayer.getTileHeight() * (row),
									collisionLayer.getTileWidth(), collisionLayer.getTileHeight()));
				}
			}
		}
	}

	// @Asel
	// Delta time helps with a constant game speed on different frame rates
	// if we didnt handle it in anyway, the game speed would be
	// influenced by the frame rate
	@Override
	public void draw(Batch spritebatch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(spritebatch);
	}

	private boolean hasCollided() {
		Rectangle playerRectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
		for (Rectangle r : CollisionObjects) {
			if (playerRectangle.overlaps(r))
				return true;
		}
		return false;
	}

	public void update(float delta) {
		velocity.x = 0;
		velocity.y = 0;
		if (Gdx.input.isKeyPressed(Input.Keys.UP))
			velocity.y = speed;
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
			velocity.y = -speed;
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			velocity.x = speed;
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			velocity.x = -speed;

		float oldX = getX();
		float oldY = getY();

		setX(getX() + velocity.x * delta);
		if (hasCollided())
			setX(oldX);

		setY(getY() + velocity.y * delta);
		if (hasCollided())
			setY(oldY);
	}
}
