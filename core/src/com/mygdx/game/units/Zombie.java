package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;

public class Zombie extends Enemy {
    public Zombie(Player player, float x, float y) {
        super(new Texture("zombie.png"), x, y, 150, player);
    }
}
