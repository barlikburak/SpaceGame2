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
public enum SHIP {//Uzay gemisi resimleri ve canımızın kaç kaldığını gösteren resimler bulunur.
    Blue("/view/resources/shipchooser/playerShip_blue.png", "/view/resources/shipchooser/playerLife_blue.png"),
    Green("/view/resources/shipchooser/playerShip_green.png", "/view/resources/shipchooser/playerLife_green.png"),
    Orange("/view/resources/shipchooser/playerShip_orange.png", "/view/resources/shipchooser/playerLife_orange.png"),
    Red("/view/resources/shipchooser/playerShip_red.png", "/view/resources/shipchooser/playerLife_red.png");

    private final String urlShip;
    private final String urlLife;

    private SHIP(String urlShip, String urlLife) {
        this.urlShip = urlShip;
        this.urlLife = urlLife;
    }

    public String getUrlShip() {
        return urlShip;
    }

    public String getUrlLife() {
        return urlLife;
    }
}
