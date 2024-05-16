package com.mygdx.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Unit {
    private final Vector2 pozicia;
    private final Sprite sprite;
    public Unit(Texture img, float x, float y) {
        this.sprite = new Sprite(img);
        this.pozicia = new Vector2(x, y);
    }

    abstract void pohyb(float deltaTime);

    public void vykresli(SpriteBatch batch) {
        this.pohyb(Gdx.graphics.getDeltaTime());
        this.attack(Gdx.graphics.getDeltaTime(), batch);
        batch.draw(this.getSprite(), this.pozicia.x, this.pozicia.y);
    }
    protected Texture getTexture() {
        return this.sprite.getTexture();
    }
    abstract void attack(float deltaTime, SpriteBatch batch);
    protected Sprite getSprite() {
        return this.sprite;
    }
    protected Vector2 getPosition() {
        return this.pozicia;
    }
}