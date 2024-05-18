package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Trieda Bat predstavuje nepriateľskú jednotku netopiera.
 * Netopier je nepriateľ, ktorý sa pohybuje na mape a útočí na hráča.
 */
public class Bat extends Enemy {

    /**
     * Konštruktor triedy Bat.
     * @param player Referencia na hráča
     * @param x X-ová pozícia netopiera na mape
     * @param y Y-ová pozícia netopiera na mape
     */
    public Bat(Player player, float x, float y) {
        super(new Texture("bat.png"), x, y, 170, player, 30);
    }

    /**
     * Metóda na útok netopiera na hráča.
     * @param deltaTime Čas od posledného snímku
     * @param batch SpriteBatch pre vykreslenie
     */
    @Override
    public void attack(float deltaTime, SpriteBatch batch) {
        if (this.getLengthFromUnit(this.getPlayerPos()) < this.getPlayer().getHitbox()) {
            this.getPlayer().unitDeath();
        }
    }
}
