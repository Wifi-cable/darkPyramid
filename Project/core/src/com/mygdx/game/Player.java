package com.mygdx.game;

import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player{
	
	private Sprite sprite;
	private Vector2 velocity = new Vector2();
	private float speed = 100;
	private Level currentLevel;

	public Player() {
		Pixmap pix = new Pixmap(20, 20, Pixmap.Format.RGBA8888);
		pix.setColor(Color.GREEN);
		pix.fill();
		sprite = new Sprite(new Texture(pix));
		pix.dispose();
		currentLevel = Level.thisLevel;
	}


	public void draw(Batch spritebatch) {
		sprite.draw(spritebatch);
	}
	
	private boolean hasCollidedWith(List<Rectangle> objects) {
		Rectangle playerRectangle = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
		for (Rectangle r : objects) {
			if (playerRectangle.overlaps(r))
				return true;
		}
		return false;
	}
	
	// @Asel
	// Delta time helps with a constant game speed on different frame rates
	// if we didnt handle it in anyway, the game speed would be
	// influenced by the frame rate
	public void update() {
		float delta = Gdx.graphics.getDeltaTime();
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

		float oldX = sprite.getX();
		float oldY = sprite.getY();

		sprite.setX(sprite.getX() + velocity.x * delta);
		if (hasCollidedWith(currentLevel.getWalls()))
			sprite.setX(oldX);
		sprite.setY(sprite.getY() + velocity.y * delta);
		if (hasCollidedWith(currentLevel.getWalls()))
			sprite.setY(oldY);
	}


	public Rectangle getRectangle() {
		return sprite.getBoundingRectangle();
	}
}
