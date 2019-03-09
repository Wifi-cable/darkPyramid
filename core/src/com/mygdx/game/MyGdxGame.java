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


    public Rectangle rectangle;
    private Random rand;


    public boolean walls[][] = new boolean[20][15];


    @Override
    public void create() {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        tiledMap = new TmxMapLoader().load("../assets/prototype2.tmx");
        tiledmaprenderer = new OrthogonalTiledMapRenderer(tiledMap);



        batch = new SpriteBatch();


        Pixmap pix = new Pixmap(20, 20, Pixmap.Format.RGBA8888);
        pix.setColor(Color.GREEN);
        pix.fill();
        player = new Player(new Sprite(new Texture(pix)), (TiledMapTileLayer) tiledMap.getLayers().get(0));
        pix.dispose();


        Gdx.input.setInputProcessor(player);

    }

    @Override
    public void dispose() {
        batch.dispose();
        player.getTexture().dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);


        camera.update();
        tiledmaprenderer.setView(camera);
        tiledmaprenderer.render();


        batch.begin();
        player.draw(batch);

//        player.sprite.setPosition(player.cordinateX, player.cordinateY);
//        player.sprite.draw(batch);

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
