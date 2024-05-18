package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.logic.ScoreCounter;
import com.mygdx.game.logic.UnitsManager;
import com.mygdx.game.exceptions.WriteHighScoreException;
import com.mygdx.game.units.Player;
import com.mygdx.game.units.Unit;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Player player;
    private final ArrayList<Unit> unitArrayList = new ArrayList<>();
    private UnitsManager unitsManager;
    private OrthographicCamera camera;
    private ScoreCounter scoreCounter;

    @Override
    public void create () {
        this.batch = new SpriteBatch();
        this.player = new Player(this.unitArrayList);
        this.unitArrayList.add(this.player);
        this.scoreCounter = new ScoreCounter();
        this.unitsManager = new UnitsManager(this.player, this.unitArrayList, this.scoreCounter);
        this.camera = new OrthographicCamera(1280, 720);
        this.camera.setToOrtho(false);
    }

    @Override
    public void render () {
        this.batch.begin();
        if (!this.player.isDead()) {
            this.camera.position.set(this.player.getPlayerPos().x, this.player.getPlayerPos().y, 0);
            this.camera.update();
            this.unitsManager.manageUnits(Gdx.graphics.getDeltaTime());
            ScreenUtils.clear(0, 0, 0, 0);
            this.scoreCounter.draw(this.batch, this.player.getPozicia().x - 890, this.player.getPozicia().y + 435);

            for (Unit unit : this.unitArrayList) {
                unit.vykresli(this.batch);
            }

            this.batch.setProjectionMatrix(this.camera.combined);
        } else {
            BitmapFont text = new BitmapFont();
            text.getData().setScale(4.0f);
            text.draw(this.batch, "YOU LOST", this.player.getPozicia().x - 120, this.player.getPozicia().y);
            BitmapFont score = new BitmapFont();
            score.getData().setScale(2.0F);
            try {
                this.scoreCounter.writeHighScore();
            } catch (WriteHighScoreException e) {
                throw new RuntimeException(e);
            }
            score.draw(this.batch, "Your Highscore: " + this.scoreCounter.getHighScore(), this.player.getPozicia().x - 80, this.player.getPozicia().y - 50);
        }
        this.batch.end();
    }

    @Override
    public void dispose () {
        this.batch.dispose();
    }
}
