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
public class SHIPPickerGreen extends SHIPCircle {

    private final SHIP ship;

    public SHIPPickerGreen() {
        this.ship = SHIP.Green;
    }

    @Override
    public SHIP getSHIP() {
        return this.ship;
    }

}
