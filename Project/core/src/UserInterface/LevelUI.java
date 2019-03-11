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

import com.mygdx.game.Player;



public class LevelUI implements UIinterface{
	int currentLevel;
	boolean completed;
	SimpleButton pauseButton = new SimpleButton();
	
	
	
	public Player player;
	public TiledMap tiledMap;
	public TiledMapRenderer tiledmaprenderer;
	public OrthographicCamera camera;
	
	public LevelUI() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
		tiledMap = new TmxMapLoader().load("prototype2.tmx");
		tiledmaprenderer = new OrthogonalTiledMapRenderer(tiledMap);

		Pixmap pix = new Pixmap(20, 20, Pixmap.Format.RGBA8888);
		pix.setColor(Color.GREEN);
		pix.fill();
		player = new Player(new Sprite(new Texture(pix)), (TiledMapTileLayer) tiledMap.getLayers().get(0));
		pix.dispose();
	}
	
	@Override
	public GameState handleInput() {

		if(Gdx.input.isKeyJustPressed(Keys.P)|| pauseButton.isJustPressed())
			return GameState.PauseMenu;
		
		//cheating and testing:
		if(Gdx.input.isKeyJustPressed(Keys.W)) {
			completed = true;
			return GameState.LevelOver;
		}
		if(Gdx.input.isKeyJustPressed(Keys.L)) {
			completed = false;
			return GameState.LevelOver;
		}
		return GameState.Level;
		
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.end();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		camera.update();
		tiledmaprenderer.setView(camera);
		tiledmaprenderer.render();

		batch.begin();
		player.draw(batch);
		com.mygdx.game.MyGdxGame.font.draw(batch, "playing level, P to pause, W to win, L to lose", 100, 300);	
		pauseButton.render(batch);
	}

	public void initialize(int selectedLevel) {
		currentLevel = selectedLevel;
		completed = false;
		System.out.println("playing level "+selectedLevel);
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public boolean hasWon() {
		return completed;
	}

}
