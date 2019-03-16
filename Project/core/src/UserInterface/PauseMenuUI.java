package UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameState;

public class PauseMenuUI implements UIinterface {
	private SimpleButton continueButton;
	private SimpleButton menuButton;
	private Sprite bgSprite;
	
	public PauseMenuUI() {
		bgSprite = new Sprite(TextureLoader.background);
		bgSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		continueButton = new SimpleButton(0.3f, 0.6f, 0.4f, 0.1f, TextureLoader.continueButton);
		menuButton = new SimpleButton(0.3f, 0.45f, 0.4f, 0.1f, TextureLoader.menuButton);
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
		bgSprite.draw(batch);
		//com.mygdx.game.MyGdxGame.font.draw(batch, "Pause Menu, C to continue, M for menu", 100, 300);
		continueButton.render(batch);
		menuButton.render(batch);
	}

}
