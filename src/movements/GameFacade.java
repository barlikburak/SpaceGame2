/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movements;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author burak
 */
public class GameFacade extends AnimationTimer {

    private final Game game;
    int i = 0;

    public GameFacade(Game game) {
        this.game = game;
    }

    @Override
    public void handle(long now) {
        if (game.initializeListeners()) {//oyun bittiğinde oyun sonlandırma metodu çağırılır.
            stop();
        }
    }

    /**
     * Oyunu sonlandırır.
     */
    @Override
    public void stop() {
        super.stop();
        game.stopGame();
        game.savePuan();
    }

    /**
     * Oyunu başlatır.
     */
    @Override
    public void start() {
        game.startBooting();
        game.createScreen();
        game.init();
        super.start();
    }

    public void keyPressed(KeyEvent event) {
        game.onKeyPressed(event);
    }

    public void keyRelasedKey(KeyEvent event) {
        game.onKeyReleased(event);
    }

    public Game getGame() {
        return game;
    }

}
