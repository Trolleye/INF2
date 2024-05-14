package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.units.Unit;

import java.util.ArrayList;

public class UnitUpdater {

    public static void update(SpriteBatch batch, ArrayList<Unit> unitArrayList) {
        for (Unit unit : unitArrayList) {
            unit.vykresli(batch);
        }
    }
}
