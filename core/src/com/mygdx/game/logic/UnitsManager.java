package com.mygdx.game.logic;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.units.Bat;
import com.mygdx.game.units.Mage;
import com.mygdx.game.units.Player;
import com.mygdx.game.units.Unit;
import com.mygdx.game.units.Vampire;
import com.mygdx.game.units.Zombie;

import java.util.ArrayList;
import java.util.Random;

/**
 * Trieda UnitsManager spravuje všetky jednotky v hre a ich interakcie.
 */
public class UnitsManager {

    private final Player player;
    private final ArrayList<Unit> unitArrayList;
    private final ScoreCounter scoreCounter;
    private float cooldown = 1;

    /**
     * Konštruktor triedy UnitsManager.
     * @param player Hráč
     * @param unitArrayList Zoznam jednotiek
     * @param scoreCounter Počítadlo skóre
     */
    public UnitsManager(Player player, ArrayList<Unit> unitArrayList, ScoreCounter scoreCounter) {
        this.player = player;
        this.unitArrayList = unitArrayList;
        this.scoreCounter = scoreCounter;
        this.spawn();
    }

    /**
     * Spravuje jednotky v hre v každom snímku.
     * @param deltaTime Časový rozdiel od posledného snímku
     */
    public void manageUnits(float deltaTime) {
        for (Unit unit : this.unitArrayList) {
            if (unit.isDead()) {
                this.scoreCounter.gainScore();
            }
        }
        this.unitArrayList.removeIf(Unit::isDead);
        this.unitSpawner();
        this.spawnEnemyOnTimer(deltaTime);
    }


    private void spawn() {
        Vector2 randomVector = this.generateRandomVector(300, 800);
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
        switch (rand.nextInt(3)) {
            case 0: this.unitArrayList.add(new Zombie(player, x, y));
                    break;
            case 1: this.unitArrayList.add(new Vampire(player, x, y));
                    break;
            case 2: this.unitArrayList.add(new Mage(player, x, y));
                    break;
        }
    }


    private void spawnEnemyOnTimer(float deltaTime) {
        this.cooldown -= deltaTime;
        if (this.cooldown <= 0) {
            this.spawn();
            this.cooldown = 0.5F;
        }
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
