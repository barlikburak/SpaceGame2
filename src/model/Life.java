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
public class Life extends GameObject {

    public Life() {
        super("/view/resources/game/life.png");//Oyunda canımızı arttırmak için topladığımız can resmi(pil)
        setFitWidth(30);//Genişlik
        setFitHeight(30);//Yükseklik
        setLayoutY(1000);//Dikeyde ki konumu  /*ekranın dışına yerleştirdik.*/
    }

    /**
     *
     * @param URL menüde seçtiğimiz uzay gemisinin küçük hali (can resmi)
     */
    public Life(String URL) {
        super(URL);//Canımızın kaç olduğunu gösteren resimdir. Uzay gemimizin küçük halidir.
        setFitWidth(30);//Genişlik
        setFitHeight(22.5f);//Yükseklik
        setLayoutY(80);//Dikeyde ki konumu
        setLayoutX(460);//Yatayda ki konumu
    }

}
