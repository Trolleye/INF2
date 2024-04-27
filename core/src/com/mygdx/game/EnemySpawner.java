package com.mygdx.game;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.units.Player;
import com.mygdx.game.units.Unit;
import com.mygdx.game.units.Vampire;
import com.mygdx.game.units.Zombie;

import java.util.ArrayList;
import java.util.Random;

public class EnemySpawner {

    private final Player player;
    private  ArrayList<Unit> unitArrayList;
    private Circle kruhMensi = new Circle(0,0,100);
    private Circle kruhVacsi = new Circle(0,0,500);

    public EnemySpawner(Player player, ArrayList unitArrayList) {
        this.player = player;
        this.unitArrayList = unitArrayList;
        kruhMensi.setPosition(player.getPlayerPos());
        kruhVacsi.setPosition(player.getPlayerPos());
        spawn();
    }

    public void spawn(){
        Vector2 nahodnyVektor = generujNahodnyVektor(kruhMensi.radius, kruhVacsi.radius);
        spawnRandomEnemy(player, player.getPlayerPos().x+nahodnyVektor.x,player.getPlayerPos().y+nahodnyVektor.y);
    }

    private Vector2 generujNahodnyVektor(float innerRadius, float outerRadius) {
        Random rand = new Random();

        double x = (rand.nextDouble() * (outerRadius * 2)) - outerRadius;
        double y = (rand.nextDouble() * (outerRadius * 2)) - outerRadius;

        double distance = Math.sqrt(x * x + y * y);

        if (distance >= innerRadius && distance <= outerRadius) {
            return new Vector2((float) x, (float) y);
        }
        else {
            // Ak by nebol v rozmedzi tak sa vykona znova
            return generujNahodnyVektor(innerRadius, outerRadius);
        }
    }

    private void spawnRandomEnemy(Player player, float x, float y){
        Random rand = new Random();
        switch (rand.nextInt(2)){
            case 0: unitArrayList.add(new Zombie(player, x, y));
            case 1: unitArrayList.add(new Vampire(player, x, y));
        }
    }
}
