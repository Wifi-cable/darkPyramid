package UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

// loads all textures for UI
public class TextureLoader {
	// all backgrounds
	static Texture background, startscreen, menu, selectLevel, levelFailed, levelCompleted, gameOver;
	// all buttons
	static Texture menuButton, backButton, continueButton, controlsButton, creditsButton, level1Button, level2Button,
			level3Button, level4Button, level5Button, levelsButton, pauseButton, buttonLock;
	// levelUI
	static Texture pixelHeart, darkLayer;
	// items

	public static void loadAllTextures() {
		background = new Texture(Gdx.files.internal("UIelements/background.png"));
		startscreen = new Texture(Gdx.files.internal("UIelements/startscreen.png"));
		menu = new Texture(Gdx.files.internal("UIelements/menu.png"));
		selectLevel = new Texture(Gdx.files.internal("UIelements/selectLevel.png"));
		levelFailed  = new Texture(Gdx.files.internal("UIelements/levelFailed.png"));
		levelCompleted  = new Texture(Gdx.files.internal("UIelements/levelCompleted.png"));
		gameOver   = new Texture(Gdx.files.internal("UIelements/gameOver.png"));
		
		menuButton = new Texture(Gdx.files.internal("UIelements/menuButton.png"));
		backButton = new Texture(Gdx.files.internal("UIelements/backButton.png"));
		continueButton = new Texture(Gdx.files.internal("UIelements/continueButton.png"));
		controlsButton = new Texture(Gdx.files.internal("UIelements/controlsButton.png"));
		creditsButton = new Texture(Gdx.files.internal("UIelements/creditsButton.png"));
		level1Button = new Texture(Gdx.files.internal("UIelements/level1Button.png"));
		level2Button = new Texture(Gdx.files.internal("UIelements/level2Button.png"));
		level3Button = new Texture(Gdx.files.internal("UIelements/level3Button.png"));
		level4Button = new Texture(Gdx.files.internal("UIelements/level4Button.png"));
		level5Button = new Texture(Gdx.files.internal("UIelements/level5Button.png"));
		levelsButton = new Texture(Gdx.files.internal("UIelements/levelsButton.png"));
		pauseButton = new Texture(Gdx.files.internal("UIelements/pauseButton.png"));
		
		buttonLock = new Texture(Gdx.files.internal("UIelements/lock.png"));
		pixelHeart = new Texture(Gdx.files.internal("UIelements/pixelHeart3.png"));
		darkLayer = new Texture(Gdx.files.internal("UIelements/darkLayer.png"));
	}

	public static void disposeAllTextures() {

		background.dispose();
		startscreen.dispose();
		menu.dispose();
		selectLevel.dispose();
		levelFailed.dispose();
		levelCompleted.dispose();
		gameOver.dispose();
		
		menuButton.dispose();
		backButton.dispose();
		continueButton.dispose();
		controlsButton.dispose();
		creditsButton.dispose();
		level1Button.dispose();
		level2Button.dispose();
		level3Button.dispose();
		level4Button.dispose();
		level5Button.dispose();
		levelsButton.dispose();
		pauseButton.dispose();
		buttonLock.dispose();
		pixelHeart.dispose();
		darkLayer.dispose();
	}

	public static Texture getButtonTexture(int number) {
		switch (number) {
		case 1:
			return level1Button;
		case 2:
			return level2Button;
		case 3:
			return level3Button;
		case 4:
			return level4Button;
		case 5:
			return level5Button;
		default:
			return level1Button;
		}
	}

}
