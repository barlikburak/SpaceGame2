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
public class Explosion extends GameObject{

    public Explosion() {
        super("/view/resources/game/explosion.png");//patlama resmi
        setFitWidth(70);//Genişlik
        setFitHeight(70);//Yükseklik
        setLayoutY(-100);//Dikeyde ki konumu  /*ekranın dışına yerleştirdik*/
    }


}
