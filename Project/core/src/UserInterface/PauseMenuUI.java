package UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameState;

public class PauseMenuUI implements UIinterface {
	private SimpleButton continueButton;
	private SimpleButton menuButton;
	
	public PauseMenuUI() {
		continueButton = new SimpleButton(100, 50, 300,75,com.mygdx.game.MyGdxGame.txt);
		menuButton = new SimpleButton(100, 150,300,75,com.mygdx.game.MyGdxGame.txt);
	}
	@Override
	public GameState handleInput() {
		if(continueButton.isJustPressed() || Gdx.input.isKeyJustPressed(Keys.C))
			return GameState.Level;
		if(menuButton.isJustPressed() || Gdx.input.isKeyJustPressed(Keys.M))
			return GameState.Menu;

		return GameState.PauseMenu;
	}

	@Override
	public void render(SpriteBatch batch) {
		com.mygdx.game.MyGdxGame.font.draw(batch, "Pause Menu, C to continue, M for menu", 100, 300);
		continueButton.render(batch);
		menuButton.render(batch);
	}

}
