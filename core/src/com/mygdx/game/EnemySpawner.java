package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.units.Player;
import com.mygdx.game.units.Unit;
import com.mygdx.game.units.Zombie;

import java.util.ArrayList;

public class EnemySpawner {

    private final Texture zombieImage;

    private final Player player;
    private  ArrayList<Unit> unitArrayList;

    public EnemySpawner(Player player, ArrayList unitArrayList) {
        this.player = player;
        this.unitArrayList = unitArrayList;
        this.zombieImage = new Texture("zombie.png");
        unitArrayList.add(new Zombie(player));
    }
}
