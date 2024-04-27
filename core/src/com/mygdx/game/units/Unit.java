package com.mygdx.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Unit {
    private Vector2 pozicia;
    private Sprite sprite;
    public Unit(Texture img, float x, float y) {
        this.sprite = new Sprite(img);
        this.pozicia = new Vector2(x, y);
    }

    abstract void pohyb(float deltaTime);

    public void vykresli(SpriteBatch batch){
        pohyb(Gdx.graphics.getDeltaTime());
        batch.draw(getSprite(), pozicia.x, pozicia.y);
    }

    protected Sprite getSprite(){
        return sprite;
    }
    protected Vector2 getPozicia(){
        return this.pozicia;
    }

    public void setPosition(int x, int y) {
        pozicia.x = x;
        pozicia.y = y;
    }
}