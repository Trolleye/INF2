package com.mygdx.game.units;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class PlayerProjectile extends Projectile {
    public PlayerProjectile(Vector2 cursorPosition, Vector2 playerPosition, Texture unitTexture) {
        super(new Texture("playerProjectile.png"), cursorPosition, playerPosition, unitTexture, 300);
    }
}
