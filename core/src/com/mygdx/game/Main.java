package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.units.Player;
import com.mygdx.game.units.Player2;
import com.mygdx.game.units.Zombie;
import org.w3c.dom.Text;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Player player;
	Player2 player2;
	Zombie zombie;
	Texture img2;
	OrthographicCamera camera;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("hero.png");
		player = new Player(img);
		img2 = new Texture("zombie.png");
		player2 = new Player2(img2);
		zombie = new Zombie();
//		camera = new OrthographicCamera(1280, 720);
//		camera.update();
//		camera.setToOrtho(false);
	}

	@Override
	public void render () {
		batch.begin();
//		camera.position.set(player.getX(), player.getY(), 0);
//		camera.update();
		ScreenUtils.clear(1, 0, 0, 1);
		player.vykresli(batch);
		player2.vykresli(batch);
		zombie.vykresli(batch);
//		batch.setProjectionMatrix(camera.combined);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
