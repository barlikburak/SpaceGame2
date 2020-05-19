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
public class METEORImage extends GameObject {

    /**
     * Gelen parametreye göre resim ve boyutların atamasını yaptık.
     * @param meteor enum sınıfında tanımlanan meteorların resimlerine göre boyutlarının özelliklerini taşımaktadır.
    */
    public METEORImage(METEOR meteor) {
        super(meteor.getURL());//Meteor ressmi
        setFitWidth(meteor.getSIZE());//Genişlik
        setFitHeight(meteor.getSIZE());//Yükseklik
        setLayoutY(1000);//Dikeyde ki konumu  /*ekranın dışına yerleştirdik.*/
    }

}