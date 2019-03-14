package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.mygdx.game.Player.Directions.SOUTH;

public class Enemy extends Sprite {

    private Vector2 velocity = new Vector2();
    private Rectangle enemy;
    private float speed = 50;
    private Sprite sprite;

    private Texture spritessheet;
    private TextureRegion textureRegion;
    private TextureRegion[] texturRegWalkUp, texturRegWalkLeft, texturRegWalkRight, texturRegWalkDown;
    private Player.Directions walkDirection = SOUTH;
    private Animation<Sprite> animation, aniWalkUp, aniWalkDown, aniWalkRight, aniWalkLeft;
    private float elapsedTime = 0;


    // so many numbers and first position not clear because of header
    private int firstX = 0;
    private int firstY = Gdx.graphics.getHeight() - 80;
    private int startX;
    private int startY;
    private int endX;
    private int endY;

//	private boolean xDifference;
//	private boolean yDifference;

    public Enemy(Texture texture, int startTileX, int startTileY, int endTileX, int endTileY, int tileWith) {
        this.sprite = new Sprite(texture);
        this.startX = firstX + startTileX * tileWith + (tileWith - texture.getWidth()) / 2;
        this.startY = firstY - startTileY * tileWith - (tileWith - texture.getHeight()) / 2;
        this.endX = firstX + endTileX * tileWith + (tileWith - texture.getWidth()) / 2;
        this.endY = firstY - endTileY * tileWith - (tileWith - texture.getHeight()) / 2;


//		if (startX == endX) {
//			sameX = true;
//		} else {
//			sameX = false;
//		}
//		if (startY == endY) {
//			sameY = true;
//		} else {
//			sameY = false;
//		}

        System.out.println();
        System.out.println(startX);
        System.out.println(startY);
        System.out.println(endX);
        System.out.println(endY);

        sprite.setPosition(startX, startY);
        this.enemy = new Rectangle(sprite.getX(), sprite.getY(), tileWith, tileWith);

//		System.out.println(sprite.getX());
//		System.out.println(sprite.getY());

    }

    @SuppressWarnings("Duplicates")
    public void initTextures() {

        spritessheet = new Texture("Mummy.png");
        textureRegion = new TextureRegion(spritessheet, 0, 0, 96, 192);
        TextureRegion[][] tmpFrames = split(spritessheet, 32, 48);
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

    @Override
    public void draw(Batch spritebatch) {
        elapsedTime += Gdx.graphics.getDeltaTime();

        spritebatch.draw(animation.getKeyFrame(elapsedTime, true), sprite.getX(), sprite.getY());
    }

    public void update() {

        float delta = Gdx.graphics.getDeltaTime();
//		System.out.println(sprite.getY());
        if (startX < endX) {
            if (sprite.getX() < endX) {
                velocity.x = speed;
            } else {
                velocity.x = -speed;
                int newStartX = endX;
                endX = startX;
                startX = newStartX;

            }

        }
        if (startX > endX) {
            if (sprite.getX() > endX) {
                velocity.x = -speed;
            } else {
                velocity.x = speed;
                int newStartX = endX;
                endX = startX;
                startX = newStartX;

            }
        }

        if (startY < endY) {
            if (sprite.getY() < endY) {
                velocity.y = speed;

            } else {
                velocity.y = -speed;
                int newStartY = endY;
                endY = startY;
                startY = newStartY;
            }
        }
        if (startY > endY) {
            if (sprite.getY() > endY) {
                velocity.y = -speed;

            } else {
                velocity.y = speed;
                int newStartY = endY;
                endY = startY;
                startY = newStartY;
            }
        }
        sprite.setX(sprite.getX() + velocity.x * delta);
        sprite.setY(sprite.getY() + velocity.y * delta);

        enemy.setPosition(sprite.getX(), sprite.getY());


    }

    public Rectangle getRectangle() {
        return this.enemy;
    }
}