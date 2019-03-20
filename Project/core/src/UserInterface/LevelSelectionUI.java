package UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameState;

public class LevelSelectionUI implements UIinterface {
	private Sprite bgSprite;
	private int selectedLevel;
	private SimpleButton backButton;
	private SimpleButton[] levelButtons;
	private Sprite locks[];

	public LevelSelectionUI() {
		bgSprite = new Sprite(TextureLoader.selectLevel);
		bgSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		backButton = new SimpleButton(0.05f, 0.05f, 0.2f, 0.1f, TextureLoader.backButton);
		levelButtons = new SimpleButton[com.mygdx.game.MyGdxGame.numberofLevels];
		for (int i = 0; i < levelButtons.length; i++) {
			levelButtons[i] = new SimpleButton(0.38f, 0.6f - i * 0.1f, 0.24f, 0.08f,
					TextureLoader.getButtonTexture(i + 1));
		}
		locks = new Sprite[com.mygdx.game.MyGdxGame.numberofLevels - 1];
		for (int i = 0; i < locks.length; i++) {
			locks[i] = new Sprite(TextureLoader.buttonLock);
			locks[i].setSize(0.06f * Gdx.graphics.getHeight(), 0.06f * Gdx.graphics.getHeight());
			locks[i].setPosition(0.31f * Gdx.graphics.getWidth(),
					0.21f * Gdx.graphics.getHeight() + i * 0.1f * Gdx.graphics.getHeight());
		}

	}

	@Override
	public GameState handleInput() {
		if (Gdx.input.isKeyJustPressed(Keys.BACKSPACE) || backButton.isJustPressed())
			return GameState.Menu;
		for (int i = 0; i < levelButtons.length; i++) {
			if (levelButtons[i].isJustPressed()) {
				if (i + 1 <= com.mygdx.game.MyGdxGame.unlockedLevels) {
					selectedLevel = i + 1;
					return GameState.Level;
				} else
					System.out.println("level is still locked!");
			}
		}
		return GameState.LevelSelection;
	}

	@Override
	public void render(SpriteBatch batch) {
		bgSprite.draw(batch);
		// com.mygdx.game.MyGdxGame.font.draw(batch, "Select Level, or backspace", 100, 300);
		for (SimpleButton button : levelButtons) {
			button.render(batch);
		}
		int lockedLevels = com.mygdx.game.MyGdxGame.numberofLevels - com.mygdx.game.MyGdxGame.unlockedLevels;
		for (int i = 0; i < lockedLevels; i++) {
			locks[i].draw(batch);
		}
		backButton.render(batch);
	}

	public void initialize() {
	}

	public int getSelectedLevel() {
		return selectedLevel;
	}

}
