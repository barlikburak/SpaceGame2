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
public class SlowingPanel extends GameObject {

    public SlowingPanel() {
        super("/view/resources/game/slowing_panel.png");//Yavaşlatma paneli resmi
        setFitHeight(150);//Yükseklik
        setFitWidth(200);//Genişlik
        setLayoutY(1000);//Dikeyde ki konumu  /*ekranın dışına yerleştirdik.*/
    }

}