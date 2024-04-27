package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;

public class Vampire extends Enemy{

    private float rychlost = 200;
    public Vampire(Player player, float x, float y) {
        super(new Texture("vampire.png"), x, y, player);
    }

    @Override
    void pohyb(float deltaTime) {
        otocitSprite();
    }
}
