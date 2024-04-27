package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy extends Unit{
    private Player player;
    public Enemy(Texture img, int x, int y, Player player) {
        super(img, x, y);
        this.player = player;
    }

    protected Vector2 getPlayerPos() {
        return this.player.getPlayerPos();
    }
}
