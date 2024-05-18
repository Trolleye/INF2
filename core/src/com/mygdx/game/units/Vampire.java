package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Trieda Vampire predstavuje nepriateľskú jednotku vampír.
 * Vampír útočí na hráča a môže spawnovať ďalšie jednotky.
 */
public class Vampire extends Enemy {
    private float cooldown = 4;

    /**
     * Konštruktor triedy Vampire.
     * @param player Referencia na hráča
     * @param x X-ová pozícia vampíra na mape
     * @param y Y-ová pozícia vampíra na mape
     */
    public Vampire(Player player, float x, float y) {
        super(new Texture(  "vampire.png"), x, y, 200, player, 50);
    }

    /**
     * Metóda na aktualizáciu stavu vampíra v každom snímku.
     * Vampír sa pohybuje smerom k hráčovi, ak je hráč dostatočne ďaleko.
     * @param deltaTime Čas od posledného snímku
     */
    @Override
    public void update(float deltaTime) {
        if (this.getLengthFromUnit(this.getPlayer().getPosition()) > 300) {
            Vector2 move = this.getMoveToPlayer(deltaTime);
            this.getPosition().x += move.x;
            this.getPosition().y += move.y;
        }
        this.otocitSprite();
    }

    /**
     * Metóda na vykonanie útoku vampíra.
     * Vampír útočí na hráča a spôsobuje mu smrť.
     * @param deltaTime Čas od posledného snímku
     * @param batch SpriteBatch pre vykreslenie
     */
    @Override
    public void attack(float deltaTime, SpriteBatch batch) {
        this.cooldown -= deltaTime;
        if (this.getLengthFromUnit(this.getPlayerPos()) < this.getPlayer().getHitbox() ) {
            this.getPlayer().unitDeath();
        }
    }

    /**
     * Metóda na určenie, či môže vampír spawnovať ďalšie jednotky.
     * @return true, ak môže spawnovať novú jednotku, inak false
     */
    public boolean canSpawn() {
        if (this.cooldown <= 0) {
            this.cooldown = 4;
            return true;
        }
        return false;
    }
}
