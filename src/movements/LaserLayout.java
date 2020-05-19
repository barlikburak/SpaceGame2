/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movements;

import model.GameObject;
import model.Laser;

/**
 *
 * @author burak
 */
public class LaserLayout extends SHIPLayout {

    private boolean isSpaceKeyPressed;
    private boolean isLaserDistanceControl;
    private GameObject[] laserList;

    public LaserLayout(GameObject ship) {
        super(ship);
        this.isSpaceKeyPressed = false;//Space(Boşluk) tuşuna basılma durumu
        laserList = new GameObject[10];
        laserList[0] = new Laser();
        //Laser nesnelerimizi oluşturduk.
        for (int i = 1; i < laserList.length; i++) {
            laserList[i] = (GameObject) laserList[0].clone();
        }
    }

    /**
     * Ekranda laser varsa o ekrandan dışarı çıkana kadar hareket ettiriyoruz.
     * Boşluk tuşuna basıldığında da ekrana laser yerleştiriyoruz.
     */
    @Override
    protected void move() {
        for (GameObject laserList1 : laserList) {
            if (laserList1.getLayoutY() > -80) {
                laserList1.setLayoutY(laserList1.getLayoutY() - 2);
            }
        }
        if (isSpaceKeyPressed) {
            for (int i = 0; i < laserList.length - 1; i++) {
                isLaserDistanceControl = true;
                for (int j = i - 1; j >= 0 && isLaserDistanceControl; j--) {
                    if (laserList[j].getLayoutY() > (450 - (j * 50))) {
                        isLaserDistanceControl = false;
                    }
                }
                if (isLaserDistanceControl && laserList[i].getLayoutY() < -50) {
                    laserList[i].setLayoutY(550);
                    laserList[i].setLayoutX(getShip().getLayoutX() + 35);
                }
            }
        }
    }

    public boolean isIsSpaceKeyPressed() {
        return isSpaceKeyPressed;
    }

    public void setIsSpaceKeyPressed(boolean isSpaceKeyPressed) {
        this.isSpaceKeyPressed = isSpaceKeyPressed;
    }

    public boolean isIsLaserDistanceControl() {
        return isLaserDistanceControl;
    }

    public void setIsLaserDistanceControl(boolean isLaserDistanceControl) {
        this.isLaserDistanceControl = isLaserDistanceControl;
    }

    public GameObject[] getLaserList() {
        return laserList;
    }

    public void setLaserList(GameObject[] laserList) {
        this.laserList = laserList;
    }

}
