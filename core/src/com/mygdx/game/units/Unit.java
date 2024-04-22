package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.UnitUpdater;

public abstract class Unit {
    private Sprite sprite;
    public Unit(Texture img) {
        this.sprite = new Sprite(img);
    }

    abstract void pohyb(float deltaTime);

    public abstract void vykresli(SpriteBatch batch);

    public Sprite getSprite(){
        return sprite;
    }
}