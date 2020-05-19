/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movements;

import javafx.util.Duration;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author burak
 */
public class SpaceGameMainPane {

    private boolean isHidden;
    private AnchorPane paneToHide;

    public SpaceGameMainPane() {
        isHidden = false;
        paneToHide = null;
    }

    public SpaceGameMainPane(AnchorPane paneToHide) {
        this.paneToHide = paneToHide;
        isHidden = true;
    }

    /**
     * Parametredeki menüyü animasyon şeklinde ekrana getirmek veya ekrandan
     * çıkarmak için bir fonksiyondur.
     *
     * @param pane hareket ettirilecek menü
     */
    private void moveSubScene(AnchorPane pane) {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(pane);
        if (isHidden) {
            transition.setToX(700);
            isHidden = false;
        } else {
            transition.setToX(0);
            isHidden = true;
        }
        transition.play();
    }

    /**
     * İstenilen menüyü göstermek için kullanılır.
     *
     * @param pane gösterilmek istenen menü
     */
    public void showPane(AnchorPane pane) {
        if (paneToHide != null) {
            moveSubScene(paneToHide);
        }
        moveSubScene(pane);
        paneToHide = pane;
    }
}
