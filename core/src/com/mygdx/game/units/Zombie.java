package com.mygdx.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Zombie implements Enemy {
    private Vector2 pozicia;
    private Sprite sprite;
    private int hp;
    private float rychlost = 200;

    public Zombie() {
        sprite = new Sprite(new Texture("zombie.png"));
        sprite.setScale(3);
        pozicia = new Vector2(Gdx.graphics.getWidth()/2, sprite.getScaleY()*sprite.getHeight()/2);
    }

    @Override
    public void zautoc() {

    }

    @Override
    public void dostanDmg(int damage) {

    }

    @Override
    public boolean jeNazive() {
        if (hp > 0){
            return true;
        }
        else {return false;}
    }

    @Override
    public void pohyb(float deltaTime) {

    }

    @Override
    public void vykresli(SpriteBatch batch) {
        sprite.setPosition(pozicia.x, pozicia.y);
        sprite.draw(batch);
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }
}