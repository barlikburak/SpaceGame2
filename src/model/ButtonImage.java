/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author burak
 */
public class ButtonImage {

    private final String BUTTON_PRESSED_STYLE;
    private final String BUTTON_RELEASED_STYLE;

    public ButtonImage() {
        BUTTON_PRESSED_STYLE = "-fx-background-image: url('/view/resources/button_pressed.png');";
        BUTTON_RELEASED_STYLE = "-fx-background-image: url('/view/resources/button.png');";
    }

    public String getBUTTON_PRESSED_STYLE() {//Buton basılı olduğu sürece kalacak resim
        return BUTTON_PRESSED_STYLE;
    }

    public String getBUTTON_RELEASED_STYLE() {//Buton basılı olmadığı sürece kalacak resim
        return BUTTON_RELEASED_STYLE;
    }
}
