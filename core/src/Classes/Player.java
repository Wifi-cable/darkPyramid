package Classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite implements InputProcessor {
//    public int cordinateX
//    public int cordinateY

    private Vector2 velocity = new Vector2();
    private float speed = 100;
    private TiledMapTileLayer collisionLayer;

    public Player(Sprite sprite, TiledMapTileLayer collisionMap) {
        super(sprite);
        this.collisionLayer = collisionMap;
    }

    @Override
    public void draw(Batch spritebatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spritebatch);
    }

    public void update(float delta) {


        float oldX = getX();
        float oldY = getY();
        float tileWidth = collisionLayer.getTileWidth();
        float tileHeight = collisionLayer.getTileHeight();
        boolean collidedonX = false;
        boolean collidedonY = false;

        setX(getX() + velocity.x * delta);



        if (velocity.x > 0) {
            //top right
            collidedonX = collisionLayer.getCell((int) ((getX() + getWidth()) / tileWidth), (int) ((getY() + getHeight()) / tileHeight)).getTile()
                    .getProperties().containsKey("blocked");
            //miffle right
            if (!collidedonX)
                collidedonX = collisionLayer.getCell((int) ((getX() + getWidth()) / tileWidth), (int) ((getY() + getHeight() / 2) / tileHeight)).getTile()
                        .getProperties().containsKey("blocked");


            //bot right
            if (!collidedonX)
                collidedonX = collisionLayer.getCell((int) ((getX() + getWidth()) / tileWidth), (int) ((getY()) / tileHeight)).getTile()
                        .getProperties().containsKey("blocked");


        } else if (velocity.x < 0) {
            //top left
            collidedonX = collisionLayer.getCell((int) (getX() / tileWidth), (int) ((getY() + getHeight()) / tileHeight))
                    .getTile().getProperties().containsKey("blocked");

            //middle left
            if (!collidedonX)
                collidedonX = collisionLayer.getCell((int) (getX() / tileWidth), (int) ((getY() + getHeight() / 2) / tileHeight))
                        .getTile().getProperties().containsKey("blocked");

            //bot left
            if (!collidedonX)
                collidedonX = collisionLayer.getCell((int) (getX() / tileWidth), (int) (getY() / tileHeight)).getTile()
                        .getProperties().containsKey("blocked");

        }

        if(collidedonX){
            setX(oldX);
            velocity.x=0;
        }


        //debugg
        if(collidedonX || collidedonY)
        System.out.println("blocked");


        //move Y -Axis
        setY(getY() + velocity.y * delta);

        if (velocity.y > 0) {
            //bot left
            collidedonY = collisionLayer.getCell((int) ((getX()) / tileWidth), (int) ((getY()) / tileHeight)).getTile()
                    .getProperties().containsKey("blocked");
            //bot middle
            if (!collidedonY)
                collidedonY = collisionLayer.getCell((int) (((getX())+getWidth()) / tileWidth), (int) ((getY()) / tileHeight)).getTile()
                        .getProperties().containsKey("blocked");
            //bot right
            if (!collidedonY)
                collidedonY = collisionLayer.getCell((int) ((getX()+getWidth()) / tileWidth), (int) ((getY()) / tileHeight)).getTile()
                        .getProperties().containsKey("blocked");

        } else if (velocity.y < 0) {

            //top left
            collidedonY = collisionLayer.getCell((int) ((getX()) / tileWidth), (int) ((getY()+getHeight()  ) / tileHeight)).getTile()
                    .getProperties().containsKey("blocked");

            //top mid
            if(!collidedonY)
                    collidedonY = collisionLayer.getCell((int) (((getX())+getWidth()) / tileWidth), (int) ((getY()+getHeight()) / tileHeight)).getTile()
                            .getProperties().containsKey("blocked");

            //top right

            if (collidedonY)
                collidedonY = collisionLayer.getCell((int) (((getX())+getWidth()) / tileWidth), (int) ((getY()) / tileHeight)).getTile()
                        .getProperties().containsKey("blocked");



        }

        if(collidedonY){
            setY(oldY);
            velocity.y =0;
        }
    }













    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                velocity.y +=speed;
                break;
            case Input.Keys.DOWN:
                velocity.y -= speed;
                break;
            case Input.Keys.RIGHT:
                velocity.x += speed;
                break;
            case Input.Keys.LEFT:
                velocity.x -= speed ;
                break;

        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                velocity.y =0;
                break;
            case Input.Keys.DOWN:
                velocity.y =0;
                break;
            case Input.Keys.RIGHT:
                velocity.x =0;
                break;
            case Input.Keys.LEFT:
                velocity.x =0 ;
                break;

        }
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




    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
    }
}
