/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import util.Connector;

/**
 *
 * @author burak
 */
public abstract class AbstractDAO {

    private Connector connector;
    private Connection connection;

    public Connector getConnector() {
        if (this.connector == null) {
            this.connector = new Connector();
        }
        return this.connector;
    }

    public Connection getConnection() {
        if (this.connection == null) {
            this.connection = getConnector().connect();
        }
        return connection;
    }

    public abstract Object insert(Object object);

    public abstract void update(Object object);

    public abstract Object findById(Object object);
    
    public abstract Object findByObject(Object object);

    public abstract Object findAll();
}
