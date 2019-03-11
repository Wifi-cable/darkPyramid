package UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimpleButton {
	private float x;
	private float y;
	private float width;
	private float height;
	private Sprite sprite;
	
	//for testing purpose
	public SimpleButton() {
		this(10, 10, 50, 50, com.mygdx.game.MyGdxGame.txt);
	}
	
	//x and y coordinates have origin at top left corner of window
	public SimpleButton(float x, float y, float width, float height, Texture texture) {
		this.x = x;
		this.y = Gdx.graphics.getHeight() - y - height; // because sprite coordinates have origin at bottom left
		this.width = width;
		this.height = height;
		this.sprite = new Sprite(texture);
		sprite.setSize(this.width, this.height);
		sprite.setPosition(this.x, this.y);
	}

	public boolean isJustPressed() {
		if (Gdx.input.justTouched() && isHovered()) {
			return true;
		}
		return false;
	}

	private boolean isHovered() {
		float xClick = Gdx.input.getX();
		float yClick = Gdx.graphics.getHeight() - Gdx.input.getY();
		if (xClick >= x && xClick <= x + width && yClick >= y && yClick <= y + height)
			return true;
		else
			return false;
	}

	public void render(SpriteBatch batch) {
		if (isHovered())
			sprite.draw(batch, 100);
		else
			sprite.draw(batch);
	}
}
