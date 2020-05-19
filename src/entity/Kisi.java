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
public class Kisi {

    private long kisi_id;
    private String ad;
    private String soyad;
    private String e_posta;

    private Login login;//one-to-one ilişki

    public Kisi() {
    }

    public Kisi(long kisi_id, String ad, String soyad, String e_posta, Login login) {
        this.kisi_id = kisi_id;
        this.ad = ad;
        this.soyad = soyad;
        this.e_posta = e_posta;
        this.login = login;
    }

    public long getKisi_id() {
        return kisi_id;
    }

    public void setKisi_id(long kisi_id) {
        this.kisi_id = kisi_id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getE_posta() {
        return e_posta;
    }

    public void setE_posta(String e_posta) {
        this.e_posta = e_posta;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

}
