/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Font;

/**
 *
 * @author burak
 */
public class PointsLabel extends Label {

    public PointsLabel() {
        super("POINTS : 00");
        Image image = new Image("/view/resources/info_label_blue.png", 150, 50, false, true);//Resim ataması yaptık.
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);//Label için background nesnesi oluşturduk.
        setBackground(new Background(backgroundImage));//Label'a background ataması yaptık.
        setAlignment(Pos.CENTER);//Metni ortalayarak yaz.
        setPrefWidth(130);//Genişlik
        setPrefHeight(50);//Yükseklik
        setLayoutX(460);//Yatayda ki Konumu
        setLayoutY(10);//Dikeyde ki konumu
        setFont(new Font(null, 18));
    }

}