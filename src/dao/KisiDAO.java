/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Kisi;
import entity.Login;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author burak
 */
public class KisiDAO extends AbstractDAO {

    private LoginDAO loginDAO;

    /**
     * Önce login sonra kisi tablosuna ekleme işlemi yapılıyor.
     *
     * @param object Kisi sınıfından türetilmiş nesnedir.
     * @return oluşturulan anahtar
     */
    @Override
    public Object insert(Object object) {
        Kisi kisi = (Kisi) object;
        String query = "insert into kisi (ad,soyad,e_posta,login_id) values (?, ?, ?, ?)";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, kisi.getAd());
            pst.setString(2, kisi.getSoyad());
            pst.setString(3, kisi.getE_posta());
            pst.setLong(4, (long) getLoginDAO().insert(kisi.getLogin()));
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            gk.next();
            return gk.getLong(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Önce login sonra kisi tablosu güncelleniyor.
     *
     * @param object Kisi sınıfından türetilmiş nesnedir.
     */
    @Override
    public void update(Object object) {
        Kisi kisi = (Kisi) object;
        getLoginDAO().update(kisi.getLogin());
        String query = "update kisi set ad=?, soyad=?, e_posta=? where kisi_id=?";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            pst.setString(1, kisi.getAd());
            pst.setString(2, kisi.getSoyad());
            pst.setString(3, kisi.getE_posta());
            pst.setLong(4, kisi.getKisi_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param object id değeridir.
     * @return veritabanındaki kisi tablosundaki verileri kisi nesnesi olarak
     * döndürür.
     */
    @Override
    public Object findById(Object object) {
        long id = Long.valueOf(object.toString());
        Kisi kisi = null;
        String query = "select * from kisi where kisi_id=?";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            //login nesnesini set ederken loginDao'dan login_id'ye göre arama yaparak bulup set ediyoruz.
            kisi = new Kisi(id, rs.getString("ad"), rs.getString("soyad"), rs.getString("e_posta"), (Login) getLoginDAO().findById(rs.getLong("login_id")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kisi;
    }
    

    
    /**
     * @param object e-posta adresi
     * @return daha önce aynı e-posta ile kayıt gerçekleştirilme durumunu
     * döndürür.
     */
    @Override
    public Object findByObject(Object object) {
        String e_posta = object.toString();
        String query = "select * from kisi where e_posta=?";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            pst.setString(1, e_posta);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     *
     * @return veritabanındaki kisi tablosundaki verinin hepsini döndürüyor.
     */
    @Override
    public Object findAll() {
        ArrayList<Kisi> kisiList = new ArrayList<>();
        String query = "select * from kisi";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Kisi kisi = new Kisi(rs.getLong("kisi_id"), rs.getString("ad"), rs.getString("soyad"), rs.getString("e_posta"), (Login) getLoginDAO().findById(rs.getLong("login_id")));
                kisiList.add(kisi);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return kisiList;
    }

    public LoginDAO getLoginDAO() {
        if (this.loginDAO == null) {
            this.loginDAO = new LoginDAO();
        }
        return loginDAO;
    }

}
