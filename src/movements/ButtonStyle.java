/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movements;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import model.ButtonImage;

/**
 *
 * @author burak
 */
public class ButtonStyle extends ButtonImage {

    
    public void setOnMouseReleased(Button button) {
        button.setStyle(getBUTTON_RELEASED_STYLE());//buton stili ataması
        button.setPrefHeight(49);//Yükseklik
        button.setLayoutY(button.getLayoutY() - 4);//Mouse tıklaması bittiğinde buton konumunu dikey olarak 4 azaltır.
    }

    public void setOnMousePressed(Button button) {
        button.setStyle(getBUTTON_PRESSED_STYLE());//buton stili ataması
        button.setPrefHeight(45);//Yükseklik
        button.setLayoutY(button.getLayoutY() + 4);//Mouse tıklaması gerçekleştiğinde buton konumunu dikey olarak 4 arttırır.
    }

    public void setOnMouseEntered(Button button) {
        //Mouse butonun üzerinde olduğu zaman verilen efekt
        button.setEffect(new DropShadow());
    }

    public void setOnMouseExited(Button button) {
        //Mouse butonun üzerinde olmadığı zaman efekti kaldırma
        button.setEffect(null);
    }
}
