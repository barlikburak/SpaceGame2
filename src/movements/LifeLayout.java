/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movements;

import javafx.scene.layout.AnchorPane;
import model.GameObject;
import model.Life;

/**
 *
 * @author burak
 */
public class LifeLayout extends MeteorLayout implements StartInterface {

    private GameObject life;
    private GameObject[] playerLife;
    private byte lifeCount;

    public LifeLayout(GameObject ship, String lifeURL) {
        super(ship);
        life = new Life();//Kazanılan can(pil) nesnesi oluşturduk.
        //kalan can nesnesi oluşturduk.
        playerLife = new GameObject[3];
        playerLife[0] = new Life(lifeURL);
        for (int i = 1; i < playerLife.length; i++) {
            playerLife[i] = new Life(lifeURL);
            playerLife[i].setLayoutX(playerLife[i].getLayoutX() + (i * 50));//yatay eksende konum ataması yaptık.
        }
        this.lifeCount = 2;//Kalan can sayısı
    }

    /**
     *
     * @param object boolean değerdir ve can kalıp kalmadığını return eder.
     */
    @Override
    public void startGame(Object[] object) {
        super.move();
        meteorBreaking();
        move();
        object[0] = removeLife();
        lifeCollide();
    }

    /**
     * Can toplandığında(pil ile uzay gemisi çarpışmasında) yapılacak işlemler.
     */
    private void lifeCollide() {
        if (checkIfElementsCollide(life, getShip()) && lifeCount < 2) {
            setNewElementPosition(life);
            setNewElementRotate(life);
            playerLife[++this.lifeCount].setVisible(true);
        }
    }

    /**
     *
     * @return canın bitip bitmediğini kontrol eder.
     */
    private Boolean removeLife() {
        for (GameObject meteorList : getMeteorList()) {
            if (checkIfElementsCollide(meteorList, getShip())) {
                setNewElementPosition(meteorList);
                playerLife[lifeCount].setVisible(false);
                return this.lifeCount-- == 0;
            }
        }
        return false;
    }

    /**
     * canın (pil) hareket durumu
     */
    @Override
    protected void move() {
        if (life.getLayoutY() < 4000) {
            life.setLayoutY(life.getLayoutY() + 9);
        } else {
            setNewElementPosition(life);
            setNewElementRotate(life);
        }
    }

    @Override
    public void addObjects(AnchorPane gamePane) {
        gamePane.getChildren().add(this.life);
        gamePane.getChildren().addAll(this.playerLife);
        gamePane.getChildren().addAll(this.getExplosion());
        gamePane.getChildren().addAll(this.getLaserList());
        gamePane.getChildren().addAll(this.getMeteorList());
    }

    @Override
    public StartInterface getStartInterface() {
        return this;
    }

    public GameObject getLife() {
        return life;
    }

    public void setLife(GameObject life) {
        this.life = life;
    }

    public GameObject[] getPlayerLife() {
        return playerLife;
    }

    public void setPlayerLife(GameObject[] playerLife) {
        this.playerLife = playerLife;
    }

    public byte getLifeCount() {
        return lifeCount;
    }

    public void setLifeCount(byte lifeCount) {
        this.lifeCount = lifeCount;
    }

}
