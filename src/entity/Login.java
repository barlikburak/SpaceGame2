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
public class Login {

    private long login_id;
    private String sifre;

    public Login() {
    }

    public Login(long login_id, String sifre) {
        this.login_id = login_id;
        this.sifre = sifre;
    }

    public long getLogin_id() {
        return login_id;
    }

    public void setLogin_id(long login_id) {
        this.login_id = login_id;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

}
