package UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameState;

public class LevelSelectionUI implements UIinterface{
	private int selectedLevel;
	private SimpleButton backButton;
	private SimpleButton [] levelButtons;
	
	public LevelSelectionUI() {
		backButton = new SimpleButton();
		levelButtons = new SimpleButton[com.mygdx.game.MyGdxGame.numberofLevels];
		for(int i = 0; i<levelButtons.length;i++) {
			levelButtons[i] = new SimpleButton(200,100+ i*100, 300,75,com.mygdx.game.MyGdxGame.txt);
		}
	}
	@Override
	public GameState handleInput() {
		if(Gdx.input.isKeyJustPressed(Keys.BACKSPACE)||backButton.isJustPressed())
			return GameState.Menu;
		for(int i = 0; i< levelButtons.length;i++) {
			if(levelButtons[i].isJustPressed()) {
				if(i+1 <= com.mygdx.game.MyGdxGame.unlockedLevels) {
					selectedLevel = i+1;
				return GameState.Level;}
				else
					System.out.println("level is still locked!");
			}
		}
		return GameState.LevelSelection;
	}

	@Override
	public void render(SpriteBatch batch) {
		com.mygdx.game.MyGdxGame.font.draw(batch, "Select Level, or backspace", 100, 300);
		for(SimpleButton button : levelButtons) {
			button.render(batch);
		}
		backButton.render(batch);
	}

	public void initialize() {
		//mark unlocked levels
	}

	public int getSelectedLevel() {
		return selectedLevel;
	}

}
