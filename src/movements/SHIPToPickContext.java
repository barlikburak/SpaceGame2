/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movements;

import javafx.scene.image.ImageView;
import model.SHIP;
import model.SHIPPickerInterface;

/**
 *
 * @author burak
 */
public class SHIPToPickContext {

    private final SHIPPickerInterface shipToPick;

    public SHIPToPickContext(SHIPPickerInterface shipToPick) {
        this.shipToPick = shipToPick;
    }

    public void setCircleChoosenImage(ImageView circleImage) {
        this.shipToPick.setCircleChoosenImage(circleImage);
    }

    public void setCircleNotChoosesen() {
        this.shipToPick.setCircleNotChoosesen();
    }

    public SHIP getSHIP() {
        return this.shipToPick.getSHIP();
    }
}

