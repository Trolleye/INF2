package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Abstraktná trieda Enemy predstavuje základnú implementáciu nepriateľských jednotiek v hre.
 * Táto trieda poskytuje základné metódy a vlastnosti pre všetky typy nepriateľských jednotiek.
 */
public abstract class Enemy extends Unit {
    private final Player player;
    private final int speed;
    private int otocenie = 1;
    private final Vector2 movement = new Vector2();
    private final Vector2 posOfPlayer = new Vector2();

    /**
     * Konštruktor triedy Enemy.
     * @param img Textúra nepriateľa
     * @param x X-ová pozícia nepriateľa na mape
     * @param y Y-ová pozícia nepriateľa na mape
     * @param speed Rýchlosť pohybu nepriateľa
     * @param player Referencia na hráča
     * @param hitbox Veľkosť hitboxu nepriateľa
     */
    public Enemy(Texture img, float x, float y, int speed, Player player, int hitbox) {
        super(img, x, y, hitbox);
        this.player = player;
        this.speed = speed;
    }

    /**
     * Metóda na aktualizáciu stavu nepriateľa v každom snímku.
     * @param deltaTime Čas od posledného snímku
     */
    @Override
    public void update(float deltaTime) {
        this.getPosition().x += this.getMoveToPlayer(deltaTime).x;
        this.getPosition().y += this.getMoveToPlayer(deltaTime).y;
        this.otocitSprite();
    }



    protected Vector2 getMoveToPlayer(float deltaTime) {
        this.posOfPlayer.x = this.player.getPlayerPos().x;
        this.posOfPlayer.y = this.player.getPlayerPos().y;
        Vector2 directionOfMove = this.posOfPlayer.sub(this.getPosition()).nor();
        float moveDistance = this.speed * deltaTime;
        this.movement.x = directionOfMove.x * moveDistance;
        this.movement.y = directionOfMove.y * moveDistance;
        return this.movement;
    }


    protected void otocitSprite() {
        if (this.getPlayerPos().x < this.getPosition().x && this.otocenie == 1) {
            this.getSprite().flip(true, false);
            this.otocenie = 0;
        }
        if (this.getPlayerPos().x > this.getPosition().x && this.otocenie == 0) {
            this.getSprite().flip(true, false);
            this.otocenie = 1;
        }
    }

    protected Vector2 getPlayerPos() {
        return this.player.getPosition();
    }

    protected Unit getPlayer() {
        return this.player;

    }
}
