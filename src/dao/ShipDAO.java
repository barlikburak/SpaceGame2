/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Ship;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author burak
 */
public class ShipDAO extends AbstractDAO {

    /**
     * Uzay gemimiz sadece 4 tane olacağından dolayı ekleme işlemi yapmıyoruz.
     *
     * @param object
     * @return
     */
    @Override
    public Object insert(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Uzay gemilerimizin resimlerinin yani renklerininde değişmeyeceğinden
     * dolayı güncelleme yapmıyoruz.
     *
     * @param object
     */
    @Override
    public void update(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param object id değeridir.
     * @return veritabanındaki ship tablosundaki verileri ship nesnesi olarak
     * döndürür.
     */
    @Override
    public Object findById(Object object) {
        short id = Short.valueOf(object.toString());
        Ship ship = null;
        String query = "select * from ship where ship_id=?";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            pst.setShort(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            ship = new Ship(id, rs.getString("renk"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ship;
    }

    /**
     * ship nesnesi içindeki farklı bir nitelik için arama işlemine ihtiyaç
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
     * Ship tablomuzdaki verilerimiz sabit olduğu için hepsini görüntüleme
     * işlemi yapmıyoruz.
     *
     * @return
     */
    @Override
    public Object findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
