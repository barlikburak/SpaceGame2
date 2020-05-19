/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movements;

import javafx.animation.FadeTransition;
import javafx.util.Duration;
import model.Explosion;
import model.GameObject;
import model.METEOR;
import model.METEORImage;

/**
 *
 * @author burak
 */
public class MeteorLayout extends LaserLayout {

    private GameObject[] meteorList;
    private GameObject[] explosion;
    private byte explosionIndex;

    public MeteorLayout(GameObject ship) {
        super(ship);
        /* Meteor nesnelerini oluşturduk. */
        this.meteorList = new GameObject[10];
        meteorList[0] = new METEORImage(METEOR.MeteorBrown_big1);
        meteorList[1] = new METEORImage(METEOR.MeteorGrey_med1);
        meteorList[2] = new METEORImage(METEOR.MeteorBrown_big4);
        meteorList[3] = new METEORImage(METEOR.MeteorGrey_big4);
        meteorList[4] = new METEORImage(METEOR.MeteorGrey_big3);
        meteorList[5] = new METEORImage(METEOR.MeteorBrown_big3);
        meteorList[6] = new METEORImage(METEOR.MeteorBrown_big2);
        meteorList[7] = new METEORImage(METEOR.MeteorGrey_big1);
        meteorList[8] = new METEORImage(METEOR.MeteorGrey_big2);
        meteorList[9] = new METEORImage(METEOR.MeteorGrey_big1);

        explosion = new GameObject[20];
        explosion[0] = new Explosion();
        //Patlama nesnelerini oluşturduk.
        for (int i = 1; i < explosion.length; i++) {
            explosion[i] = (GameObject) explosion[0].clone();
        }

        explosionIndex = 0;
    }

    /**
     * Laser ile meteorların çarpışması durumunda laser ve meteorun kaybolup
     * yerine patlama resminin gelmesi işlemidir.
     */
    protected void meteorBreaking() {
        for (GameObject laserList : getLaserList()) {
            for (GameObject meteorList1 : meteorList) {
                if (checkIfElementsCollide(meteorList1, laserList)) {
                    explosion[explosionIndex].setLayoutX(meteorList1.getLayoutX());
                    explosion[explosionIndex].setLayoutY(meteorList1.getLayoutY());
                    opacitySet(explosion[explosionIndex]);
                    if (++explosionIndex == 20) {
                        explosionIndex = 0;
                    }
                    laserList.setLayoutY(-100);
                    laserList.setLayoutX(0);
                    setNewElementPosition(meteorList1);
                }
            }
        }
    }

    /**
     * Patlama resminin ekrandan yavaşça kaybolmasını sağlar.
     *
     * @param explosion patlama efekti
     */
    private void opacitySet(GameObject explosion) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(5000));
        fade.setFromValue(10);
        fade.setToValue(0);
        fade.setNode(explosion);
        fade.play();
    }

    /**
     * Meteorların hareket işlemi
     */
    @Override
    protected void move() {
        super.move();
        for (int i = 0; i < meteorList.length; i++) {
            if (meteorList[i].getLayoutY() < 700) {
                meteorList[i].setLayoutY(meteorList[i].getLayoutY() + ((i + 1) * 0.7));
                meteorList[i].setRotate(meteorList[i].getRotate() + (i + 1) * 0.5);
            } else {
                setNewElementPosition(meteorList[i]);
            }
        }
    }

    /**
     * Meteora yeni konum ataması işlemi gerçekleştirir.
     *
     * @param index listedeki meteorun yerini bildirir.
     */
    private void setMeteorPosition(int index) {
        setNewElementPosition(meteorList[index]);
    }

    public GameObject[] getMeteorList() {
        return meteorList;
    }

    public void setMeteorList(GameObject[] meteorList) {
        this.meteorList = meteorList;
    }

    public GameObject[] getExplosion() {
        return explosion;
    }

    public void setExplosion(GameObject[] explosion) {
        this.explosion = explosion;
    }

    public byte getExplosionIndex() {
        return explosionIndex;
    }

    public void setExplosionIndex(byte explosionIndex) {
        this.explosionIndex = explosionIndex;
    }

}
