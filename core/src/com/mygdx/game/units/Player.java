package com.mygdx.game.units;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Player extends Unit {
    private int otocenie = 1;
    private final Vector2 cursorPosition = new Vector2();
    private final ArrayList<PlayerProjectile> projectiles = new ArrayList<PlayerProjectile>();
    private float cooldown = 3;
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

    @Override
    void attack(float deltaTime, SpriteBatch batch) {
        System.out.println(Gdx.input.getY() - 399);
        this.cooldown -= deltaTime;
       // if (this.cooldown < 0) {
            this.projectiles.add(new PlayerProjectile(new Vector2(Gdx.input.getX() - 667 + this.getPosition().x, Gdx.input.getY() - 399 + this.getPosition().y), this.getPlayerPos(), this.getTexture()));
            this.cooldown = 100;
     //   }
        for (PlayerProjectile projectile : this.projectiles) {
            projectile.vykresli(batch);
        }
    }

    public Vector2 getPlayerPos() {
        return this.getPosition();
    }
}