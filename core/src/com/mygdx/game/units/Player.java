package com.mygdx.game.units;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends Unit{
    private Vector2 pozicia;
    private int otocenie = 1;
    private float rychlost = 500;
    public Player(Texture img){
        super(img);
        getSprite().setScale(0.5F);
        pozicia = new Vector2(Gdx.graphics.getWidth()/2, getSprite().getScaleY()*getSprite().getHeight()/2);
    }

    @Override
    public void pohyb(float deltaTime){
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            pozicia.x-=deltaTime*rychlost;
            if(otocenie==1){
                getSprite().flip(true,false);
                otocenie=0;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            pozicia.x+=deltaTime*rychlost;
            if(otocenie==0){
            getSprite().flip(true,false);
            otocenie=1;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) pozicia.y-=deltaTime*rychlost;
        if(Gdx.input.isKeyPressed(Input.Keys.W)) pozicia.y+=deltaTime*rychlost;
    }

    @Override
    public void vykresli(SpriteBatch batch) {
        pohyb(Gdx.graphics.getDeltaTime());
        batch.draw(getSprite(), pozicia.x, pozicia.y);
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
