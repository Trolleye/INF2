package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bat extends Enemy {
    public Bat(Player player, float x, float y) {
        super(new Texture("bat.png"), x, y, 170, player, 30);
    }

    @Override
    public void attack(float deltaTime, SpriteBatch batch) {
        if (this.getLengthFromUnit(this.getPlayerPos()) < this.getPlayer().getHitbox()) {
            this.getPlayer().unitDeath();
        }
    }
}
