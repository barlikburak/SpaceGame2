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
public class Laser extends GameObject {
    
    public Laser() {
        super("/view/resources/game/laser.png");//Ateş etmek için kullanılacak olan laser resmi
        setLayoutY(-100);//Dikeyde ki konumu  /*ekranın dışına yerleştirdik*/
    }

}
