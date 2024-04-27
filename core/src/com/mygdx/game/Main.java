package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
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
		this.UnitArrayList.add(player);
		this.enemySpawner = new EnemySpawner(player, UnitArrayList);
		this.camera = new OrthographicCamera(1280, 720);
		this.camera.setToOrtho(false);
	}

	@Override
	public void render () {
		batch.begin();
		camera.position.set(player.getPlayerPos().x, player.getPlayerPos().y, 0);
		camera.update();
		ScreenUtils.clear(0, 0, 0, 0);
		UnitUpdater.update(batch, UnitArrayList);
		batch.setProjectionMatrix(camera.combined);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
