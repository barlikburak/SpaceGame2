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
public class Star extends GameObject {

    public Star() {
        super("/view/resources/game/star.png");//Puan kazanmak için olan yıldız resmi
        setFitHeight(30);//Yükseklik
        setFitWidth(30);//Genişlik
        setLayoutY(1000);//Dikeyde ki konumu  /*ekranın dışına yerleştirdik.*/
    }

}
