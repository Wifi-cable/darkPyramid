package UserInterface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameState;

public class LevelOverUI implements UIinterface {
	private Sprite levelCompletedSprite;
	private Sprite levelFailedSprite;
	private Sprite gameOverSprite;
	private Sprite background;
	private boolean lastLevelWon;
	private int nextLevel;
	public LevelOverUI() {
		levelCompletedSprite = new Sprite(TextureLoader.levelCompleted);
		levelCompletedSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		levelFailedSprite = new Sprite(TextureLoader.levelFailed);
		levelFailedSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		gameOverSprite = new Sprite(TextureLoader.gameOver);
		gameOverSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

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
		background.draw(batch);
		//com.mygdx.game.MyGdxGame.font.draw(batch, "level over, space to continue", 100, 300);
	}

	public int getNextLevel() {
		return nextLevel;
	}

	public void initialize(int playedLevel, boolean won) {
		this.lastLevelWon = false;
		this.nextLevel = playedLevel;

		if (won) {
			System.out.println("level completed" + playedLevel);
			if (playedLevel == com.mygdx.game.MyGdxGame.numberofLevels) {
				this.lastLevelWon = true;
				System.out.println("won last level, continue to menu");
				background = gameOverSprite;
			} else {
				if (playedLevel == com.mygdx.game.MyGdxGame.unlockedLevels) {
					System.out.println("level unlocked");
					com.mygdx.game.MyGdxGame.unlockedLevels ++;
					writeUnlockedLevelToFile(com.mygdx.game.MyGdxGame.unlockedLevels);
				}
				this.nextLevel = playedLevel + 1;
				System.out.println("continue with nextlevel");
				background = levelCompletedSprite;
			}
		} else {
			System.out.println("level failed, retry level");
			background = levelFailedSprite;
		}
	}
	public static void writeUnlockedLevelToFile(int unlockedLevel){	
		String userDirectory = System.getProperty("user.home");
		Path pathToSaveGameFile = Paths.get(userDirectory, "documents", "DarkPyramid", "SaveState", "DarkPyramidSaveGameFile.txt");
		String outputToFile = "" + unlockedLevel;
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(new FileWriter(pathToSaveGameFile.toString()));
			writer.write(outputToFile);
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
