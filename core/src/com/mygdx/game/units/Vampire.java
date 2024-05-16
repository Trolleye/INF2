package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Vampire extends Enemy {
    public Vampire(Player player, float x, float y) {
        super(new Texture(  "vampire.png"), x, y, 200, player);
    }

    @Override
    void pohyb(float deltaTime) {
        if (this.getLengthFromPlayer() > 300) {
            Vector2 move = this.getMoveToPlayer(deltaTime);
            this.getPosition().x += move.x;
            this.getPosition().y += move.y;
        }
        this.otocitSprite();
    }
}
