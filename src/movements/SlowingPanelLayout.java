/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movements;

import javafx.scene.layout.AnchorPane;
import model.GameObject;
import model.SlowingPanel;

/**
 *
 * @author burak
 */
public class SlowingPanelLayout extends SHIPLayout implements StartInterface {

    private GameObject slowingPanel;

    public SlowingPanelLayout(GameObject ship) {
        super(ship);
        this.slowingPanel = new SlowingPanel();//panel nesnesini oluşturduk.
    }

    /**
     *
     * @param object null
     */
    @Override
    public void startGame(Object[] object) {
        super.move();
        move();
    }

    /**
     * Yavaşlatma panelinin hareket durumu ve Uzay gemisi o panelin içine
     * girdiğinde yavaşlaması durumu
     */
    @Override
    protected void move() {
        if (slowingPanel.getLayoutY() > 800) {
            setNewElementPosition(slowingPanel);
        }
        if (slowingPanel.getLayoutY() + slowingPanel.getFitHeight() >= 0) {
            slowingPanel.setLayoutY(slowingPanel.getLayoutY() + 0.5f);
            if (checkIfElementsCollide(slowingPanel, getShip())
                    && slowingPanel.getLayoutY() < getShip().getLayoutY() + getShip().getFitHeight()) {
                setRotateIncrease(3);
                setLayoutXIncrease(1);
            } else {
                setRotateIncrease(5);
                setLayoutXIncrease(3);
            }
        } else {
            slowingPanel.setLayoutY(slowingPanel.getLayoutY() + 2);
            setRotateIncrease(5);
            setLayoutXIncrease(3);
        }
    }

    @Override
    public void addObjects(AnchorPane gamePane) {
        gamePane.getChildren().add(this.slowingPanel);
        gamePane.getChildren().add(this.getShip());
    }

    @Override
    public StartInterface getStartInterface() {
        return this;
    }

    public GameObject getSlowingPanel() {
        return slowingPanel;
    }

    public void setSlowingPanel(GameObject slowingPanel) {
        this.slowingPanel = slowingPanel;
    }

}
