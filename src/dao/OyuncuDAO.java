/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Kisi;
import entity.Oyuncu;
import entity.Ship;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author burak
 */
public class OyuncuDAO extends AbstractDAO {

    private KisiDAO kisiDAO;
    private ShipDAO shipDAO;

    /**
     * Önce kisi sonra oyuncu daha sonra da oyuncu_ship_relationship tablosuna
     * veri ekleniyor.
     *
     * @param object Oyuncu sınıfından türetilmiş nesnedir.
     * @return oluşturulan anahtar
     */
    @Override
    public Object insert(Object object) {
        Oyuncu oyuncu = (Oyuncu) object;
        String query = "insert into oyuncu (yas, kisi_id) values (?, ?)";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setLong(1, oyuncu.getYas());
            pst.setLong(2, (long) getKisiDAO().insert(oyuncu.getKisi()));
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            gk.next();
            long oyuncu_id = gk.getLong(1);
            /**
             * Eklenen her oyuncuya default olarak 1 tane uzay gemisi veriyoruz
             * ve bu verdiğimiz uzay gemisini ve oyuncuyu ilişkili tabloya
             * ekliyoruz.
             */
            pst = this.getConnection().prepareStatement("insert into oyuncu_ship_relationship (oyuncu_id, ship_id) values (?, ?)");
            pst.setLong(1, oyuncu_id);
            pst.setLong(2, 1);
            pst.executeUpdate();

            return oyuncu_id;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Önce kişi sonra oyuncu tablosu güncelleniyor.
     *
     * @param object Oyuncu sınıfından türetilmiş nesnedir.
     */
    @Override
    public void update(Object object) {
        Oyuncu oyuncu = (Oyuncu) object;
        getKisiDAO().update(oyuncu.getKisi());
        String query = "update oyuncu set yas=? where oyuncu_id=?";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            pst.setShort(1, oyuncu.getYas());
            pst.setLong(2, oyuncu.getOyuncu_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param object id değeridir.
     * @return veritabanındaki oyuncu tablosundaki verileri oyuncu nesnesi
     * olarak döndürür.
     */
    @Override
    public Object findById(Object object) {
        long id = Long.valueOf(object.toString());
        Oyuncu oyuncu = null;
        String query = "select * from oyuncu where oyuncu_id=?";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            //kisi nesnesini set ederken kisiDao'dan kisi_id'ye göre arama yaparak bulup set ediyoruz.
            oyuncu = new Oyuncu(id, rs.getShort("yas"), (Kisi) getKisiDAO().findById(rs.getLong("kisi_id")), getShipList(id));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return oyuncu;
    }

    /**
     * e-posta niteliğine göre arama işlemi
     *
     * @param object e-posta
     * @return oyuncu
     */
    @Override
    public Object findByObject(Object object) {
        Oyuncu oyuncu = null;
        String query = "select * from oyuncu where oyuncu_id in (select oyuncu_id from oyuncu where kisi_id in (select kisi_id from kisi where e_posta=?))";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            pst.setString(1, object.toString());
            ResultSet rs = pst.executeQuery();
            rs.next();
            oyuncu = new Oyuncu(rs.getLong("oyuncu_id"), rs.getShort("yas"), (Kisi) getKisiDAO().findById(rs.getLong("kisi_id")), getShipList(rs.getLong("oyuncu_id")));
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return oyuncu;
    }

    /**
     *
     * @return veritabanındaki oyuncu tablosundaki verinin hepsini döndürüyor.
     */
    @Override
    public Object findAll() {
        ArrayList<Oyuncu> oyuncuList = new ArrayList<>();
        String query = "select * from oyuncu";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Oyuncu oyuncu = new Oyuncu(rs.getLong("oyuncu_id"), rs.getShort("yas"), (Kisi) getKisiDAO().findById(rs.getLong("kisi_id")), getShipList(rs.getLong("oyuncu_id")));
                oyuncuList.add(oyuncu);
            }
            return oyuncuList;
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }

    /**
     *
     * @param oyuncu_id ilişkili tablodan arama yapmak için kullanıyoruz.
     * @return oyuncuların sahip oldukları uzay gemilerini liste şeklinde
     * döndürür.
     */
    private ArrayList<Ship> getShipList(long oyuncu_id) {
        ArrayList<Ship> shipList = new ArrayList<>();
        String query = "select * from oyuncu_ship_relationship where oyuncu_id=?";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            pst.setLong(1, oyuncu_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                //ilişkili olan her uzay gemisinin id'sine göre arama yaparak ekliyoruz.
                shipList.add((Ship) getShipDAO().findById(rs.getShort("ship_id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return shipList;
    }

    public KisiDAO getKisiDAO() {
        if (this.kisiDAO == null) {
            this.kisiDAO = new KisiDAO();
        }
        return kisiDAO;
    }

    public ShipDAO getShipDAO() {
        if (this.shipDAO == null) {
            this.shipDAO = new ShipDAO();
        }
        return shipDAO;
    }

}
