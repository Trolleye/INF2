package com.mygdx.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Zombie extends Enemy{
    public Zombie(Texture img) {
        super(img, 0, 0);
    }

    @Override
    void pohyb(float deltaTime) {

    }

    @Override
    public void vykresli(SpriteBatch batch) {
        pohyb(Gdx.graphics.getDeltaTime());
        batch.draw(getSprite(), getPozicia().x, getPozicia().y);
    }

}
