package UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameState;

public class MenuUI implements UIinterface {
	SimpleButton controlsButton;
	SimpleButton levelSelectionButton;
	SimpleButton creditsButton;

	public MenuUI() {
		levelSelectionButton = new SimpleButton(200, 50, 200, 75, com.mygdx.game.MyGdxGame.txt);
		controlsButton = new SimpleButton(200, 150, 200, 75, com.mygdx.game.MyGdxGame.txt);
		creditsButton = new SimpleButton(200, 250, 200, 75, com.mygdx.game.MyGdxGame.txt);

	}

	@Override
	public GameState handleInput() {
		if (levelSelectionButton.isJustPressed() || Gdx.input.isKeyJustPressed(Keys.L))
			return GameState.LevelSelection;
		if (controlsButton.isJustPressed() || Gdx.input.isKeyJustPressed(Keys.C))
			return GameState.Controls;
		if (creditsButton.isJustPressed() || Gdx.input.isKeyJustPressed(Keys.R))
			return GameState.Credits;
		else
			return GameState.Menu;
	}

	@Override
	public void render(SpriteBatch batch) {
		com.mygdx.game.MyGdxGame.font.draw(batch, "Menu, L for levels, C for controls, R for credits", 100, 100);
		controlsButton.render(batch);
		levelSelectionButton.render(batch);
		creditsButton.render(batch);

	}

}
