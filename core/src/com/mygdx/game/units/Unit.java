package com.mygdx.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Abstraktná trieda Unit predstavuje základnú jednotku v hre.
 * Táto trieda definuje vlastnosti a metódy, ktoré sú spoločné pre všetky typy jednotiek.
 */
public abstract class Unit {
    private final Vector2 pozicia;
    private final Sprite sprite;
    private boolean isDead = false;
    private final int hitbox;

    /**
     * Konštruktor triedy Unit.
     * @param img Textúra jednotky
     * @param x X-ová pozícia jednotky na mape
     * @param y Y-ová pozícia jednotky na mape
     * @param hitbox Veľkosť hitboxu jednotky
     */
    public Unit(Texture img, float x, float y, int hitbox) {
        this.sprite = new Sprite(img);
        this.hitbox = hitbox;
        this.pozicia = new Vector2(x, y);
    }

    /**
     * Abstraktná metóda na aktualizáciu stavu jednotky v každom snímku.
     * @param deltaTime Čas od posledného snímku
     */
    public abstract void update(float deltaTime);

    /**
     * Metóda na získanie pozície jednotky na mape.
     * @return Pozícia jednotky
     */
    public Vector2 getPozicia() {
        return this.pozicia;
    }

    /**
     * Metóda na získanie veľkosti hitboxu jednotky.
     * @return Veľkosť hitboxu jednotky
     */
    public int getHitbox() {
        return this.hitbox;
    }

    /**
     * Metóda na vykreslenie jednotky.
     * @param batch SpriteBatch pre vykreslenie
     */
    public void vykresli(SpriteBatch batch) {
        this.update(Gdx.graphics.getDeltaTime());
        this.attack(Gdx.graphics.getDeltaTime(), batch);
        batch.draw(this.getSprite(), this.pozicia.x, this.pozicia.y);

    }

    /**
     * Abstraktná metóda na vykonanie útoku jednotky.
     * @param deltaTime Čas od posledného snímku
     * @param batch SpriteBatch pre vykreslenie
     */
    public abstract void attack(float deltaTime, SpriteBatch batch);

    /**
     * Metóda na označenie jednotky ako mŕtvej.
     */
    public void unitDeath() {
        this.isDead = true;
    }

    /**
     * Metóda na výpočet vzdialenosti medzi jednotkou a inou jednotkou na mape.
     * @param unitPos Pozícia druhej jednotky
     * @return Vzdialenosť medzi jednotkami
     */
    public float getLengthFromUnit(Vector2 unitPos) {
        return new Vector2(unitPos.x, unitPos.y).sub(this.getPosition()).len();
    }

    /**
     * Metóda na zistenie, či je jednotka mŕtva.
     * @return true, ak je jednotka mŕtva, inak false
     */
    public boolean isDead() {
        return this.isDead;
    }

    protected Vector2 getPosition() {
        return this.pozicia;
    }

    protected Sprite getSprite() {
        return this.sprite;
    }
}