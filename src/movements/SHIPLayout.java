/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movements;

import model.GameObject;

/**
 *
 * @author burak
 */
public class SHIPLayout extends SpaceGame {

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private int rotateIncrease;
    private int layoutXIncrease;

    private final GameObject ship;

    /**
     *
     * @param ship oyunu oynadığımız uzay gemisi
     */
    public SHIPLayout(GameObject ship) {
        this.ship = ship;
        this.isLeftKeyPressed = false;//Sol tuşa basılma Durumu
        this.isRightKeyPressed = false;//Sağ tuşa basılma Durumu
        this.rotateIncrease = 5;//rotasyon artış hızı
        this.layoutXIncrease = 3;//konum artış hızı
    }

    /**
     * Sadece sol tuşa basılma durumu Sadece Sağ tuşa basılma durumu Hem sağ hem
     * sol tuşa basılma durumu Sağ ve Sol tuşa da basılmama durumu kontrol
     * edilerek yapılan rotasyon ve konum değişiklikleri
     */
    protected void move() {
        if (isLeftKeyPressed && !isRightKeyPressed) {
            if (ship.getRotate() > -30) {
                ship.setRotate(ship.getRotate() - rotateIncrease);
            }
            if (ship.getLayoutX() > -10) {
                ship.setLayoutX(ship.getLayoutX() - layoutXIncrease);
            }
        }
        if (!isLeftKeyPressed && isRightKeyPressed) {
            if (ship.getRotate() < 30) {
                ship.setRotate(ship.getRotate() + rotateIncrease);
            }
            if (ship.getLayoutX() < 530) {
                ship.setLayoutX(ship.getLayoutX() + layoutXIncrease);
            }
        }
        if (!isLeftKeyPressed && !isRightKeyPressed) {
            if (ship.getRotate() < 0) {
                ship.setRotate(ship.getRotate() + 5);
            } else if (ship.getRotate() > 0) {
                ship.setRotate(ship.getRotate() - 5);
            }
        }
        if (isLeftKeyPressed && isRightKeyPressed) {
            if (ship.getRotate() < 0) {
                ship.setRotate(ship.getRotate() + 5);
            } else if (ship.getRotate() > 0) {
                ship.setRotate(ship.getRotate() - 5);
            }
        }
    }

    public boolean isIsLeftKeyPressed() {
        return isLeftKeyPressed;
    }

    public void setIsLeftKeyPressed(boolean isLeftKeyPressed) {
        this.isLeftKeyPressed = isLeftKeyPressed;
    }

    public boolean isIsRightKeyPressed() {
        return isRightKeyPressed;
    }

    public void setIsRightKeyPressed(boolean isRightKeyPressed) {
        this.isRightKeyPressed = isRightKeyPressed;
    }

    public GameObject getShip() {
        return ship;
    }

    public int getRotateIncrease() {
        return rotateIncrease;
    }

    public void setRotateIncrease(int rotateIncrease) {
        this.rotateIncrease = rotateIncrease;
    }

    public int getLayoutXIncrease() {
        return layoutXIncrease;
    }

    public void setLayoutXIncrease(int layoutXIncrease) {
        this.layoutXIncrease = layoutXIncrease;
    }

}
