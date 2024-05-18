package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Logic.UnitsManager;
import com.mygdx.game.units.Player;
import com.mygdx.game.units.Unit;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Player player;
    private final ArrayList<Unit> unitArrayList = new ArrayList<Unit>();
    private UnitsManager unitsManager;
    private OrthographicCamera camera;

    @Override
    public void create () {
        this.batch = new SpriteBatch();
        this.player = new Player(this.unitArrayList);
        this.unitArrayList.add(this.player);
        this.unitsManager = new UnitsManager(this.player, this.unitArrayList);
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
            for (Unit unit : this.unitArrayList) {
                unit.vykresli(this.batch);
            }
            this.batch.setProjectionMatrix(this.camera.combined);
        } else {
            BitmapFont text = new BitmapFont();
            text.getData().setScale(4.0f);
            text.draw(this.batch, "YOU LOST", this.player.getPozicia().x - 120, this.player.getPozicia().y);
        }
        this.batch.end();
    }

    @Override
    public void dispose () {
        this.batch.dispose();
    }
}
