package com.mygdx.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Unit {
    private final Vector2 pozicia;
    private final Sprite sprite;
    private boolean isDead = false;
    private final int hitbox;
    public Unit(Texture img, float x, float y, int hitbox) {
        this.sprite = new Sprite(img);
        this.hitbox = hitbox;
        this.pozicia = new Vector2(x, y);
    }
    public abstract void update(float deltaTime);

    public Vector2 getPozicia() {
        return this.pozicia;
    }

    public int getHitbox() {
        return this.hitbox;
    }
    public void vykresli(SpriteBatch batch) {
        this.update(Gdx.graphics.getDeltaTime());
        this.attack(Gdx.graphics.getDeltaTime(), batch);
        batch.draw(this.getSprite(), this.pozicia.x, this.pozicia.y);
    }
    public abstract void attack(float deltaTime, SpriteBatch batch);
    protected Sprite getSprite() {
        return this.sprite;
    }

    public void unitDeath() {
        this.isDead = true;
    }


    public float getLengthFromUnit(Vector2 unitPos) {
        return new Vector2(unitPos.x, unitPos.y).sub(this.getPosition()).len();
    }
    public boolean isDead() {
        return this.isDead;
    }
    protected Vector2 getPosition() {
        return this.pozicia;
    }
}