package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Vampire extends Enemy{

    public Vampire(Player player, float x, float y) {
        super(new Texture(  "vampire.png"), x, y, 200, player);
    }

    @Override
    void pohyb(float deltaTime) {
        getPosOfPlayer().x = getPlayerPos().x;
        getPosOfPlayer().y = getPlayerPos().y;
        float vzdialenost = getPosOfPlayer().sub(this.getPosition()).len();

        Vector2 directionOfMove = getPosOfPlayer().sub(this.getPosition()).nor();
        if (vzdialenost > 300){
            float moveDistance = getSpeed() * deltaTime;
            getMovement().x = directionOfMove.x * moveDistance;
            getMovement().y = directionOfMove.y * moveDistance;

            this.getPosition().x += getMovement().x;
            this.getPosition().y += getMovement().y;
        }
        otocitSprite();
    }
}
