package UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameState;

public class CreditsUI implements UIinterface {
	private SimpleButton backButton = new SimpleButton();
	
	@Override
	public GameState handleInput() {
		if (Gdx.input.isKeyJustPressed(Keys.BACKSPACE)||backButton.isJustPressed())
			return GameState.Menu;
		else
			return GameState.Credits;
	}

	@Override
	public void render(SpriteBatch batch) {
		com.mygdx.game.MyGdxGame.font.draw(batch, "Credits here, backspace", 100, 300);
		backButton.render(batch);
	}

}
