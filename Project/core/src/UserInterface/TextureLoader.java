package UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
// loads all textures for UI
public class TextureLoader {
	// all backgrounds
	static Texture background;
	// all buttons
	static Texture menuButton, backButton, continueButton, controlsButton, creditsButton, level1Button,Level2Button,Level3Button, levelsButton, pauseButton;
	// levelUI 
	static Texture pixelHeart, darkLayer;
	// items

	public static void loadAllTextures() {
		background  = new Texture(Gdx.files.internal("UIelements/background.png"));
		menuButton = new Texture(Gdx.files.internal("UIelements/menuButton.png"));
		backButton = new Texture(Gdx.files.internal("UIelements/backButton.png"));
		continueButton = new Texture(Gdx.files.internal("UIelements/continueButton.png"));
		controlsButton = new Texture(Gdx.files.internal("UIelements/controlsButton.png"));
		creditsButton = new Texture(Gdx.files.internal("UIelements/creditsButton.png"));
		level1Button = new Texture(Gdx.files.internal("UIelements/level1Button.png"));
		Level2Button = new Texture(Gdx.files.internal("UIelements/level2Button.png"));
		Level3Button  = new Texture(Gdx.files.internal("UIelements/level3Button.png"));
		levelsButton = new Texture(Gdx.files.internal("UIelements/levelsButton.png"));
		pauseButton = new Texture(Gdx.files.internal("UIelements/pauseButton.png"));
		pixelHeart = new Texture(Gdx.files.internal("UIelements/pixelHeart3.png"));
		darkLayer = new Texture(Gdx.files.internal("UIelements/darklayer.png"));
	}

	public static void disposeAllTextures() {

		background.dispose();
		menuButton.dispose();
		backButton.dispose();
		continueButton.dispose();
		controlsButton.dispose(); 
		creditsButton.dispose(); 
		level1Button.dispose();
		Level2Button.dispose();
		Level3Button.dispose();
		levelsButton.dispose(); 
		pauseButton.dispose(); 
		pixelHeart.dispose();
		darkLayer.dispose();
	}

}
