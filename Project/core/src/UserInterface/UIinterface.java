package UserInterface;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameState;

public interface UIinterface {
	GameState handleInput();
	void render(SpriteBatch batch);
}
