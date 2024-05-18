package com.mygdx.game;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.projectiles.PlayerProjectile;
import com.mygdx.game.units.*;

import java.util.ArrayList;
import java.util.Random;

public class UnitsManager {

    private final Player player;
    private final ArrayList<Unit> unitArrayList;
    public UnitsManager(Player player, ArrayList<Unit> unitArrayList) {
        this.player = player;
        this.unitArrayList = unitArrayList;
        this.spawn();
    }

    public void spawn() {
        Vector2 randomVector = this.generateRandomVector(100, 500);
        this.chooseRandomEnemy(this.player, this.player.getPlayerPos().x + randomVector.x, this.player.getPlayerPos().y + randomVector.y);
    }

    private Vector2 generateRandomVector(float innerRadius, float outerRadius) {
        Random rand = new Random();

        double x = (rand.nextDouble() * (outerRadius * 2)) - outerRadius;
        double y = (rand.nextDouble() * (outerRadius * 2)) - outerRadius;

        double distance = Math.sqrt(x * x + y * y);

        if (distance >= innerRadius && distance <= outerRadius) {
            return new Vector2((float)x, (float)y);
        } else {
            // Ak by nebol v rozmedzi tak sa vykona znova
            return this.generateRandomVector(innerRadius, outerRadius);
        }
    }

    private void chooseRandomEnemy(Player player, float x, float y) {
        Random rand = new Random();
        switch (rand.nextInt(2)) {
            case 0: this.unitArrayList.add(new Zombie(player, x, y));
                    break;
            case 1: this.unitArrayList.add(new Vampire(player, x, y));
                    break;
        }
    }

    public void manageUnits() {
        this.unitArrayList.removeIf(Unit::isDead);
        this.unitSpawner();
    }

    private void unitSpawner() {
        ArrayList<Unit> unitsToAdd = new ArrayList<>();

        for (Unit unit : this.unitArrayList) {
            if (unit instanceof Vampire && ((Vampire)unit).canSpawn()) {
                unitsToAdd.add(new Bat(this.player, unit.getPozicia().x, unit.getPozicia().y));
            }
        }

        this.unitArrayList.addAll(unitsToAdd);
    }
}
