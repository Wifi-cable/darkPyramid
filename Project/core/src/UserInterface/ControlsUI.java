package UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameState;

public class ControlsUI implements UIinterface {
	private SimpleButton backButton = new SimpleButton();

	@Override
	public GameState handleInput() {
		if (Gdx.input.isKeyJustPressed(Keys.BACKSPACE)||backButton.isJustPressed())
			return GameState.Menu;
		else
			return GameState.Controls;
	}

	@Override
	public void render(SpriteBatch batch) {
		com.mygdx.game.MyGdxGame.font.draw(batch, "Controls here, backspace", 100, 300);
		backButton.render(batch);
	}
}
