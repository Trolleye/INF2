package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.mygdx.game.units.Player;
import com.mygdx.game.units.Unit;
import com.mygdx.game.units.Zombie;

import java.util.ArrayList;
import java.util.Random;

public class EnemySpawner {

    private final Player player;
    private  ArrayList<Unit> unitArrayList;
    private Circle kruhMensi = new Circle(0,0,100);
    private Circle kruhVacsi = new Circle(0,0,200);

    public EnemySpawner(Player player, ArrayList unitArrayList) {
        this.player = player;
        this.unitArrayList = unitArrayList;
        kruhMensi.setPosition(player.getPlayerPos());
        kruhVacsi.setPosition(player.getPlayerPos());
        spawn();
    }

    public void spawn(){
        unitArrayList.add(new Zombie(player, player.getPlayerPos().x+getNahodneX(),player.getPlayerPos().y+getNahodneY()));
    }
    private float getNahodneX(){
        Random r = new Random();
        float x =  (r.nextInt((int) (kruhVacsi.radius - kruhMensi.radius)) + kruhMensi.radius);
        int temp = (Math.random() <= 0.5) ? 1 : 2;
        if (temp==1){
            x=-x;
        }
        return x;
    }

    private float getNahodneY() {
        Random r = new Random();
        float y = (r.nextInt((int) (kruhVacsi.radius - kruhMensi.radius)) + kruhMensi.radius);
        int temp = (Math.random() <= 0.5) ? 1 : 2;
        if (temp == 1) {
            y = -y;
        }
        return y;
    }
}
