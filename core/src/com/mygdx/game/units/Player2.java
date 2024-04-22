package com.mygdx.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player2 {
    private Vector2 pozicia;
    private Sprite sprite;
    private int otocenie = 1;
    private float rychlost = 500;
    public Player2(Texture img){
        sprite = new Sprite(img);
        sprite.setScale(0.5F);
        pozicia = new Vector2(Gdx.graphics.getWidth()/2, sprite.getScaleY()*sprite.getHeight()/2);
    }

    public void pohyb(float deltaTime){
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            pozicia.x-=deltaTime*rychlost;
            if(otocenie==1){
                sprite.flip(true,false);
                otocenie=0;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            pozicia.x+=deltaTime*rychlost;
            if(otocenie==0){
            sprite.flip(true,false);
            otocenie=1;
            }
        };
        if(Gdx.input.isKeyPressed(Input.Keys.S)) pozicia.y-=deltaTime*rychlost;
        if(Gdx.input.isKeyPressed(Input.Keys.W)) pozicia.y+=deltaTime*rychlost;
    }
    public void vykresli(SpriteBatch batch){
        pohyb(Gdx.graphics.getDeltaTime());
        sprite.setPosition(pozicia.x, pozicia.y);
        sprite.draw(batch);
    }

    public float getX() {
        return pozicia.x;
    }

    public float getY() {
        return pozicia.y;
    }

    public void setPosition(int x, int y) {
        pozicia.x = x;
        pozicia.y = y;
    }
}
