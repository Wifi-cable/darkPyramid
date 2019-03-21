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

import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.prefs.Preferences;

public class MyGdxGame extends ApplicationAdapter {
	
	// for testing purpose	
	public static BitmapFont font;
	public static Texture txt;
	public static final int numberofLevels = 5;
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
	Preferences savegameFile;

	@Override
	public void create() {
		unlockedLevels = readFromUnlockedLevelsFile();
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
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
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
		case Level: {
			if (currentGameState == GameState.LevelSelection) {
				int selectedLevel = levelSelection.getSelectedLevel();
				level.initialize(selectedLevel);
				boolean darkmode = levelSelection.getDarkmode();
				level.setDarkMode(darkmode);
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
	private int readFromUnlockedLevelsFile() {

		String userDirectory = System.getProperty("user.home");
		Path pathToSaveGameFile = Paths.get(userDirectory, "DarkPyramidSaveGameFile.txt");
		try {
            if (!Files.exists(pathToSaveGameFile)) {
				System.out.println("Does not exist");
				BufferedWriter writer = null;
				try{
					writer = new BufferedWriter(new FileWriter(pathToSaveGameFile.toString()));
					writer.write("1");
					writer.flush();
					writer.close();
				} catch(Exception e) {
					e.printStackTrace();
					writer.close();
				}//finally {
//					if(writer != null) {
//						try {
//							writer.close();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//				}
			}
		
//		System.out.println(userDirectory);
		
		File file = pathToSaveGameFile.toFile();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String readLine; 
		readLine = bufferedReader.readLine();
		int numberOfunlockedLevels = Integer.parseInt(readLine);	
			return numberOfunlockedLevels;
		} catch (Exception e) {
			e.printStackTrace();
			return 1; // As backup, in case of exception the game can be played from Level 1 on.
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		} catch (IOException e1) {
			e1.printStackTrace();
			return 1;
		}
		
		
	}

	
}
