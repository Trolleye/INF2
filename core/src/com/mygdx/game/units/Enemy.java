package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy extends Unit{
    private Player player;
    private int otocenie = 1;
    public Enemy(Texture img, int x, int y, Player player) {
        super(img, x, y);
        this.player = player;
    }


    protected void otocitSprite(){
        if (getPlayerPos().x < getPozicia().x && this.otocenie==1){
            getSprite().flip(true,false);
            this.otocenie = 0;
        }
        if (getPlayerPos().x > getPozicia().x && this.otocenie==0){
            getSprite().flip(true,false);
            this.otocenie = 1;
        }
    }
    protected Vector2 getPlayerPos() {
        return this.player.getPlayerPos();
    }
}
