package UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.GameState;

public class StartScreenUI implements UIinterface{
	Sprite bgSprite;
	
	public StartScreenUI() {
		bgSprite = new Sprite(TextureLoader.background);
		bgSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
	}
	@Override
	public GameState handleInput() {
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) || Gdx.input.justTouched())
			return GameState.Menu;
		else
			return GameState.StartScreen;
	}

	@Override
	public void render(SpriteBatch batch) {
		bgSprite.draw(batch);
		com.mygdx.game.MyGdxGame.font.draw(batch, "Startscreen, press space ", 100, 300);
	}

}
