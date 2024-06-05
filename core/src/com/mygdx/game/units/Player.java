package com.mygdx.game.units;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    private final BitmapFont text = new BitmapFont();
    private int otocenie = 1;
    private final ArrayList<PlayerProjectile> projectiles = new ArrayList<>();
    private float cooldown = 1;
    private boolean isDashing = false;
    private float dashTime = 0;
    private float dashCooldown = 0;
    private static final float DASH_DURATION = 0.2f;
    private static final float DASH_SPEED = 500;
    private static final float DASH_COOLDOWN = 1.0f;

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
        float currentSpeed = speed;

        // Dash logic
        if (isDashing) {
            dashTime -= deltaTime;
            if (dashTime <= 0) {
                isDashing = false;
                dashCooldown = DASH_COOLDOWN;
            } else {
                currentSpeed = DASH_SPEED;
            }
        } else {
            if (dashCooldown > 0) {
                dashCooldown -= deltaTime;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && dashCooldown <= 0) {
                isDashing = true;
                dashTime = DASH_DURATION;
            }
        }

        // Movement logic
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.getPosition().x -= deltaTime * currentSpeed;
            if (this.otocenie == 1) {
                this.getSprite().flip(true, false);
                this.otocenie = 0;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.getPosition().x += deltaTime * currentSpeed;
            if (this.otocenie == 0) {
                this.getSprite().flip(true, false);
                this.otocenie = 1;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            this.getPosition().y -= deltaTime * currentSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            this.getPosition().y += deltaTime * currentSpeed;
        }
    }

    private void showDashCooldown(SpriteBatch batch) {

        if (this.dashCooldown <= 0) {
            text.draw(batch, "Dash ready", this.getPozicia().x - 6, this.getPozicia().y);
        } else {
            text.draw(batch, String.format("%.2g%n", this.dashCooldown), this.getPozicia().x + 18, this.getPozicia().y);
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

    public void playerSpecifics(SpriteBatch batch) {
        this.showDashCooldown(batch);
    }
}