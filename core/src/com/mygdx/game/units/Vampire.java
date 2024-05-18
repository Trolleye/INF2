package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Vampire extends Enemy {
    private float cooldown = 4;
    public Vampire(Player player, float x, float y) {
        super(new Texture(  "vampire.png"), x, y, 200, player, 60);
    }

    @Override
    void update(float deltaTime) {
        if (this.getLengthFromUnit(this.getPlayer().getPosition()) > 300) {
            Vector2 move = this.getMoveToPlayer(deltaTime);
            this.getPosition().x += move.x;
            this.getPosition().y += move.y;
        }
        this.otocitSprite();
    }

    @Override
    void attack(float deltaTime, SpriteBatch batch) {
        if (this.getLengthFromUnit(this.getPlayerPos()) <= 300) {
            if (this.cooldown <= 0) {
                this.cooldown = 4;
            }
            this.cooldown -= deltaTime;
        }
        if (this.getLengthFromUnit(this.getPlayerPos()) < this.getPlayer().getHitbox() ) {
            this.getPlayer().unitDeath();
        }
    }

    public boolean canSpawn() {
        return this.cooldown <= 0;
    }
}
