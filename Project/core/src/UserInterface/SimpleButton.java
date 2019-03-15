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
		this(0.1f, 0.1f, 0.2f, 0.2f, com.mygdx.game.MyGdxGame.txt);
	}
	
	//x and y coordinates have origin at bottom left corner of window, relative coordinates: 1 equals 100% 
	public SimpleButton(float x, float y, float width, float height, Texture texture) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = new Sprite(texture);
		sprite.setSize(this.width*Gdx.graphics.getWidth(), this.height*Gdx.graphics.getHeight());
		sprite.setPosition(this.x*Gdx.graphics.getWidth(), this.y*Gdx.graphics.getHeight());
	}

	public boolean isJustPressed() {
		if (Gdx.input.justTouched() && isHovered()) {
			return true;
		}
		return false;
	}

	private boolean isHovered() {
		float xClick = (float) Gdx.input.getX()/Gdx.graphics.getWidth();
		float yClick = (float) (Gdx.graphics.getHeight() - Gdx.input.getY())/Gdx.graphics.getHeight();
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
