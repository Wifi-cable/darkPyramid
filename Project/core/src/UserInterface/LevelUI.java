package UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.GameState;
import com.mygdx.game.Level;
import com.mygdx.game.Player;

public class LevelUI implements UIinterface{
	int currentLevel;
	boolean completed;
	SimpleButton pauseButton = new SimpleButton();
	public Player player;
	private Level level;
	public OrthographicCamera camera;
	
	
	public LevelUI() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
	}
	
	@Override
	public GameState handleInput() {
		camera.update();
		player.update(Gdx.graphics.getDeltaTime());
		level.setView(camera);

		if(Gdx.input.isKeyJustPressed(Keys.P)|| pauseButton.isJustPressed())
			return GameState.PauseMenu;
		
		//cheating and testing:
		if(Gdx.input.isKeyJustPressed(Keys.W)||player.hasWon()) {
			completed = true;
			return GameState.LevelOver;
		}
		if(Gdx.input.isKeyJustPressed(Keys.L)||player.hasFailed()) {
			completed = false;
			return GameState.LevelOver;
		}
		return GameState.Level;
		
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.end();
		level.render();
		batch.begin();
		player.draw(batch);
		pauseButton.render(batch);
	}

	public void initialize(int selectedLevel) {
		currentLevel = selectedLevel;
		completed = false;
		level = new Level(selectedLevel);
		player= new Player(level);
		System.out.println("playing level "+selectedLevel);
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public boolean hasWon() {
		return completed;
	}

}
