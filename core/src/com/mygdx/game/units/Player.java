package com.mygdx.game.units;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends Unit{
    private int otocenie = 1;
    private float rychlost = 500;
    public Player(Texture img){
        super(img, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        getSprite().setScale(0.5F);
    }

    @Override
    public void pohyb(float deltaTime){
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            getPozicia().x-=deltaTime*rychlost;
            if(otocenie==1){
                getSprite().flip(true,false);
                otocenie=0;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            getPozicia().x+=deltaTime*rychlost;
            if(otocenie==0){
            getSprite().flip(true,false);
            otocenie=1;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) getPozicia().y-=deltaTime*rychlost;
        if(Gdx.input.isKeyPressed(Input.Keys.W)) getPozicia().y+=deltaTime*rychlost;
    }

    @Override
    public void vykresli(SpriteBatch batch) {
        pohyb(Gdx.graphics.getDeltaTime());
        batch.draw(getSprite(), getPozicia().x, getPozicia().y);
    }

    public Vector2 getPlayerPos(){
        return getPozicia();
    }
}
