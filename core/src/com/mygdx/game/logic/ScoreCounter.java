package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.exceptions.WriteHighScoreException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * Trieda ScoreCounter slúži na sledovanie a manipuláciu so skóre hry.
 */
public class ScoreCounter {
    private final BitmapFont currentScore;
    private final BitmapFont highScore;
    private final String highScoreFilePath;
    private  int score = 0;

    /**
     * Konštruktor triedy ScoreCounter.
     * Vytvára novú inštanciu ScoreCounter s vytvoreným textovým fontom a prednastavenou cestou k súboru s najvyšším skóre.
     */
    public ScoreCounter() {
        this.currentScore = new BitmapFont();
        this.highScore = new BitmapFont();
        this.highScoreFilePath = "highscore.txt";
    }

    /**
     * Metóda pre získanie najvyššieho dosiahnutého skóre zo súboru.
     * @return Textová reprezentácia najvyššieho skóre. Ak súbor neexistuje alebo nastane chyba pri čítaní, vráti sa "0".
     */
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
    /**
     * Metóda pre vykreslenie aktuálneho skóre na obrazovku.
     * @param batch SpriteBatch na kreslenie
     * @param x X-ová pozícia vykreslenia skóre
     * @param y Y-ová pozícia vykreslenia skóre
     */

    public void draw(SpriteBatch batch, float x, float y) {
        this.currentScore.draw(batch, "Score: " + this.score, x, y);
        this.highScore.draw(batch, "Highscore: " + this.getHighScore(), x + 1, y - 16);
    }

    /**
     * Metóda pre zvýšenie aktuálneho skóre o jedna.
     */
    public void gainScore() {
        this.score++;
    }

    /**
     * Metóda pre zápis aktuálneho skóre do súboru, ak je vyššie ako najvyššie dosiahnuté skóre.
     * @throws WriteHighScoreException Ak sa vyskytne chyba pri zápise do súboru
     */
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

