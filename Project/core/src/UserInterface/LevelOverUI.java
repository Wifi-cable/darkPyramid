package UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameState;

public class LevelOverUI implements UIinterface {
	private int playedLevel;
	private boolean hasWon;
	private boolean lastLevelWon;
	private int nextLevel;

	@Override
	public GameState handleInput() {
		// menu if last level, or level if next level
		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			if(lastLevelWon)
				return GameState.Menu;
			else 
				return GameState.Level;
		}
		return GameState.LevelOver;
	}

	@Override
	public void render(SpriteBatch batch) {
		com.mygdx.game.MyGdxGame.font.draw(batch, "level over, space to continue", 100, 300);
	}

	public int getNextLevel() {
		return nextLevel;
	}

	public void initialize(int playedLevel, boolean won) {
		this.playedLevel = playedLevel;
		this.hasWon = won;
		this.lastLevelWon = false;
		this.nextLevel = playedLevel;

		if (won) {
			System.out.println("level completed" + playedLevel);
			if (playedLevel == com.mygdx.game.MyGdxGame.numberofLevels) {
				this.lastLevelWon = true;
				System.out.println("won last level, continue to menu");
			} else {
				if (playedLevel == com.mygdx.game.MyGdxGame.unlockedLevels) {
					System.out.println("level unlocked");
					com.mygdx.game.MyGdxGame.unlockedLevels ++;
				}
				this.nextLevel = playedLevel + 1;
				System.out.println("continue with nextlevel");
			}
		} else {
			System.out.println("level failed, retry level");
		}
	}

}
