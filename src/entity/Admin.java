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
public class Admin {

    private long admin_id;
    private String Telefon;

    private Kisi kisi;//is-a ilişkisi

    public Admin() {
    }

    public Admin(long admin_id, String Telefon, Kisi kisi) {
        this.admin_id = admin_id;
        this.Telefon = Telefon;
        this.kisi = kisi;
    }

    public long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(long admin_id) {
        this.admin_id = admin_id;
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String Telefon) {
        this.Telefon = Telefon;
    }

    public Kisi getKisi() {
        return kisi;
    }

    public void setKisi(Kisi kisi) {
        this.kisi = kisi;
    }

}
