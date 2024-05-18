package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.exceptions.WriteHighScoreException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScoreCounter {
    private final BitmapFont text;
    private final String highScoreFilePath;
    private  int score = 0;

    public ScoreCounter() {
        this.text = new BitmapFont();
        this.highScoreFilePath = "highscore.txt";
    }

    public String getHighScore() {
        Path path = Paths.get(this.highScoreFilePath);
        try {
            if (Files.exists(path)) {
                String content = new String(Files.readAllBytes(path));
                return content.trim();
            } else {
                return "0";
            }
        } catch (IOException e) {
            return "0";
        }
    }

    public void draw(SpriteBatch batch, float x, float y) {
        this.text.draw(batch, "Score: " + this.score, x, y);
    }

    public void gainScore() {
        this.score++;
    }

    public void writeHighScore() throws WriteHighScoreException {
        Path path = Paths.get(this.highScoreFilePath);

        try {
            if (Files.exists(path)) {
                String content = new String(Files.readAllBytes(path));
                int highScore = Integer.parseInt(content.trim());

                if (this.score > highScore) {
                    Files.write(path, String.valueOf(this.score).getBytes());
                }
            } else {
                Files.write(path, String.valueOf(this.score).getBytes());
            }
        } catch (IOException e) {
            throw new WriteHighScoreException();
        }
    }
}

