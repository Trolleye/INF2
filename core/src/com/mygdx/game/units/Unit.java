package com.mygdx.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Unit {
    private Vector2 pozicia;
    private Sprite sprite;
    private float rychlost;
    public Unit(Texture img) {
        this.sprite = new Sprite(img);

    }

    public void pohyb(float deltaTime){

    }

    public void vykresli(SpriteBatch batch){
        pohyb(Gdx.graphics.getDeltaTime());
        sprite.setPosition(pozicia.x, pozicia.y);
        sprite.draw(batch);
    }

    protected Sprite getSprite(){
        return sprite;
    }
}