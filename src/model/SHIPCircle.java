/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author burak
 */
public abstract class SHIPCircle implements SHIPPickerInterface {

    private final String circleNotChoosen;
    private final String circleChoosen;
    private ImageView circleImage;

    public SHIPCircle() {
        circleNotChoosen = "/view/resources/shipchooser/grey_circle.png";
        circleChoosen = "/view/resources/shipchooser/boxTick.png";
    }

    @Override
    public abstract SHIP getSHIP();

    @Override
    public void setCircleChoosenImage(ImageView circleImage) {
        this.circleImage = circleImage;
        this.circleImage.setImage(new Image(circleChoosen));
    }

    @Override
    public void setCircleNotChoosesen() {
        circleImage.setImage(new Image(circleNotChoosen));
    }

}
