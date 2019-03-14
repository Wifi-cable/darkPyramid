package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import static com.mygdx.game.Player.Directions.*;

public class Player {

    private Sprite sprite;
    private Texture spritessheet;
    private TextureRegion textureRegion;
    private TextureRegion[] texturRegWalkUp,texturRegWalkLeft,texturRegWalkRight,texturRegWalkDown;
    private Directions walkDirection = SOUTH;
    private Animation<Sprite> animation,aniWalkUp,aniWalkDown,aniWalkRight,aniWalkLeft;
    private Vector2 velocity = new Vector2();
    private float speed = 100;
    private Level currentLevel;
    private boolean hasFailed;
    private boolean hasWon;
    private float elapsedTime = 0;


    public Player() {
        currentLevel = Level.thisLevel;
        initTextures();
    }

    public void initTextures() {

        spritessheet = new Texture("viola.png");
        textureRegion = new TextureRegion(spritessheet, 0, 0, 96, 192);
        TextureRegion[][] tmpFrames = textureRegion.split(spritessheet, 32, 48);
        texturRegWalkDown = new TextureRegion[3];
        texturRegWalkLeft = new TextureRegion[3];
        texturRegWalkRight = new TextureRegion[3];
        texturRegWalkUp = new TextureRegion[3];
        int index = 0;


        for (int j = 0; j < 3; j++) {
            System.out.println(tmpFrames.length);
            texturRegWalkDown[index] = tmpFrames[0][j];
            texturRegWalkLeft[index] = tmpFrames[1][j];
            texturRegWalkRight[index] = tmpFrames[2][j];
            texturRegWalkUp[index] = tmpFrames[3][j];
            index++;
        }


        sprite = new Sprite(texturRegWalkDown[1], 32, 48, 32, 48);
        sprite.setPosition(5, 5);

        aniWalkDown = new Animation(0.3f, texturRegWalkDown);
        aniWalkUp = new Animation(0.3f, texturRegWalkUp);
        aniWalkLeft = new Animation(0.3f, texturRegWalkLeft);
        aniWalkRight = new Animation(0.3f, texturRegWalkRight);
        animation = new Animation(0.3f, texturRegWalkRight);


    }


    public void draw(Batch spritebatch) {
        elapsedTime += Gdx.graphics.getDeltaTime();

        spritebatch.draw(animation.getKeyFrame(elapsedTime, true), sprite.getX(), sprite.getY());

    }

    private boolean hasCollidedWith(List<Rectangle> objects) {
        Rectangle playerRectangle = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth() - 6, sprite.getHeight() / 2);
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
    public void update() {

        float delta = Gdx.graphics.getDeltaTime();
        velocity.x = 0;
        velocity.y = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            velocity.y = speed;
            walkDirection = NORTH;
            animation = aniWalkUp;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            velocity.y = -speed;
            walkDirection = SOUTH;
            animation = aniWalkDown;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            velocity.x = speed;
            walkDirection = EAST;
            animation = aniWalkRight;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

            velocity.x = -speed;
            walkDirection = WEST;
            animation = aniWalkLeft;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            switch (walkDirection) {
                case NORTH:
                    animation = new Animation(0.3f, texturRegWalkUp[1]);
                    break;
                case SOUTH:
                    animation = new Animation(0.3f, texturRegWalkDown[1]);
                    break;
                case WEST:
                    animation = new Animation(0.3f, texturRegWalkLeft[1]);
                    break;
                case EAST:
                    animation = new Animation(0.3f, texturRegWalkRight[1]);
                    break;
            }


        }
        float oldX = sprite.getX();
        float oldY = sprite.getY();

        sprite.setX(sprite.getX() + velocity.x * delta);
        if (hasCollidedWith(currentLevel.getWalls()))
            sprite.setX(oldX);
        sprite.setY(sprite.getY() + velocity.y * delta);
        if (hasCollidedWith(currentLevel.getWalls()))
            sprite.setY(oldY);

        //animation = new Animation(0.3f,texturRegWalkDown[1].getTexture());
    }


    public boolean hasWon() {
        return hasWon;
    }


    public boolean hasFailed() {
        return hasFailed;
    }


    public enum Directions {
        NORTH,
        SOUTH,
        WEST,
        EAST,

    }

    public Rectangle getRectangle() {
        return sprite.getBoundingRectangle();
    }
}
