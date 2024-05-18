package com.mygdx.game;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument

/**
 * Hlavná trieda DesktopLauncher slúži na spustenie hry na desktopových platformách.
 * Konfiguruje okno hry a inicializuje jej spustenie.
 */
public class DesktopLauncher {

    /**
     * Metóda main je vstupný bod pre spustenie hry.
     * Konfiguruje parametre okna a spúšťa hru.
     * @param arg Argumenty príkazového riadku (nevyužívané)
     */
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setResizable(false);
        config.setWindowedMode(1800, 900);
        config.setForegroundFPS(60);
        config.setTitle("Hero Survivors");
        new Lwjgl3Application(new Main(), config);
    }
}
