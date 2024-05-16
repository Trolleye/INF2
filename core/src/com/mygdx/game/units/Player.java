package com.mygdx.game.units;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player extends Unit {
    private int otocenie = 1;

    public Player() {
        super(new Texture("hero.png"), 0, 0);
    }

    @Override
    public void pohyb(float deltaTime) {
        float speed = 250;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.getPosition().x -= deltaTime * speed;
            if (this.otocenie == 1) {
                this.getSprite().flip(true, false);
                this.otocenie = 0;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.getPosition().x += deltaTime * speed;
            if (this.otocenie == 0) {
                this.getSprite().flip(true, false);
                this.otocenie = 1;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            this.getPosition().y -= deltaTime * speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            this.getPosition().y += deltaTime * speed;
        }
    }

    public Vector2 getPlayerPos() {
        return this.getPosition();
    }
}