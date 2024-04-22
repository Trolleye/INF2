package com.mygdx.game.units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Enemy {
    void zautoc();
    void dostanDmg(int damage);
    boolean jeNazive();
    void pohyb(float deltaTime);
    void vykresli(SpriteBatch batch);
    int getX();
    int getY();
}
