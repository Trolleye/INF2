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
	SpriteBatch batch;
	Player player;
	ArrayList<Unit> UnitArrayList = new ArrayList<Unit>();
	EnemySpawner enemySpawner;
	private OrthographicCamera camera;

	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.player = new Player();
		this.UnitArrayList.add(this.player);
		this.enemySpawner = new EnemySpawner(this.player, this.UnitArrayList);
		this.camera = new OrthographicCamera(1280, 720);
		this.camera.setToOrtho(false);
	}

	@Override
	public void render () {
		this.batch.begin();
		this.camera.position.set(this.player.getPlayerPos().x, this.player.getPlayerPos().y, 0);
		this.camera.update();
		ScreenUtils.clear(0, 0, 0, 0);
	if (Gdx.input.isKeyPressed(Input.Keys.P)) {
				this.enemySpawner.spawn();
	}
		if (Gdx.input.isKeyPressed(Input.Keys.O)) {
			this.UnitArrayList.clear();
		}
		for (Unit unit : this.UnitArrayList) {
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
