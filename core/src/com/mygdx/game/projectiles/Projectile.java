package com.mygdx.game.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Projectile {
    private final Vector2 position;
    private final Sprite sprite;
    private final Vector2 targetPosition;
    private final float speed;
    private final Vector2 beginningPosition;


    public Projectile(Texture img, Vector2 targetPosition, Vector2 startingPosition, float speed) {
        this.sprite = new Sprite(img);
        this.speed = speed;
        this.targetPosition = new Vector2(targetPosition);
        this.position = new Vector2(startingPosition);
        this.beginningPosition = new Vector2(startingPosition);
    }

    private void pohyb(float deltaTime) {
        Vector2 moveVector = this.getMoveToTarget(deltaTime);
        this.position.add(moveVector);
    }

    public void vykresli(SpriteBatch batch) {
        this.checkNearEnemy();
        this.pohyb(Gdx.graphics.getDeltaTime());
        batch.draw(this.getSprite(), this.position.x, this.position.y);
    }
    protected Sprite getSprite() {
        return this.sprite;
    }

    protected Vector2 getMoveToTarget(float deltaTime) {
        Vector2 positionOfTarget = new Vector2(this.targetPosition.x, this.targetPosition.y);
        Vector2 directionOfMove = new Vector2(positionOfTarget).sub(this.beginningPosition).nor();
        return directionOfMove.scl(this.speed * deltaTime);
    }
    protected Vector2 getPosition() {
        return this.position;
    }
    abstract void checkNearEnemy();
}