package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.units.Player;
import com.mygdx.game.units.Zombie;

import java.util.ArrayList;

public class EnemySpawner {

    private Texture zombieImage;
    private Player player;
    private ArrayList unitArrayList;

    public EnemySpawner(Player player, ArrayList unitArrayList) {
        this.player = player;
        this.unitArrayList = unitArrayList;
        this.zombieImage = new Texture("zombie.png");
        Zombie zombie = new Zombie(zombieImage);
        unitArrayList.add(zombie);
    }
}
