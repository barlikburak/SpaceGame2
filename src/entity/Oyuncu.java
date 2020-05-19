/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author burak
 */
public class Oyuncu {

    private long oyuncu_id;
    private short yas;

    private Kisi kisi;//is-a ilişkisi
    private ArrayList<Ship> shipList;//many-to-many ilişkisi

    public Oyuncu() {
    }

    public Oyuncu(long oyuncu_id, short yas, Kisi kisi, ArrayList<Ship> shipList) {
        this.oyuncu_id = oyuncu_id;
        this.yas = yas;
        this.kisi = kisi;
        this.shipList = shipList;
    }

    public long getOyuncu_id() {
        return oyuncu_id;
    }

    public void setOyuncu_id(long oyuncu_id) {
        this.oyuncu_id = oyuncu_id;
    }

    public short getYas() {
        return yas;
    }

    public void setYas(short yas) {
        this.yas = yas;
    }

    public Kisi getKisi() {
        return kisi;
    }

    public void setKisi(Kisi kisi) {
        this.kisi = kisi;
    }

    public ArrayList<Ship> getShipList() {
        return shipList;
    }

    public void setShipList(ArrayList<Ship> shipList) {
        this.shipList = shipList;
    }

}
