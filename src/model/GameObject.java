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
public class GameObject extends ImageView implements Cloneable {

    public GameObject(String url) {
        super(new Image(url));
    }

    @Override
    public Object clone() {
        Object clone = null;

        try {
            clone = super.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return clone;
    }
}
