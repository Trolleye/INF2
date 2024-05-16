package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy extends Unit {
    private final Player player;
    private final int speed;
    private int otocenie = 1;
    private final Vector2 movement = new Vector2();
    private final Vector2 posOfPlayer = new Vector2();
    private final Vector2 playerPos = new Vector2();

    public Enemy(Texture img, float x, float y, int speed, Player player) {
        super(img, x, y);
        this.player = player;
        this.speed = speed;
    }

    @Override
    void pohyb(float deltaTime) {
        this.getPosition().x += this.getMoveToPlayer(deltaTime).x;
        this.getPosition().y += this.getMoveToPlayer(deltaTime).y;
        this.otocitSprite();
    }

    protected float getLengthFromPlayer() {
        this.playerPos.x = this.getPlayerPos().x;
        this.playerPos.y = this.getPlayerPos().y;
        return this.playerPos.sub(this.getPosition()).len();
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
}
