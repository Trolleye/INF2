package com.mygdx.game.projectiles;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.units.Player;

/**
 * Trieda EnemyProjectile predstavuje projektil vystrelený nepriateľskou jednotkou.
 * Tento projektil mieri na miesto, kde sa hráč pri vytvorení inštancie nachádzal a po dopade na hráča spôsobí jeho smrť.
 */
public class EnemyProjectile extends Projectile {
    private final Player player;

    /**
     * Konštruktor triedy EnemyProjectile.
     * @param targetPosition Pozícia, na ktorú sa projektil snaží dopadnúť
     * @param startingPosition Počiatočná pozícia projektilu
     * @param player Hráč, ktorý je cieľom tohto projektilu
     */
    public EnemyProjectile(Vector2 targetPosition, Vector2 startingPosition, Player player) {
        super(new Texture("enemyProjectile.png"), targetPosition, startingPosition, 300);
        this.player = player;
    }


    @Override
    protected void checkNearEnemy() {
        if (this.player.getLengthFromUnit(this.getPosition()) < 20) {
            this.player.unitDeath();
        }
    }
}
