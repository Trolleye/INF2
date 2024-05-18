package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Trieda Zombie predstavuje nepriateľskú jednotku zombie.
 * Zombie útočí na hráča.
 */
public class Zombie extends Enemy {

    /**
     * Konštruktor triedy Zombie.
     * @param player Referencia na hráča
     * @param x X-ová pozícia zombiaka na mape
     * @param y Y-ová pozícia zombiaka na mape
     */
    public Zombie(Player player, float x, float y) {
        super(new Texture("zombie.png"), x, y, 150, player, 50);
    }

    /**
     * Metóda na vykonanie útoku zombie.
     * Zombie útočí na hráča a spôsobuje mu smrť.
     * @param deltaTime Čas od posledného snímku
     * @param batch SpriteBatch pre vykreslenie
     */
    @Override
    public void attack(float deltaTime, SpriteBatch batch) {
        if (this.getLengthFromUnit(this.getPlayerPos()) < this.getPlayer().getHitbox() ) {
            this.getPlayer().unitDeath();
        }
    }
}
