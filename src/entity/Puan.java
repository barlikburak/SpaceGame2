/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Timestamp;

/**
 *
 * @author burak
 */
public class Puan {

    private long puan_id;
    private long puan;
    private Timestamp zaman;

    private Oyuncu oyuncu;//one-to-many ilişkisi

    public Puan() {
    }

    public Puan(long puan_id, long puan, Timestamp zaman, Oyuncu oyuncu) {
        this.puan_id = puan_id;
        this.puan = puan;
        this.zaman = zaman;
        this.oyuncu = oyuncu;
    }

    public long getPuan_id() {
        return puan_id;
    }

    public void setPuan_id(long puan_id) {
        this.puan_id = puan_id;
    }

    public long getPuan() {
        return puan;
    }

    public void setPuan(long puan) {
        this.puan = puan;
    }

    public Timestamp getZaman() {
        return zaman;
    }

    public void setZaman(Timestamp zaman) {
        this.zaman = zaman;
    }

    public Oyuncu getOyuncu() {
        return oyuncu;
    }

    public void setOyuncu(Oyuncu oyuncu) {
        this.oyuncu = oyuncu;
    }

}
