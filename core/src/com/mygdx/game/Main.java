package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
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
        this.camera.position.set(this.player.getPlayerPos().x, this.player.getPlayerPos().y, 0);
        this.camera.update();
        this.unitsManager.manageUnits(Gdx.graphics.getDeltaTime());
        ScreenUtils.clear(0, 0, 0, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            this.unitsManager.spawn();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.O)) {
            this.unitArrayList.clear();
        }
        for (Unit unit : this.unitArrayList) {
            unit.vykresli(this.batch);
        }
        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.end();
    }

    @Override
    public void dispose () {
        this.batch.dispose();
    }
}
