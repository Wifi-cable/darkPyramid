package UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameState;

public class CreditsUI implements UIinterface {
	private SimpleButton backButton;
	Sprite bgSprite;
    Sprite creditsBox;
	public CreditsUI() {
		backButton = new SimpleButton(0.05f, 0.05f, 0.2f, 0.1f, TextureLoader.backButton);
		bgSprite = new Sprite(TextureLoader.background);
		bgSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        creditsBox = new Sprite(TextureLoader.credits);
        creditsBox.setSize(Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight() * 0.6f);
        creditsBox.setPosition(Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.3f);
		
	}
	@Override
	public GameState handleInput() {
		if (Gdx.input.isKeyJustPressed(Keys.BACKSPACE)||backButton.isJustPressed())
			return GameState.Menu;
		else
			return GameState.Credits;
	}

	@Override
	public void render(SpriteBatch batch) {
		bgSprite.draw(batch);
        creditsBox.draw(batch);
//		com.mygdx.game.MyGdxGame.font.draw(batch, "Credits here, backspace", 100, 300);
		backButton.render(batch);
	}

}
