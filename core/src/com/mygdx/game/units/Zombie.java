package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;

public class Zombie extends Enemy{

    private float rychlost = 200;
    public Zombie(Player player) {
        super(new Texture("zombie.png"), 0, 0, player);
    }

    @Override
    void pohyb(float deltaTime) {
        otocitSprite();
    }
}
