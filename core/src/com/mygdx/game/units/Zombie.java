package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;

public class Zombie extends Enemy{

    private float rychlost = 200;
    public Zombie(Player player, float x, float y) {
        super(new Texture("zombie.png"), x, y, player);
    }

    @Override
    void pohyb(float deltaTime) {
        otocitSprite();
    }
}
