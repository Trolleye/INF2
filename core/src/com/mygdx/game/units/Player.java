package com.mygdx.game.units;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.projectiles.PlayerProjectile;

import java.util.ArrayList;

/**
 * Trieda Player predstavuje hráča v hre.
 * Hráč môže pohybovať svojou postavou pomocou klávesnice a útočiť na nepriateľov vystreľovaním projektilov.
 */
public class Player extends Unit {
    private final ArrayList<Unit> unitArrayList;
    private int otocenie = 1;
    private final ArrayList<PlayerProjectile> projectiles = new ArrayList<>();
    private float cooldown = 1;

    /**
     * Konštruktor triedy Player.
     * @param unitArrayList Zoznam všetkých jednotiek v hre
     */
    public Player(ArrayList<Unit> unitArrayList) {
        super(new Texture("hero.png"), 0, 0, 50);
        this.unitArrayList = unitArrayList;
    }

    /**
     * Metóda na aktualizáciu stavu hráča v každom snímku.
     * Hráč môže pohybovať svojou postavou pomocou klávesnice.
     * @param deltaTime Čas od posledného snímku
     */
    @Override
    public void update(float deltaTime) {
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

    /**
     * Metóda na vykonanie útoku hráča.
     * Hráč útočí na nepriateľov vystreľovaním projektilov.
     * @param deltaTime Čas od posledného snímku
     * @param batch SpriteBatch pre vykreslenie
     */
    @Override
    public void attack(float deltaTime, SpriteBatch batch) {
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
                projectile.update(batch);
            }
        }
    }

    /**
     * Metóda, ktorá vráti pozíciu hráča.
     * @return Pozícia hráča
     */
    public Vector2 getPlayerPos() {
        return this.getPosition();
    }

    /**
     * Metóda na odstránenie projektilov, ktoré zasiahli nepriateľov.
     */
    public void enemyHit() {
        this.projectiles.removeIf(PlayerProjectile::isEnemyHit);
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
}