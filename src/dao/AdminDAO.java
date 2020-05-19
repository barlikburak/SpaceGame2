/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Admin;
import entity.Kisi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author burak
 */
public class AdminDAO extends AbstractDAO {

    private KisiDAO kisiDAO;

    /**
     * Önce kişi sonra admin tablosuna ekleme işlemi yapılıyor.
     *
     * @param object Admin sınıfından türetilmiş nesnedir.
     * @return oluşturulan anahtar
     */
    @Override
    public Object insert(Object object) {
        Admin admin = (Admin) object;
        String query = "insert into admin (telefon, kisi_id) values (?, ?)";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, admin.getTelefon());
            pst.setLong(2, (long) getKisiDAO().insert(admin.getKisi()));
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
     * Önce kişi sonra admin tablosu güncelleniyor.
     *
     * @param object Admin sınıfından türetilmiş nesnedir.
     */
    @Override
    public void update(Object object) {
        Admin admin = (Admin) object;
        getKisiDAO().update(admin.getKisi());
        String query = "update admin set telefon=? where admin_id=?";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            pst.setString(1, admin.getTelefon());
            pst.setLong(2, admin.getAdmin_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param object id değeridir.
     * @return veritabanındaki admin tablosundaki verileri admin nesnesi olarak
     * döndürür.
     */
    @Override
    public Object findById(Object object) {
        long id = Long.valueOf(object.toString());
        Admin admin = null;
        String query = "select * from admin where admin_id=?";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            //kisi nesnesini set ederken kisiDao'dan kisi_id'ye göre arama yaparak bulup set ediyoruz.
            admin = new Admin(id, rs.getString("telefon"), (Kisi) getKisiDAO().findById(rs.getLong("kisi_id")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return admin;
    }

    /**
     * e-posta niteliğine göre arama işlemi
     *
     * @param object e-posta
     * @return admin
     */
    @Override
    public Object findByObject(Object object) {
        Admin admin = null;
        String query = "select * from admin where admin_id in (select admin_id from admin where kisi_id in (select kisi_id from kisi where e_posta=?))";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            pst.setString(1, object.toString());
            ResultSet rs = pst.executeQuery();
            rs.next();
            admin = new Admin(rs.getLong("admin_id"), rs.getString("telefon"), (Kisi) getKisiDAO().findById(rs.getLong("kisi_id")));
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return admin;
    }

    /**
     *
     * @return veritabanındaki admin tablosundaki verinin hepsini döndürüyor.
     */
    @Override
    public Object findAll() {
        ArrayList<Admin> adminList = new ArrayList<>();
        String query = "select * from admin";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Admin admin = new Admin(rs.getLong("admin_id"), rs.getString("telefon"), (Kisi) getKisiDAO().findById(rs.getLong("kisi_id")));
                adminList.add(admin);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return adminList;
    }

    public KisiDAO getKisiDAO() {
        if (this.kisiDAO == null) {
            this.kisiDAO = new KisiDAO();
        }
        return kisiDAO;
    }

}
