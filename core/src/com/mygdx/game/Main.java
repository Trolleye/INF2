package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.units.Player;
import com.mygdx.game.units.Zombie;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture playerImage;
	Player player;
	Zombie zombie;
	Texture zombieImage;
	OrthographicCamera camera;

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.playerImage = new Texture("hero.png");
		this.zombieImage = new Texture("zombie.png");
		player = new Player(playerImage);

//		camera = new OrthographicCamera(1280, 720);
//		camera.update();
//		camera.setToOrtho(false);
	}

	@Override
	public void render () {
		batch.begin();
//		camera.position.set(player.getX(), player.getY(), 0);
//		camera.update();
	//	ScreenUtils.clear(1, 0, 0, 1);
		player.vykresli(batch);
//		batch.setProjectionMatrix(camera.combined);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		zombieImage.dispose();
		playerImage.dispose();
	}
}
