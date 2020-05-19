/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
public class LoginDAO extends AbstractDAO {

    /**
     * Login tablosuna ekleme işlemi yapılıyor.
     *
     * @param object Login sınıfından türetilmiş nesnedir.
     * @return oluşturulan anahtar
     */
    @Override
    public Object insert(Object object) {
        Login login = (Login) object;
        String query = "insert into login (sifre) values (?)";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, login.getSifre());
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
     * Login tablosu güncelleniyor.
     *
     * @param object Login sınıfından türetilmiş nesnedir.
     */
    @Override
    public void update(Object object) {
        Login login = (Login) object;
        String query = "update login set sifre=? where login_id=?";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            pst.setString(1, login.getSifre());
            pst.setLong(2, login.getLogin_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param object id değeridir.
     * @return veritabanındaki login tablosundaki verileri login nesnesi olarak
     * döndürür.
     */
    @Override
    public Object findById(Object object) {
        long id = Long.valueOf(object.toString());
        Login login = null;
        String query = "select * from login where login_id=?";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            login = new Login(id, rs.getString("sifre"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return login;
    }

    /**
     * Sadece id'ye göre arama işlemi gerçekleştirdiğimizden dolayı bu metota
     * ihtiyaç yoktur.
     *
     * @param object
     * @return
     */
    @Override
    public Object findByObject(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return veritabanındaki login tablosundaki verinin hepsini döndürüyor.
     */
    @Override
    public Object findAll() {
        ArrayList<Login> loginList = new ArrayList<>();
        String query = "select * from login";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Login login = new Login(rs.getLong("login_id"), rs.getString("sifre"));
                loginList.add(login);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return loginList;
    }

    /**
     * Kullanıcı adı ve şifrenin doğruluğunu ve bunun doğru kullanıcıya ait
     * olduğunu kontrol eden metot.
     *
     * @param eposta
     * @param password
     * @param giris
     * @return login onayı
     */
    public boolean login(String eposta, String password, String giris) {
        String query = "select * from login where login_id in (Select login_id from kisi where e_posta=? and kisi_id in (select kisi_id from " + giris.toLowerCase() + ")) and sifre=?";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            pst.setString(1, eposta);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return false;
    }

}
