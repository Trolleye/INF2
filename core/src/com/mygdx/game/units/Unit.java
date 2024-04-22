package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Unit {
    private Vector2 pozicia;
    private Sprite sprite;
    public Unit(Texture img, int x, int y) {
        this.sprite = new Sprite(img);
        this.pozicia = new Vector2(x, y);
    }

    abstract void pohyb(float deltaTime);

    public abstract void vykresli(SpriteBatch batch);

    protected Sprite getSprite(){
        return sprite;
    }
    protected Vector2 getPozicia(){
        return this.pozicia;
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