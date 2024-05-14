package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy extends Unit{
    private final Player player;
    private final int speed;
    private int otocenie = 1;
    private final Vector2 movement = new Vector2();
    private final Vector2 posOfPlayer = new Vector2();
    public Enemy(Texture img, float x, float y, int speed, Player player) {
        super(img, x, y);
        this.player = player;
        this.speed = speed;
    }

    @Override
    void pohyb(float deltaTime){
        posOfPlayer.x = player.getPlayerPos().x;
        this.posOfPlayer.y = player.getPlayerPos().y;

        Vector2 directionOfMove = posOfPlayer.sub(this.getPosition()).nor();

        float moveDistance = speed * deltaTime;


        movement.x = directionOfMove.x * moveDistance;
        movement.y = directionOfMove.y * moveDistance;

        this.getPosition().x += movement.x;
        this.getPosition().y += movement.y;
        this.otocitSprite();
    }

    protected Vector2 getMovement() {
        return movement;
    }

    protected int getSpeed(){
        return this.speed;
    }
    protected Vector2 getPosOfPlayer() {
        return posOfPlayer;
    }

    protected void otocitSprite(){
        if (getPlayerPos().x < getPosition().x && this.otocenie==1){
            getSprite().flip(true,false);
            this.otocenie = 0;
        }
        if (getPlayerPos().x > getPosition().x && this.otocenie==0){
            getSprite().flip(true,false);
            this.otocenie = 1;
        }
    }
    protected Vector2 getPlayerPos() {
        return this.player.getPosition();
    }
}
