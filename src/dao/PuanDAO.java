/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Oyuncu;
import entity.Puan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author burak
 */
public class PuanDAO extends AbstractDAO {

    private OyuncuDAO oyuncuDAO;

    /**
     * Puan tablosuna veri ekliyoruz.
     *
     * @param object Oyuncu sınıfından türetilmiş nesnedir.
     * @return oluşturulan anahtar
     */
    @Override
    public Object insert(Object object) {
        Puan puan = (Puan) object;
        String query = "insert into puan (puan, oyuncu_id) values (?, ?)";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setLong(1, puan.getPuan());
            pst.setLong(2, puan.getOyuncu().getOyuncu_id());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            gk.next();
            long puan_id = gk.getLong(1);
            /**
             * Oyuncu belirli puanları geçtikçe yeni uzay gemilerini kullanıma
             * açıyoruz ve bunları oyuncu ve uzay gemisinin ilişkili tablosuna
             * ekliyoruz.
             */
            byte shipCount = (byte) puan.getOyuncu().getShipList().size();
            if (shipCount == 1 && puan.getPuan() >= 25) {
                insertOyuncuShipRelationShip(puan.getOyuncu().getOyuncu_id(), (byte) 2);
                shipCount++;
            }
            if (shipCount == 2 && puan.getPuan() >= 50) {
                insertOyuncuShipRelationShip(puan.getOyuncu().getOyuncu_id(), (byte) 3);
                shipCount++;
            }
            if (shipCount == 3 && puan.getPuan() >= 100) {
                insertOyuncuShipRelationShip(puan.getOyuncu().getOyuncu_id(), (byte) 4);
            }

            return puan_id;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param object oyuncu id değeridir.
     * @return veritabanındaki puan tablosundaki verileri puan nesnesi olarak
     * döndürür.
     */
    @Override
    public Object findById(Object object) {
        long id = Long.valueOf(object.toString());
        String query = "select * from puan where oyuncu_id=? order by puan desc";
        ArrayList<Puan> puanList = new ArrayList<>();
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            Puan puan;
            while (rs.next()) {
                //oyuncu nesnesini set ederken oyuncuDao'dan oyuncu_id'ye göre arama yaparak bulup set ediyoruz.
                puan = new Puan(rs.getLong("puan_id"), rs.getLong("puan"), rs.getTimestamp("zaman"), (Oyuncu) getOyuncuDAO().findById(rs.getLong("oyuncu_id")));
                puanList.add(puan);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return puanList;
    }

    /**
     * puan nesnesi içindeki farklı bir nitelik için arama işlemine ihtiyaç
     * yoktur.
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
     * @return veritabanındaki puan tablosundaki verinin hepsini döndürüyor.
     */
    @Override
    public Object findAll() {
        ArrayList<Puan> puanList = new ArrayList<>();
        String query = "select * from puan";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Puan puan = new Puan(rs.getLong("puan_id"), rs.getLong("puan"), rs.getTimestamp("zaman"), (Oyuncu) getOyuncuDAO().findById(rs.getLong("oyuncu_id")));
                puanList.add(puan);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return puanList;
    }

    /**
     * Gelen parametreleri ilişkili tabloya ekliyoruz.
     *
     * @param oyuncu_id
     * @param ship_id
     * @throws SQLException
     */
    private void insertOyuncuShipRelationShip(long oyuncu_id, byte ship_id) throws SQLException {
        PreparedStatement pst = this.getConnection().prepareStatement("insert into oyuncu_ship_relationship (oyuncu_id, ship_id) values (?, ?)");
        pst.setLong(1, oyuncu_id);
        pst.setLong(2, ship_id);
        pst.executeUpdate();
    }

    public OyuncuDAO getOyuncuDAO() {
        if (this.oyuncuDAO == null) {
            this.oyuncuDAO = new OyuncuDAO();
        }
        return oyuncuDAO;
    }

}
