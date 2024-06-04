package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Hlavná trieda Main predstavuje aplikáciu a riadi jej beh.
 * Implementuje metódy z rozhrania ApplicationAdapter.
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private GameInitialiser newGame;

    /**
     * Metóda vytvára herný svet a inicializuje potrebné komponenty.
     */
    @Override
    public void create () {
        this.batch = new SpriteBatch();
        this.newGame = new GameInitialiser();
    }

    /**
     * Metóda vykresľuje herný svet a aktualizuje jeho stav v každom snímku.
     */
    @Override
    public void render () {
        this.batch.begin();
        this.newGame.update(this.batch);
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && this.newGame.getPlayerDead()) {
            this.newGame = new GameInitialiser();
        }
        this.batch.end();
    }

    /**
     * Metóda uvoľňuje zdroje v používaných komponentoch.
     */
    @Override
    public void dispose () {
        this.batch.dispose();
    }
}
