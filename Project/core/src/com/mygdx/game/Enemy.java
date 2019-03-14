package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Sprite {

	private Vector2 velocity = new Vector2();
	private Rectangle enemy;
	private float speed = 50;
	private Sprite sprite;

	// so many numbers and first position not clear because of header
	private int firstX = 0;
	private int firstY = Gdx.graphics.getHeight() - 80;
	private int startX;
	private int startY;
	private int endX;
	private int endY;

//	private boolean xDifference;
//	private boolean yDifference;

	public Enemy(Texture texture, int startTileX, int startTileY, int endTileX, int endTileY, int tileWith) {
		this.sprite = new Sprite(texture);
		this.startX = firstX + startTileX * tileWith + (tileWith - texture.getWidth())/2;
		this.startY = firstY - startTileY * tileWith - (tileWith - texture.getHeight())/2;
		this.endX = firstX + endTileX * tileWith + (tileWith - texture.getWidth())/2;
		this.endY = firstY - endTileY * tileWith - (tileWith - texture.getHeight())/2;



//		if (startX == endX) {
//			sameX = true;
//		} else {
//			sameX = false;
//		}
//		if (startY == endY) {
//			sameY = true;
//		} else {
//			sameY = false;
//		}

		System.out.println();
		System.out.println(startX);
		System.out.println(startY);
		System.out.println(endX);
		System.out.println(endY);

		sprite.setPosition(startX, startY);
		this.enemy = new Rectangle(sprite.getX(), sprite.getY(), tileWith, tileWith);

//		System.out.println(sprite.getX());
//		System.out.println(sprite.getY());

	}

	@Override
	public void draw(Batch spritebatch) {
		sprite.draw(spritebatch);
	}

	public void update() {

		float delta = Gdx.graphics.getDeltaTime();
//		System.out.println(sprite.getY());
		if (startX < endX) {
			if (sprite.getX() < endX) {
				velocity.x = speed;
			} else {
				velocity.x = -speed;
				int newStartX = endX;
				endX = startX;
				startX = newStartX;

			}

		}
		if (startX > endX) {
			if (sprite.getX() > endX) {
				velocity.x = -speed;
			} else {
				velocity.x = speed;
				int newStartX = endX;
				endX = startX;
				startX = newStartX;

			}
		}

		if (startY < endY) {
			if (sprite.getY() < endY) {
				velocity.y = speed;

			} else {
				velocity.y = -speed;
				int newStartY = endY;
				endY = startY;
				startY = newStartY;
			}
		}
		if (startY > endY) {
			if (sprite.getY() > endY) {
				velocity.y = -speed;

			} else {
				velocity.y = speed;
				int newStartY = endY;
				endY = startY;
				startY = newStartY;
			}
		}
		sprite.setX(sprite.getX() + velocity.x * delta);
		sprite.setY(sprite.getY() + velocity.y * delta);

		enemy.setPosition(sprite.getX(), sprite.getY());

//		if (sprite.getX() == endX - 1) {
//			velocity.x = -speed;
//			int newStartX = endX;
//			endX = startX;
//			startX = newStartX;
//		}
//		if (sprite.getY() == endY - 1) {
//			velocity.y = -speed;
//			int newStartY = endY;
//			endY = startY;
//			startY = newStartY;
//		}
	}

	public Rectangle getRectangle() {
		return this.enemy;
	}
}
