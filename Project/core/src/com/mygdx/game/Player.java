package com.mygdx.game;

import java.text.Format;
import java.text.Normalizer;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sun.glass.ui.Pixels;

public class Player {

    private Sprite sprite;
    private Animation<Sprite> animation;
    private Vector2 velocity = new Vector2();
    private float speed = 100;
    private Level currentLevel;
    private boolean hasFailed;
    private boolean hasWon;
    private float elapsedTime = 0;

    public Player(Level level) {
        Pixmap pix = new Pixmap(20, 20, Pixmap.Format.RGBA8888);
        pix.setColor(Color.GREEN);
        pix.fill();
        sprite = new Sprite(new Texture(pix));
        pix.dispose();
        currentLevel = level;

        TextureRegion[][] tmpFrames = TextureRegion.split
                (new Texture
                                (new FileHandle("testanimation.png"),Pixmap.Format.RGB888, true)
                             , 40, 40);
        TextureRegion[] animationFrames = new TextureRegion[4];
        int index = 0;
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                System.out.println(tmpFrames.length);
                animationFrames[index++] = tmpFrames[j][i];

            }
        }
        animation = new Animation(1f / 4f, animationFrames);
    }


    public void draw(Batch spritebatch) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        spritebatch.draw(animation.getKeyFrame(elapsedTime, true), sprite.getX(), sprite.getY());
        //sprite.draw(spritebatch);
    }

    private boolean hasCollidedWith(List<Rectangle> objects) {
        Rectangle playerRectangle = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        for (Rectangle r : objects) {
            if (playerRectangle.overlaps(r))
                return true;
        }
        return false;
    }

    // @Asel
    // Delta time helps with a constant game speed on different frame rates
    // if we didnt handle it in any way, the game speed would be
    // influenced by the frame rate
    public void update(float delta) {

        System.out.println(animation.getKeyFrame(elapsedTime, true));
        // sprite = new Sprite(animation.getKeyFrame(elapsedTime));


        velocity.x = 0;
        velocity.y = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            velocity.y = speed;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            velocity.y = -speed;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            velocity.x = speed;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            velocity.x = -speed;

        float oldX = sprite.getX();
        float oldY = sprite.getY();

        sprite.setX(sprite.getX() + velocity.x * delta);
        if (hasCollidedWith(currentLevel.getWalls()))
            sprite.setX(oldX);
        sprite.setY(sprite.getY() + velocity.y * delta);
        if (hasCollidedWith(currentLevel.getWalls()))
            sprite.setY(oldY);
    }


    public boolean hasWon() {
        return hasWon;
    }


    public boolean hasFailed() {
        return hasFailed;
    }
}
