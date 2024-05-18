package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.projectiles.EnemyProjectile;

import java.util.ArrayList;

public class Mage extends Enemy {
    private final ArrayList<EnemyProjectile> projectiles = new ArrayList<>();
    private float cooldown = 1.5F;
    public Mage(Player player, float x, float y) {
        super(new Texture("mage.png"), x, y, 130, player, 43);
    }

    @Override
    public void update(float deltaTime) {
        if (this.getLengthFromUnit(this.getPlayer().getPosition()) > 500) {
            Vector2 move = this.getMoveToPlayer(deltaTime);
            this.getPosition().x += move.x;
            this.getPosition().y += move.y;

        }
        this.otocitSprite();
    }

    @Override
    public void attack(float deltaTime, SpriteBatch batch) {
        if (this.getLengthFromUnit(this.getPlayer().getPosition()) <= 500) {
            if (this.cooldown < 0) {
                this.projectiles.add(new EnemyProjectile(this.getPlayerPos(), this.getPosition(), (Player)this.getPlayer()));
                this.cooldown = 1.5F;
            }
        }
        for (EnemyProjectile projectile : this.projectiles) {
            projectile.update(batch);
        }
        this.cooldown -= deltaTime;
    }
}
