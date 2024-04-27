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
        System.out.println(getPlayerPos());
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

    public Vector2 getPlayerPos(){
        return getPozicia();
    }
}