/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author burak
 */
public class GameDAO extends AbstractDAO {

    /**
     * Yardım yazısı sadece 1 tane olacağından dolayı insert işlemi yapmıyoruz.
     *
     * @param object
     * @return
     */
    @Override
    public Object insert(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Oyun hakkındaki bilgi yazısı güncelleniyor.
     *
     * @param object String bir değerdir.
     */
    @Override
    public void update(Object object) {
        String query = "update game set help=?";
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
            pst.setString(1, object.toString());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tabloda zaten sadece 1 veri bulunduğu için bu metodu kullanmıyoruz.
     *
     * @param object
     * @return
     */
    @Override
    public Object findById(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Tabloda zaten sadece 1 veri bulunduğu için bu metodu kullanmıyoruz.
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
     * @return Oyun hakkındaki bilgi yazısını döndürüyor.
     */
    @Override
    public Object findAll() {
        String query = "select * from game";
        try {

            PreparedStatement pst = getConnection().prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            rs.next();
            return rs.getString("help");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "";
    }

}
