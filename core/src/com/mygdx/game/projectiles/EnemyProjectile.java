package com.mygdx.game.projectiles;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.units.Player;

public class EnemyProjectile extends Projectile {
    private final Player player;

    public EnemyProjectile(Vector2 targetPosition, Vector2 startingPosition, Player player) {
        super(new Texture("enemyProjectile.png"), targetPosition, startingPosition, 300);
        this.player = player;
    }

    @Override
    protected void checkNearEnemy() {
        if (this.player.getLengthFromUnit(this.getPosition()) < this.player.getHitbox()) {
            this.player.unitDeath();
        }
    }
}
