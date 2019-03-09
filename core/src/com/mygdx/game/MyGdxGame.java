package com.mygdx.game;


import Classes.Map;
import Classes.Player;
import Classes.Wall;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


import java.awt.*;
import java.util.Random;


public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
    public static boolean testvar = true;

    private SpriteBatch batch;
    public Player player;
    public TiledMap tiledMap;
    public TiledMapRenderer tiledmaprenderer;
    public OrthographicCamera camera;

    //@Asel
    // for debug and manuel Map generation
    public boolean walls[][] = new boolean[20][15];

    //@Asel
    //this method run´s on the start of the programm
    //it initializes our crucial objects for later use in
    //the method render
    @Override
    public void create() {

        //@Asel
        //we create  a camera and set it on the center of the Game-Window
        //The camera is what we see later
        //Gdx is a static Class with Sub-Classes and methods for utility
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        //@Asel
        //load a tiled Map we created on 'Tiles' programm and also create
        //a map Renderer for the tilemap
        //Important: we created tiled maps with tiles who have the attribute
        //"blocked" which serves as wall´s for later use
        tiledMap = new TmxMapLoader().load("assets/prototype2.tmx");
        tiledmaprenderer = new OrthogonalTiledMapRenderer(tiledMap);

        //@Asel
        //a buffer that let´s us draw
        batch = new SpriteBatch();

        //@Asel
        //drawing a pixmap for the charakter sprite
        Pixmap pix = new Pixmap(20, 20, Pixmap.Format.RGBA8888);
        pix.setColor(Color.GREEN);
        pix.fill();

        //@Asel
        //create a player figure with the pixmap we created(looks like  a  green blob)
        //IMPORTANT: always .dispose() texture´s and pixmap´s after use!
        player = new Player(new Sprite(new Texture(pix)), (TiledMapTileLayer) tiledMap.getLayers().get(0));
        pix.dispose();

        //@Asel
        //this listen´s to keyboard Input´s and send´s it to the player object
        Gdx.input.setInputProcessor(player);

    }

    @Override
    public void dispose() {
        batch.dispose();
        player.getTexture().dispose();
    }


    //@Asel
    //this method is called constantly. It´s also extremely important
    @Override
    public void render() {

        //@Asel
        //Not that important, basic openGL configurations for Background,Alpha channels etc.
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        //@Asel
        //render whatever the camera shows,
        camera.update();
        tiledmaprenderer.setView(camera);
        tiledmaprenderer.render();

        //@Asel
        //open the buffer and start drawing
        batch.begin();
        player.draw(batch);
        batch.end();
    }


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public boolean keyDown(int keycode) {

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {


        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
