package com.mygdx.game;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.units.Player;
import com.mygdx.game.units.Unit;
import com.mygdx.game.units.Vampire;
import com.mygdx.game.units.Zombie;

import java.util.ArrayList;
import java.util.Random;

public class EnemySpawner {

    private final Player player;
    private final ArrayList<Unit> unitArrayList;
    public EnemySpawner(Player player, ArrayList<Unit> unitArrayList) {
        this.player = player;
        this.unitArrayList = unitArrayList;
        this.spawn();
    }

    public void spawn() {
        Vector2 nahodnyVektor = this.generujNahodnyVektor(100, 500);
        this.spawnRandomEnemy(this.player, this.player.getPlayerPos().x + nahodnyVektor.x, this.player.getPlayerPos().y + nahodnyVektor.y);
    }

    private Vector2 generujNahodnyVektor(float innerRadius, float outerRadius) {
        Random rand = new Random();

        double x = (rand.nextDouble() * (outerRadius * 2)) - outerRadius;
        double y = (rand.nextDouble() * (outerRadius * 2)) - outerRadius;

        double distance = Math.sqrt(x * x + y * y);

        if (distance >= innerRadius && distance <= outerRadius) {
            return new Vector2((float)x, (float)y);
        } else {
            // Ak by nebol v rozmedzi tak sa vykona znova
            return this.generujNahodnyVektor(innerRadius, outerRadius);
        }
    }

    private void spawnRandomEnemy(Player player, float x, float y) {
        Random rand = new Random();
        switch (rand.nextInt(2)) {
            case 0: this.unitArrayList.add(new Zombie(player, x, y));
                    break;
            case 1: this.unitArrayList.add(new Vampire(player, x, y));
                    break;
        }
    }
}
