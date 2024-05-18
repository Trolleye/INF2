package com.mygdx.game.projectiles;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.units.Enemy;
import com.mygdx.game.units.Unit;

import java.util.ArrayList;

public class PlayerProjectile extends Projectile {
    private final ArrayList<Unit> unitArrayList;
    private boolean enemyHit = false;

    public PlayerProjectile(Vector2 targetPosition, Vector2 playerPosition, ArrayList<Unit> unitArrayList) {
        super(new Texture("playerProjectile.png"), targetPosition, playerPosition, 300);
        this.unitArrayList = unitArrayList;
    }

    public boolean isEnemyHit() {
        return this.enemyHit;
    }
    @Override
    protected void checkNearEnemy() {
        for (int i = 1; i < this.unitArrayList.size(); i++) {
            if (this.unitArrayList.get(i) instanceof Enemy) {
                if (this.unitArrayList.get(i).getLengthFromUnit(this.getPosition()) < this.unitArrayList.get(i).getHitbox()) {
                    this.unitArrayList.get(i).unitDeath();
                    this.enemyHit = true;
                }
            }
        }
    }

}
