package com.mygdx.game.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Abstraktná trieda Projectile predstavuje základ pre všetky projektilové objekty v hre.
 */
public abstract class Projectile {
    private final Vector2 position;
    private final Sprite sprite;
    private final Vector2 targetPosition;
    private final float speed;
    private final Vector2 beginningPosition;

    /**
     * Konštruktor triedy Projectile.
     * @param img Textúra projektilu
     * @param targetPosition Cieľová pozícia projektilu
     * @param startingPosition Počiatočná pozícia projektilu
     * @param speed Rýchlosť pohybu projektilu
     */
    public Projectile(Texture img, Vector2 targetPosition, Vector2 startingPosition, float speed) {
        this.sprite = new Sprite(img);
        this.speed = speed;
        this.targetPosition = new Vector2(targetPosition);
        this.position = new Vector2(startingPosition);
        this.beginningPosition = new Vector2(startingPosition);
    }

    /**
     * Aktualizuje stav projektilu a vykreslí ho na obrazovku.
     * @param batch SpriteBatch pre vykreslenie
     */
    public void update(SpriteBatch batch) {
        this.checkNearEnemy();
        this.move(Gdx.graphics.getDeltaTime());
        batch.draw(this.getSprite(), this.position.x, this.position.y);
    }
    private void move(float deltaTime) {
        Vector2 moveVector = this.getMoveToTarget(deltaTime);
        this.position.add(moveVector);
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
    protected abstract void checkNearEnemy();
}