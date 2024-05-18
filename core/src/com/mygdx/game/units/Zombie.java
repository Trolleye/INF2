package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Zombie extends Enemy {
    public Zombie(Player player, float x, float y) {
        super(new Texture("zombie.png"), x, y, 150, player, 50);
    }


    @Override
    public void attack(float deltaTime, SpriteBatch batch) {
        if (this.getLengthFromUnit(this.getPlayerPos()) < this.getPlayer().getHitbox() ) {
            this.getPlayer().unitDeath();
        }
    }
}
