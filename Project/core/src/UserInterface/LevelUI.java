package UserInterface;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameState;
import com.mygdx.game.Level;

public class LevelUI implements UIinterface{
	private int currentLevel;
	private boolean completed;
	
	private Healthbar healthbar;
	private float timeLimit;
	private SimpleButton pauseButton;
	private Level level;
	private OrthographicCamera camera;
	
	
	public LevelUI() {
		healthbar = new Healthbar();
		pauseButton = new SimpleButton(700, 10, 90, 40, com.mygdx.game.MyGdxGame.txt);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
	}
	
	@Override
	public GameState handleInput() {
	    timeLimit -= Gdx.graphics.getDeltaTime();
		camera.update();
		level.update();
		level.setView(camera);
		healthbar.update(level.getHealthOfPlayer());

		if(Gdx.input.isKeyJustPressed(Keys.P)|| pauseButton.isJustPressed())
			return GameState.PauseMenu;
		
		//cheating and testing:
		if(Gdx.input.isKeyJustPressed(Keys.W)||level.playerHasWon()) {
			completed = true;
			return GameState.LevelOver;
		}
		if(Gdx.input.isKeyJustPressed(Keys.L)|| level.playerHasFailed()||timeOver()) {
			completed = false;
			return GameState.LevelOver;
		}
		return GameState.Level;
		
		
	}

	private boolean timeOver() {
		return timeLimit < 0;
	}

	@Override
	public void render(SpriteBatch batch) {
		level.render(batch);
	    int minutes = ((int)timeLimit) / 60;
	    int seconds = ((int)timeLimit) % 60;
		pauseButton.render(batch);
		com.mygdx.game.MyGdxGame.font.draw(batch, ""+minutes+":"+seconds, 10, 640);
		healthbar.render(batch);
	}

	public void initialize(int selectedLevel) {
		currentLevel = selectedLevel;
		completed = false;
		level = new Level(selectedLevel);
		timeLimit = level.getTimeLimit();
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public boolean hasWon() {
		return completed;
	}
	
	private class Healthbar{
		int count;
		float x = 100;
		float y = 610;
		Texture heart = new Texture(Gdx.files.internal("pixelHeart.png"));	
		Sprite heartSprite;
		public Healthbar() {
			heartSprite = new Sprite(heart);
		}
		void render(SpriteBatch batch) {
			for(int i = 0; i < count;i++) {
				heartSprite.setPosition(x+i*heartSprite.getWidth() +i*10, y);
				heartSprite.draw(batch);
			}
		}
		void update(int numberOfHearts) {
			count = numberOfHearts;
		}
	}

}
