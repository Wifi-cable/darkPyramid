package UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameState;

public class MenuUI implements UIinterface {
	Sprite bgSprite;
	SimpleButton controlsButton;
	SimpleButton levelSelectionButton;
	SimpleButton creditsButton;

	public MenuUI() {
		bgSprite = new Sprite(TextureLoader.background);
		bgSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		levelSelectionButton = new SimpleButton(0.3f, 0.6f, 0.4f, 0.1f, TextureLoader.levelsButton);
		controlsButton = new SimpleButton(0.3f, 0.45f, 0.4f, 0.1f, TextureLoader.controlsButton);
		creditsButton = new SimpleButton(0.3f, 0.3f, 0.4f, 0.1f, TextureLoader.creditsButton);
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
		bgSprite.draw(batch);
		com.mygdx.game.MyGdxGame.font.draw(batch, "Menu, L for levels, C for controls, R for credits", 100, 100);
		controlsButton.render(batch);
		levelSelectionButton.render(batch);
		creditsButton.render(batch);

	}

}

