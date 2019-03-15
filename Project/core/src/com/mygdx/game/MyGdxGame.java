package com.mygdx.game;

import UserInterface.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	
	// for testing purpose	
	public static BitmapFont font;
	public static Texture txt;
	public static final int numberofLevels = 2;
	public static int unlockedLevels = 1;
	
	SpriteBatch batch;
	GameState currentGameState = GameState.StartScreen;
	GameState nextGameState = GameState.StartScreen;

	StartScreenUI startScreen;
	MenuUI menu;
	LevelSelectionUI levelSelection;
	ControlsUI controls;
	CreditsUI credits;
	LevelUI level;
	PauseMenuUI pauseMenu;
	LevelOverUI levelOver;

	@Override
	public void create() {
		TextureLoader.loadAllTextures();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(2);
		txt = new Texture(Gdx.files.internal("UIelements/logo.png"));
		Music mp3Music = Gdx.audio.newMusic(Gdx.files.internal("Music/bgMusic.mp3"));
		mp3Music.play();
		
		batch = new SpriteBatch();
		
		startScreen 	= new StartScreenUI();
		menu 			= new MenuUI();
		levelSelection 	= new LevelSelectionUI();
		controls 		= new ControlsUI();
		credits 		= new CreditsUI();
		level 			= new LevelUI();
		pauseMenu 		= new PauseMenuUI();
		levelOver 		= new LevelOverUI();

	}
	
	@Override
	public void render() {
		currentGameState = nextGameState;
		updateNextGameState();
		clear();
		batch.begin();
		renderCurrentGameState();
		batch.end();
		if(nextGameState != currentGameState)
			initializeNextGameState();
	}

	@Override
	public void dispose() {
		TextureLoader.disposeAllTextures();
		batch.dispose();
		font.dispose();
		txt.dispose();
	}
	
	private void clear() {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	private void updateNextGameState() {
		switch (currentGameState) {
		case StartScreen: 	 	nextGameState = startScreen.handleInput();break;
		case Menu:			 	nextGameState = menu.handleInput();break;
		case LevelSelection: 	nextGameState = levelSelection.handleInput();break;
		case Controls:			nextGameState = controls.handleInput();break;
		case Credits:			nextGameState = credits.handleInput();break;
		case Level:				nextGameState = level.handleInput();break;
		case PauseMenu:			nextGameState = pauseMenu.handleInput();break;
		case LevelOver:			nextGameState = levelOver.handleInput();break;
			default:
		}	
	}
	
	private void renderCurrentGameState() {
		switch (currentGameState) {
		case StartScreen:		startScreen.render(batch);break;
		case Menu:				menu.render(batch);break;
		case LevelSelection:	levelSelection.render(batch);break;
		case Controls:			controls.render(batch);break;
		case Credits:			credits.render(batch);break;
		case Level:				level.render(batch);break;
		case PauseMenu:			pauseMenu.render(batch);break;
		case LevelOver:			levelOver.render(batch);break;
		default: font.draw(batch, "Error", 100, 300);
		}
	}
	
	private void initializeNextGameState() {
		switch (nextGameState) {
		case LevelSelection: {
			levelSelection.initialize();
		} break;
		case Level: {
			if (currentGameState == GameState.LevelSelection) {
				int selectedLevel = levelSelection.getSelectedLevel();
				level.initialize(selectedLevel);
			} else if (currentGameState == GameState.LevelOver) {
				int selectedLevel = levelOver.getNextLevel();
				level.initialize(selectedLevel);
			}
			// if current == pause ?
		} break;
		case LevelOver: {
			int playedLevel = level.getCurrentLevel();
			boolean won = level.hasWon();
			levelOver.initialize(playedLevel, won);
		} break;
		default:
		}

	}
}
