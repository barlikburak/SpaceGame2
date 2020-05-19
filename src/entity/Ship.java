/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author burak
 */
public class Ship {

    private short ship_id;
    private String renk;

    public Ship() {
    }

    public Ship(short ship_id, String renk) {
        this.ship_id = ship_id;
        this.renk = renk;
    }

    public short getShip_id() {
        return ship_id;
    }

    public void setShip_id(short ship_id) {
        this.ship_id = ship_id;
    }

    public String getRenk() {
        return renk;
    }

    public void setRenk(String renk) {
        this.renk = renk;
    }

}
