package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

public class TextureCreator  {

    public Pixmap pixmap;
    public Sprite sprite;
    public Texture texture;
    public Body body;
    public int cordinateX=0;
    public int CordinateY=0;

    public void setPixmap(Pixmap pixmap){
        this.pixmap = pixmap;
    }

    public void toSprite(){
        texture = new Texture(pixmap);
        pixmap.dispose();
        sprite = new Sprite(texture);
    }

    public void dispose(){
        texture.dispose();
    }










}
