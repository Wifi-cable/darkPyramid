package UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameState;
import com.mygdx.game.Level;

public class LevelUI implements UIinterface {
	private int currentLevel;
	private boolean completed;
	private boolean darkMode;
	private Sprite darkLayer;
	private Healthbar healthbar;
	private float timeLimit;
	private SimpleButton pauseButton;
	private Level level;
	private OrthographicCamera camera;

	public LevelUI() {
		healthbar = new Healthbar();
		pauseButton = new SimpleButton(0.83f,0.93f , 0.15f, 0.05f,TextureLoader.pauseButton);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
		darkLayer = new Sprite(TextureLoader.darkLayer);
	}

	@Override
	public GameState handleInput() {
		timeLimit -= Gdx.graphics.getDeltaTime();
		camera.update();
		level.update();
		level.setView(camera);
		healthbar.update(level.getHealthOfPlayer());
		if (darkMode)
			setDarkLayer();

		if (Gdx.input.isKeyJustPressed(Keys.P) || pauseButton.isJustPressed())
			return GameState.PauseMenu;

		// cheating and testing:
		if (Gdx.input.isKeyJustPressed(Keys.W) || level.playerHasWon()) {
			completed = true;
			return GameState.LevelOver;
		}
		if (Gdx.input.isKeyJustPressed(Keys.L) || level.playerHasFailed() || timeOver()) {
			completed = false;
			return GameState.LevelOver;
		}
		return GameState.Level;

	}

	private void setDarkLayer() {
		Rectangle rect = level.getPlayerRectangle();
		Vector2 center = new Vector2();
		rect.getCenter(center);
		darkLayer.setCenter(center.x, center.y);
	}

	private boolean timeOver() {
		return timeLimit < 0;
	}

	@Override
	public void render(SpriteBatch batch) {
		level.render(batch);
		if (darkMode)
			darkLayer.draw(batch);
		int minutes = ((int) timeLimit) / 60;
		int seconds = ((int) timeLimit) % 60;
		pauseButton.render(batch);
		com.mygdx.game.MyGdxGame.font.draw(batch, "Level "+ currentLevel+ "   " + minutes + ":" + seconds, 10, 640);
		healthbar.render(batch);
	}

	public void initialize(int selectedLevel) {
		currentLevel = selectedLevel;
		completed = false;
		level = new Level(selectedLevel);
		timeLimit = level.getTimeLimit();
		darkMode = true;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public boolean hasWon() {
		return completed;
	}

	private class Healthbar {
		int count;
		float x = 200;
		float y = 610;
		Texture heart = new Texture(Gdx.files.internal("UIelements/pixelHeart.png"));
		Sprite heartSprite;

		public Healthbar() {
			heartSprite = new Sprite(TextureLoader.pixelHeart);
		}

		void render(SpriteBatch batch) {
			for (int i = 0; i < count; i++) {
				heartSprite.setPosition(x + i * heartSprite.getWidth() + i * 10, y);
				heartSprite.draw(batch);
			}
		}

		void update(int numberOfHearts) {
			count = numberOfHearts;
		}
	}
	

}
