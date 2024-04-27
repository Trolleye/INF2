package com.mygdx.game.units;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player extends Unit{
    private int otocenie = 1;
    private float rychlost = 250;
    public Player(){
        super(new Texture("hero.png"), 0, 0);
    }

    @Override
    public void pohyb(float deltaTime){
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            getPosition().x-=deltaTime*rychlost;
            if(otocenie==1){
                getSprite().flip(true,false);
                otocenie=0;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            getPosition().x+=deltaTime*rychlost;
            if(otocenie==0){
            getSprite().flip(true,false);
            otocenie=1;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) getPosition().y-=deltaTime*rychlost;
        if(Gdx.input.isKeyPressed(Input.Keys.W)) getPosition().y+=deltaTime*rychlost;
    }

    public Vector2 getPlayerPos(){
        return getPosition();
    }
}