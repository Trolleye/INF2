package com.mygdx.game.units;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.projectiles.PlayerProjectile;
import java.util.ArrayList;
import java.util.Iterator;

public class Player extends Unit {
    private final ArrayList<Unit> unitArrayList;
    private int otocenie = 1;
    private final ArrayList<PlayerProjectile> projectiles = new ArrayList<>();
    private float cooldown = 3;
    public Player(ArrayList<Unit> unitArrayList) {
        super(new Texture("hero.png"), 0, 0, 50);
        this.unitArrayList = unitArrayList;
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
        this.cooldown -= deltaTime;
        this.enemyHit();
        if (this.cooldown < 0) {
            Enemy closestEnemy = this.getEnemy();
            if (closestEnemy != null) {
                this.projectiles.add(new PlayerProjectile(new Vector2(closestEnemy.getPosition().x + closestEnemy.getSprite().getWidth() / 2, closestEnemy.getPosition().y + closestEnemy.getSprite().getHeight() / 2), this.getPlayerPos(), this.unitArrayList));
                this.cooldown = 1;
            }
        }
        if (!this.projectiles.isEmpty()) {
            for (PlayerProjectile projectile : this.projectiles) {
                projectile.vykresli(batch);
            }
        }
    }



    private Enemy getEnemy() {
        float closestEnemyRange = 99999;
        Enemy closestEnemy = null;
        for (int i = 1; i < this.unitArrayList.size(); i++) {
            if (this.unitArrayList.get(i) != null) {
                if (this.unitArrayList.get(i)instanceof Enemy) {
                    if ((this.unitArrayList.get(i)).getLengthFromUnit(this.getPlayerPos()) <= closestEnemyRange) {
                        closestEnemyRange = (this.unitArrayList.get(i)).getLengthFromUnit(this.getPlayerPos());
                        closestEnemy = (Enemy)this.unitArrayList.get(i);
                    }
                }
            }
        }
        return closestEnemy;
    }

    public Vector2 getPlayerPos() {
        return this.getPosition();
    }

    public void enemyHit() {
        this.projectiles.removeIf(PlayerProjectile::isEnemyHit);
    }
}