package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.exceptions.WriteHighScoreException;
import com.mygdx.game.logic.ScoreCounter;
import com.mygdx.game.logic.UnitsManager;
import com.mygdx.game.units.Player;
import com.mygdx.game.units.Unit;

import java.util.ArrayList;

public class GameInitialiser {
    private Player player;
    private final ArrayList<Unit> unitArrayList = new ArrayList<>();
    private UnitsManager unitsManager;
    private OrthographicCamera camera;
    private ScoreCounter scoreCounter;

    public GameInitialiser() {
        this.player = new Player(this.unitArrayList);
        this.unitArrayList.add(this.player);
        this.scoreCounter = new ScoreCounter();
        this.unitsManager = new UnitsManager(this.player, this.unitArrayList, this.scoreCounter);
        this.camera = new OrthographicCamera(1280, 720);
        this.camera.setToOrtho(false);
    }

    public void update(SpriteBatch batch) {
        if (!this.player.isDead()) {
            this.camera.position.set(this.player.getPlayerPos().x, this.player.getPlayerPos().y, 0);
            this.camera.update();
            this.unitsManager.manageUnits(Gdx.graphics.getDeltaTime());
            ScreenUtils.clear(0, 0, 0, 0);
            this.scoreCounter.draw(batch, this.player.getPozicia().x - (float)Gdx.graphics.getWidth() / 2 + 10, this.player.getPozicia().y + (float)Gdx.graphics.getHeight() / 2 - 10);
            batch.setProjectionMatrix(this.camera.combined);
            for (Unit unit : this.unitArrayList) {
                unit.vykresli(batch);
            }
        } else {
            BitmapFont text = new BitmapFont();
            text.getData().setScale(4.0f);
            text.draw(batch, "YOU LOST", this.player.getPozicia().x - 120, this.player.getPozicia().y);
            BitmapFont text2 = new BitmapFont();
            text2.getData().setScale(4.0f);
            text2.draw(batch, "Press P to Start Again", this.player.getPozicia().x - 250, this.player.getPozicia().y - 300);
            BitmapFont score = new BitmapFont();
            score.getData().setScale(2.0F);
            try {
                this.scoreCounter.writeHighScore();
            } catch (WriteHighScoreException e) {
                throw new RuntimeException(e);
            }
            score.draw(batch, "Your Highscore: " + this.scoreCounter.getHighScore(), this.player.getPozicia().x - 90, this.player.getPozicia().y - 50);
        }
    }
}
