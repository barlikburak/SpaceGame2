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
public class SHIPImage extends GameObject {

    /**
     *
     * @param ship Menüde seçilen uzay gemisi
     */
    public SHIPImage(SHIP ship) {
        super(ship.getUrlShip());//Uzay gemisi resmi
        setFitWidth(75);//Genişlik
        setFitHeight(60);//Yükseklik
        setLayoutX(250);//Yatayda ki konumu
        setLayoutY(610);//Dikeyde ki konumu
    }

}
